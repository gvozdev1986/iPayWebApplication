package by.htp.hvozdzeu.web.command.impl.redirects;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class RedirectAdminPanelCommandImpl implements BaseCommand {
    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {
        return PagePathConstantPool.REDIRECT_ADMIN_URL;
    }
}
