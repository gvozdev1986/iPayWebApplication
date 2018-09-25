package by.htp.hvozdzeu.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MailHtmlConstructorTest {

    @Mock
    HttpServletRequest request;

    @Mock
    ServletContext servletContext;

    private String imposition;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mailConstructor() {

        ServletContext servletContext = request.getServletContext();
        String fileName = servletContext.getInitParameter("imposition");
        InputStream stream = servletContext.getResourceAsStream(fileName);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            imposition = sb.toString();
            imposition = imposition.replace("<last_name>", "Hvozdzeu")
                    .replace("<first_name>", "Aliaksandr")
                    .replace("<middle_name>", "Nikolaevich")
                    .replace("<message>", "MESSAGE");


        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(imposition);
        assertTrue(imposition.contains("Hvozdzeu"));
        assertTrue(imposition.contains("Aliaksandr"));
        assertTrue(imposition.contains("Mikolaevich"));
        assertTrue(imposition.contains("MESSAGE"));

    }

}