package ninja.harmless.nyx.measure;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@Aspect
public class MeasureTimeAspect {

    static final Logger logger = LoggerFactory.getLogger(MeasureTimeAspect.class.getName());

    @Around("@annotation(ninja.harmless.nyx.measure.MeasureTime) && execution(* * (..))")
    public Object measureMethodTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        Object returnObject = null;

        try {
            sw.start();
            returnObject = pjp.proceed();
        } catch (Throwable t) {
            throw t;
        } finally {
            sw.stop();
            logger.info(pjp.toShortString() + "finished in:" + sw.getTime() + " ms.");
        }
        return returnObject;
    }
}
