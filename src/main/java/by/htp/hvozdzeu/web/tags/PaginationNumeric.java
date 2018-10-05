package by.htp.hvozdzeu.web.tags;

import by.htp.hvozdzeu.resources.Resource;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationNumeric extends TagSupport {

    private static final long serialVersionUID = -4112416812723727444L;

    private static final String PAGINATION_NUMERIC = "pagination_numeric";

    @Override
    public int doStartTag() throws JspException {

        try {
            JspWriter out = pageContext.getOut();
            out.write(Resource.getStr(PAGINATION_NUMERIC));
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return super.doStartTag();
    }


}
