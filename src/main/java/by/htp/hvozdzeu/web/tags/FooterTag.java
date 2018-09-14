package by.htp.hvozdzeu.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.htp.hvozdzeu.resources.Resource;

public class FooterTag extends TagSupport {
	
	private static final long serialVersionUID = 6041995730674859548L;
	
	private static final String FOOTER = "footer";
	
	@Override
	public int doStartTag() throws JspException {

		try{
			JspWriter out = pageContext.getOut();
			out.write(Resource.getStr(FOOTER));
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		
		return super.doStartTag();
	}
	
}
