package by.htp.hvozdzeu.web.command.impl.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

public class PersonalDataView implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {
		System.out.println("PersonalDataView");
		return RedirectPageUrl.PERSONAL_DATA_VIEW.getUrl();
	}

}
