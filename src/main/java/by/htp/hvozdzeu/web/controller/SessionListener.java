package by.htp.hvozdzeu.web.controller;

import by.htp.hvozdzeu.web.command.impl.view.GreetingPageViewCommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingPageViewCommandImpl.class);

    @Override
    public void sessionCreated(final HttpSessionEvent event) {
        LOGGER.debug("Session has been created.");
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        LOGGER.debug("Session has been destroyed.");
    }

}
