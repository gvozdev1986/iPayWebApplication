package by.htp.hvozdzeu.web.controller;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.htp.hvozdzeu.web.util.PagePathConstantPool.*;
import static by.htp.hvozdzeu.web.util.RedirectHelper.getRedirectUrl;

public class ServletController extends HttpServlet {

    private static final long serialVersionUID = -4731601876679277122L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("SERVLET doGET");
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("SERVLET doPOST");
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BaseCommand requestCommand = CommandFactory.defineCommand(request);
        String path;
        try {
            path = requestCommand.executeCommand(request);
            if (path.contains("checkCode")) {
                LOGGER.debug("SERVLET REDIRECT ON CHECK NEW ACCOUNT");
                response.sendRedirect(path);
            } else if (path.contains("messageId")) {
                LOGGER.debug("SERVLET REDIRECT ON MESSAGE DETAIL");
                response.sendRedirect(path);
            } else if (getRedirectUrl(path)) {
                LOGGER.debug("SERVLET REDIRECT");
                response.sendRedirect(request.getContextPath() + path);
            } else {
                LOGGER.debug("SERVLET FORWARD");
                request.getRequestDispatcher(path).forward(request, response);
            }
        } catch (CommandException e) {
            LOGGER.debug("SERVLET PAGE ERROR. {}", e.getMessage());
            request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
            LOGGER.error(e.getMessage(), e);
        }
    }

}
