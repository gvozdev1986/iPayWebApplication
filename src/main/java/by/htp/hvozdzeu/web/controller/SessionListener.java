package by.htp.hvozdzeu.web.controller;

import by.htp.hvozdzeu.web.command.impl.view.GreetingPageViewCommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingPageViewCommandImpl.class);

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        LOGGER.debug("Start session destroy");
        redirectGreetingPage();
    }

    private void redirectGreetingPage(){
        LOGGER.debug("Session has been destroyed. Redirect on greeting page");
    }

}
