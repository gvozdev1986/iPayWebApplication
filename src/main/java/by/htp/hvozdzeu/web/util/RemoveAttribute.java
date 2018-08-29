package by.htp.hvozdzeu.web.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveAttribute {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoveAttribute.class);

	public static void removeAttribute(HttpServletRequest request, List<String> attributes) {

		LOGGER.info("Start remove attributes");

		for (int i = 0; i < attributes.size(); i++) {
			LOGGER.info("Remove attribute name {}", attributes.get(i));
			request.getSession().removeAttribute(attributes.get(i));
		}

		LOGGER.info("Stop remove attributes");

	}

}
