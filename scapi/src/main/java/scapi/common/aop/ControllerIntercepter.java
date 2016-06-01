package scapi.common.aop;

import java.util.HashMap;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.perf4j.LoggingStopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import scapi.common.constant.APIConstant;
import scapi.model.display.ResultJson;
import scapi.model.display.ResultMeta;

@Component("controllerIntercepter")
@Slf4j
public class ControllerIntercepter {

	@Resource(name="resultCodeMap")
	HashMap<Integer, String> resultCodeMap;
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<ResultJson> controllerPointCutMethod(ProceedingJoinPoint pjp) throws Throwable {
//		LoggingStopWatch perfStopWatch = null;
//		perfStopWatch = new Log4JStopWatch(org.apache.log4j.Logger.getLogger(Log4JStopWatch.class));
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		String args = "";
		Object[] signatureArgs = pjp.getArgs();
		for (Object signatureArg : signatureArgs) {
			args += signatureArg + ",";
		}

		args = args.length() > 0 ? args.substring(0,args.length() - 1) : args;
		log.info("[Controller Log] \t{}.{} \tSTART : [{}]", pjp.getTarget().getClass().getName(), pjp.getSignature().getName(),args);
		
		ResultJson cbjson = new ResultJson();
		HttpStatus httpStatus;
		try {
			ResponseEntity<ResultJson> responseEntity = (ResponseEntity<ResultJson>) pjp.proceed();

			cbjson = responseEntity.getBody();
			if(cbjson!= null && cbjson.getMeta() != null && cbjson.getMeta().getMessage() == null){
				cbjson.setMeta(new ResultMeta(cbjson.getMeta().getCode(), resultCodeMap.get(cbjson.getMeta().getCode()),cbjson.getMeta().getErrors() ));
			}
			
			/*
			 * assign http status by different result code
			 * 4004, status 404
			 * >=5000, status 500
			 * else, own status (200)
			 */
			if( APIConstant.UnfoundFail.equals(cbjson.getMeta().getCode()) )
				httpStatus = HttpStatus.NOT_FOUND;
			else if (cbjson.getMeta().getCode() >= APIConstant.UnknowFail)
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			else
				httpStatus = responseEntity.getStatusCode();
			
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			cbjson.setMeta(new ResultMeta(APIConstant.UnknowFail, resultCodeMap.get(APIConstant.UnknowFail)));
		}
		stopWatch.stop();
		log.info("[Controller Log] \t{}.{} \tEND\t{} ms", pjp.getTarget().getClass().getName(), pjp.getSignature().getName(), stopWatch.getTotalTimeMillis());
		//perfStopWatch.stop(pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName());
		
		return ResponseEntity.status(httpStatus).body(cbjson);
	}


}
