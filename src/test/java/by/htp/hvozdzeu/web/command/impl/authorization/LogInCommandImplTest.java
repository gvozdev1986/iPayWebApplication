package by.htp.hvozdzeu.web.command.impl.authorization;

import by.htp.hvozdzeu.service.UserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class LogInCommandImplTest {

    private HttpServletRequest req = mock(HttpServletRequest.class);
    private HttpServletResponse resp = mock(HttpServletResponse.class);
    private String url;

    @Mock
    private UserService userService = ServiceFactory.getUserService();

    @Before
    public void setUp() {
        url = "/ServletController?command=client_panel_view";
    }

    @Test
    public void executeCommand() throws IOException{
        req.setAttribute("username", "client");
        req.setAttribute("password", "client2525");
        req.setAttribute("command", "authorization_user");

        resp.sendRedirect(url);
    }
}