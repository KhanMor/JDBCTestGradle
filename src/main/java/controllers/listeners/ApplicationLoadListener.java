package controllers.listeners;

import common.exceptions.DAOException;
import common.utils.LectionNotifyer;
import models.Lection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import services.servicesimpl.LectionService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * Created by Mordr on 24.02.2017.
 */
@WebListener
public class ApplicationLoadListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(ServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(60*60*100);
                        asd();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    @Autowired
    LectionService lectionService;

    private void asd() {
        List<Lection> lections = null;
        try {
            lections = lectionService.getNeariestLections();
            if(!lections.isEmpty()) {
                for(Lection lection:lections) {
                    LectionNotifyer lectionNotifyer = new LectionNotifyer();
                    lectionNotifyer.notyfy(lection);
                }
            } else {
                logger.trace("nearieest lection not found");
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.trace("site stop");
    }
}
