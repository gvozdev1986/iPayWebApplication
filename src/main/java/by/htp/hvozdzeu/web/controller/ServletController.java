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
    private static final String CHECK_CODE = "checkCode";
    private static final String MESSAGE_ID = "messageId";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            LOGGER.debug("Servlet method doGET");
            process(request, response);
        } catch (IOException | ServletException e) {
            LOGGER.error("Error in process method {}", e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            LOGGER.debug("Servlet method doPOST");
            process(request, response);
        } catch (IOException | ServletException e) {
            LOGGER.error("Error in process method {}", e.getMessage());
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BaseCommand requestCommand = CommandFactory.defineCommand(request);
        String path;
        try {
            path = requestCommand.executeCommand(request);
            if (path.contains(CHECK_CODE)) {
                LOGGER.debug("Servlet redirect on check new account");
                response.sendRedirect(path);
            } else if (path.contains(MESSAGE_ID)) {
                LOGGER.debug("Servlet redirect on message detail");
                response.sendRedirect(path);
            } else if (getRedirectUrl(path)) {
                LOGGER.debug("Servlet redirect on {}", path);
                response.sendRedirect(request.getContextPath() + path);
            } else {
                LOGGER.debug("Servlet forward on {}", path);
                request.getRequestDispatcher(path).forward(request, response);
            }
        } catch (CommandException e) {
            LOGGER.debug("Servlet page error: {}", e.getMessage());
            request.getRequestDispatcher(PAGE_ACCESS_DENIED).forward(request, response);
        }
    }

}
