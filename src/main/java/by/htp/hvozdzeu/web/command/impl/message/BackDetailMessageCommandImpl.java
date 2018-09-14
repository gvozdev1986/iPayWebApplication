package by.htp.hvozdzeu.web.command.impl.message;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.PagePathConstantPool.REDIRECT_MESSAGE_DETAIL;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.MESSAGE_ID;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.PART_URL_PARAMETER;

public class BackDetailMessageCommandImpl implements BaseCommand {

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        String messageId = request.getParameter(MESSAGE_ID);

        return REDIRECT_MESSAGE_DETAIL + PART_URL_PARAMETER + messageId;

    }
}
