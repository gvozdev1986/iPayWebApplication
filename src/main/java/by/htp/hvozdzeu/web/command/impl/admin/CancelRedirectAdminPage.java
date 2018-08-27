package by.htp.hvozdzeu.web.command.impl.admin;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.web.command.Command;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelRedirectAdminPage implements Command {

    private static final String FORWARD_ADMIN_PANEL = "/WEB-INF/admin.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {

        response.sendRedirect(FORWARD_ADMIN_PANEL);
        
        return null;
    }
}
