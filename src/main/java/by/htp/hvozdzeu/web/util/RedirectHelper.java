package by.htp.hvozdzeu.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import static by.htp.hvozdzeu.web.util.PagePathConstantPool.*;

public final class RedirectHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedirectHelper.class);
    private static Set<String> redirectSet = new HashSet<>();

    private RedirectHelper() {
    }

    static {
        redirectSet.add(REDIRECT_PAYMENT_DATA_LIST);
        redirectSet.add(REDIRECT_ADMIN_URL);
        redirectSet.add(REDIRECT_USER_URL);
        redirectSet.add(REDIRECT_GUEST_URL);
        redirectSet.add(REDIRECT_REGISTRATION_FORM);
        redirectSet.add(REDIRECT_LIST_CARD_CLIENT);
        redirectSet.add(REDIRECT_PERSONAL_DATA_VIEW);
        redirectSet.add(REDIRECT_SAVE_PAY_PAYMENT);
        redirectSet.add(REDIRECT_SAVE_TRANSFER);
        redirectSet.add(REDIRECT_UPDATE_CLIENT_PSWD);
        redirectSet.add(REDIRECT_LIST_MESSAGE);
    }

    public static boolean getRedirectUrl(String path){
        LOGGER.debug("Getting URL: {}", path);
        return redirectSet.contains(path);
    }

}
