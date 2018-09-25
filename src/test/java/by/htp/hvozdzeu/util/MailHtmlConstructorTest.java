package by.htp.hvozdzeu.util;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MailHtmlConstructorTest {

    private String imposition;

    @Test
    public void mailConstructor() {

        try (BufferedReader br = new BufferedReader(new FileReader( "src/main/resources/mail/imposition.html"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            imposition = sb.toString();
            imposition = imposition.replace("<lastname>", "Hvozdzeu")
                    .replace("<firstname>", "Aliaksandr")
                    .replace("<midlname>", "Nikolaevich")
                    .replace("<message>", "TEST MESSAGE");


        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(imposition);
        assertTrue(imposition.contains("Hvozdzeu"));
        assertTrue(imposition.contains("Aliaksandr"));
        assertTrue(imposition.contains("Nikolaevich"));
        assertTrue(imposition.contains("TEST MESSAGE"));

    }
}