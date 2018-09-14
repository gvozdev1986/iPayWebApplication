package by.htp.hvozdzeu.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.htp.hvozdzeu.resources.Resource;

public class AboutTag extends TagSupport {

    private static final long serialVersionUID = -4112416812723727444L;

    private static final String ABOUT = "about";

    @Override
    public int doStartTag() throws JspException {

        try {
            JspWriter out = pageContext.getOut();
            out.write(Resource.getStr(ABOUT));
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return super.doStartTag();
    }


}
