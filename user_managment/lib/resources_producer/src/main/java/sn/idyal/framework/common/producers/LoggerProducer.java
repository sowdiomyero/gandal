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
/**
 * User Story Implemented here : BEL-46
 */
public class LoggerProducer {
    /**
     * La production de ressources avec CDI
     * @param injectionPoint
     * @return
     */

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {

        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
        /**
         * Ici l'usager ne connait que l'inerface du logger. SI nous changions d'implementation, que nous choisissions log4J par exemple comme API,
         * alors notre code ne change que à ce seul endroit.
         */
    }
}
