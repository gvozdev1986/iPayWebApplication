package by.htp.hvozdzeu.web.command.impl.user;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class CheckNewAccountCommandImpl implements BaseCommand {

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        String checkCode = request.getParameter("checkCode");

        System.out.println("########" + checkCode);

        return PagePathConstantPool.REDIRECT_CHECK_ACCOUNT_VIEW;
    }

}
