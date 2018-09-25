package by.htp.hvozdzeu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class MailHtmlConstructor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailHtmlConstructor.class);
    private static final String INIT_IMPOSITION_PARAMETER = "imposition";
    private static String imposition;

    private MailHtmlConstructor() {
    }

    public static String mailConstructor(HttpServletRequest request, String lastName, String firstName, String middleName, String message) throws IOException {

        ServletContext servletContext = request.getServletContext();
        String fileName = servletContext.getInitParameter(INIT_IMPOSITION_PARAMETER);
        InputStream stream = servletContext.getResourceAsStream(fileName);
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(stream))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            LOGGER.debug("Create imposition for email.");

            imposition = sb.toString();
            imposition = imposition.replace("<lastname>", lastName)
                    .replace("<firstname>", firstName)
                    .replace("<midlname>", middleName)
                    .replace("<message>", message);

        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        return imposition;

    }

}
