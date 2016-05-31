package scapi.common.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.perf4j.LoggingStopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component("otherIntercepter")
@Slf4j
public class OtherIntercepter {

	public Object servicesPointCutMethod(ProceedingJoinPoint pjp) throws Throwable {
		return logMethod(pjp,"SERVICE");
	}

	public Object repositoryPointCutMethod(ProceedingJoinPoint pjp) throws Throwable {
		return logMethod(pjp,"DAO");
	}
	
	public Object logMethod(ProceedingJoinPoint pjp,String logType) throws Throwable{
		//LoggingStopWatch perfStopWatch = null;
	  	//perfStopWatch = new Log4JStopWatch(org.apache.log4j.Logger.getLogger(Log4JStopWatch.class));
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		String args = "";
		Object[] signatureArgs = pjp.getArgs();
		for (Object signatureArg : signatureArgs) {
			args += signatureArg + ",";
		}

		args = args.length() > 0 ? args.substring(0,args.length() - 1) : args;
		log.info("[{} Log] \t{}.{} \tSTART : [{}]",logType, pjp.getTarget().getClass().getName(),pjp.getSignature().getName(),args );
		Object cbObj = pjp.proceed();
		stopWatch.stop();
		log.info("[{} Log] \t{}.{} \tEND\t{} ms",logType, pjp.getTarget().getClass().getName(),pjp.getSignature().getName(),stopWatch.getTotalTimeMillis());
		//perfStopWatch.stop(pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName());
		return cbObj;
	}
	
}
