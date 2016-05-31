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
	// remark
	// for API request only with ResultJson
	@Value("${project.environment}")
	String env;
	
	@Resource(name="resultCodeMap")
	HashMap<Integer, String> resultCodeMap;
	
	public ResponseEntity<ResultJson> controllerPointCutMethod(ProceedingJoinPoint pjp) throws Throwable {
		return logMethod(pjp, "CONTROLLER", 5000);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<ResultJson> logMethod(ProceedingJoinPoint pjp, String logType, long slowLimitTime) throws Throwable {
		LoggingStopWatch perfStopWatch = null;
		perfStopWatch = new Log4JStopWatch(org.apache.log4j.Logger.getLogger(Log4JStopWatch.class));
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		/* 
		 * String args = ""; Object[] signatureArgs = pjp.getArgs(); for (Object
		 * signatureArg : signatureArgs) { args += signatureArg + ","; }
		 * 
		 * args = args.length() > 0 ? args.substring(0,args.length() - 1) :
		 * args;
		 */
		log.info("[{} Log] \t{}.{} \tSTART : [{}]", logType, pjp.getTarget().getClass().getName(), pjp.getSignature().getName());
		
		ResultJson cbjson = new ResultJson();
		HttpStatus httpStatus;
		try {
			ResponseEntity<ResultJson> responseEntity = (ResponseEntity<ResultJson>) pjp.proceed();
			
			cbjson = responseEntity.getBody();
			if(cbjson!= null && cbjson.getMeta() != null && cbjson.getMeta().getResultMessage() == null){
				cbjson.setMeta(new ResultMeta(cbjson.getMeta().getResultCode(), resultCodeMap.get(cbjson.getMeta().getResultCode()),cbjson.getMeta().getResultErrors() ));
			}
			httpStatus = responseEntity.getStatusCode();
		} catch (Exception ex) {
			log.error("EXCEPTION",ex);
			httpStatus = HttpStatus.BAD_REQUEST;
			cbjson.setMeta(new ResultMeta(APIConstant.UnknowFail, resultCodeMap.get(APIConstant.UnknowFail)));
		}
		stopWatch.stop();
		log.info("[{} Log] \t{}.{} \tEND\t{} ms{}", logType, pjp.getTarget().getClass().getName(), pjp.getSignature().getName(), stopWatch.getTotalTimeMillis(),
				(stopWatch.getTotalTimeMillis() > slowLimitTime ? "SLOW" : ""));
		perfStopWatch.stop(pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName());
		
		return ResponseEntity.status(httpStatus).body(cbjson);
	}

}
