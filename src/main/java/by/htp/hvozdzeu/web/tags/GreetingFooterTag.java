package by.htp.hvozdzeu.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.htp.hvozdzeu.resources.ResourceManager;

public class GreetingFooterTag extends TagSupport {

	private static final long serialVersionUID = 6571952549953079433L;

	private static final String GREETING_FOOTER = "greeting_footer";

	@Override
	public int doStartTag() throws JspException {

		try {
			JspWriter out = pageContext.getOut();
			out.write(ResourceManager.getStr(GREETING_FOOTER));
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}

		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

}
