package controllers.listeners;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Mordr on 24.02.2017.
 */
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    private static final Logger logger = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //logger.trace(se.getSession().getAttribute("user"));
        logger.trace(se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if("login".equals(event.getName())){

        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}
