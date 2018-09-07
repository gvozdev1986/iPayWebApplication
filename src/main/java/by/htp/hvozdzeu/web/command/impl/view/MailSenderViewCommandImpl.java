package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.PagePathConstantPool.MAIL_SENDER_VIEW;

public class MailSenderViewCommandImpl implements BaseCommand {
    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {
        return MAIL_SENDER_VIEW;
    }
}
