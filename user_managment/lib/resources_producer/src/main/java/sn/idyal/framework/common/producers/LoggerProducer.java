package sn.idyal.framework.common.producers;


import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: sowdiomyero
 * Date: 03/06/14
 * Time: 10:21
 * To change this template use File | Settings | File Templates.
 */
public class LoggerProducer {
    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
