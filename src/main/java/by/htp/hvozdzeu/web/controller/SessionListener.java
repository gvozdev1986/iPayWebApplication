package by.htp.hvozdzeu.web.controller;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.command.impl.view.GreetingPageViewCommandImpl;
import by.htp.hvozdzeu.web.exception.CommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener, BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingPageViewCommandImpl.class);

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        LOGGER.debug("Start session destroy");
        redirectGreetingPage();
    }

    private void redirectGreetingPage(){
        LOGGER.debug("Session has been destroyed. Redirect on greeting page");
    }

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {
        return null;
    }
}
