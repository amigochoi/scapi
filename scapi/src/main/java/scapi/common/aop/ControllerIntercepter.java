package scapi.common.aop;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.perf4j.LoggingStopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import scapi.model.display.ResultJson;

@Component("controllerIntercepter")
@Slf4j
public class ControllerIntercepter {
	// remark
	// for API request only with ResultJson
	@Value("${project.environment}")
	String env;

	public ResultJson controllerPointCutMethod(ProceedingJoinPoint pjp) throws Throwable {
		return logMethod(pjp, "CONTROLLER", 5000);
	}

	public ResultJson logMethod(ProceedingJoinPoint pjp, String logType, long slowLimitTime) throws Throwable {
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
		try {
			cbjson = (ResultJson) pjp.proceed();
			cbjson.setResultCode(0);
			cbjson.setSuccess(true);
		} catch (Exception ex) {
			log.error("exception",ex);
			// success callback AOP
			cbjson.setResultCode(-1);
			cbjson.setSuccess(false);
		}finally{
			cbjson.setResponseDateTime(new Date());
			cbjson.setResponseServer(env);
		}
		stopWatch.stop();
		log.info("[{} Log] \t{}.{} \tEND\t{} ms{}", logType, pjp.getTarget().getClass().getName(), pjp.getSignature().getName(), stopWatch.getTotalTimeMillis(),
				(stopWatch.getTotalTimeMillis() > slowLimitTime ? "SLOW" : ""));
		perfStopWatch.stop(pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName());
		return cbjson;
	}

}
