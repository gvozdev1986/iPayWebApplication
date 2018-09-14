package by.htp.hvozdzeu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class DeleteReportFileAfterSending {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteReportFileAfterSending.class);

    private DeleteReportFileAfterSending() {
    }

    public static void deleteFileAfterSending(String fileName) {

        File file = new File(fileName);
        if (file.delete()) {
            LOGGER.debug("{} file has been delete from root path", fileName);
        } else {
            LOGGER.debug("{} file has't been found in root path", fileName);
        }

    }

}
