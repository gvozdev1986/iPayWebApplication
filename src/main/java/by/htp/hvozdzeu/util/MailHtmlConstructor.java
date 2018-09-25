package by.htp.hvozdzeu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * The class for build email imposition
 */
public class MailHtmlConstructor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailHtmlConstructor.class);
    private static final String INIT_IMPOSITION_PARAMETER = "imposition";
    private static String imposition;

    /**
     * Private constructor
     */
    private MailHtmlConstructor() {
    }

    /**
     * The method for collect email with imposition.
     * @param request HttpServletRequest request
     * @param lastName String last name client
     * @param firstName String first name client
     * @param middleName String middle name client
     * @param message String message for client
     * @return String collected email with client data and message
     * @throws IOException Exception
     */
    public static String mailConstructor(HttpServletRequest request, String lastName, String firstName, String middleName, String message) throws IOException {

        ServletContext servletContext = request.getServletContext();
        String fileName = servletContext.getInitParameter(INIT_IMPOSITION_PARAMETER);
        InputStream stream = servletContext.getResourceAsStream(fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            LOGGER.debug("Create imposition for email.");

            imposition = sb.toString();
            imposition = imposition.replace("<last_name>", lastName)
                    .replace("<first_name>", firstName)
                    .replace("<middle_name>", middleName)
                    .replace("<message>", message);

        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        return imposition;

    }

}
