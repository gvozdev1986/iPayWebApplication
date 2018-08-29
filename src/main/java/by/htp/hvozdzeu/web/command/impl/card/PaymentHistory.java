package by.htp.hvozdzeu.web.command.impl.card;

import static by.htp.hvozdzeu.web.pagination.CalcilatePagination.*;
import static by.htp.hvozdzeu.web.pagination.CalcilatePagination.calculatPagination;
import static by.htp.hvozdzeu.web.pagination.NavigationEvent.*;
import static by.htp.hvozdzeu.web.pagination.PaginationDots.paginationDots;
import static by.htp.hvozdzeu.web.util.RequestAttributeEntity.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.model.report.SumPaymentReportChartPie;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.IPaymentService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

public class PaymentHistory implements Command {

	private IPaymentService iPaymentService = ServiceFactory.getPaymentService();
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Long cardId = Long.valueOf(request.getParameter("idCard"));
		LocalDate startDate = LocalDate.parse(request.getParameter("startHistory"));
		LocalDate endDate = LocalDate.parse(request.getParameter("endHistory"));
		
		CreditCard creditCards = iCreditCardService.findById(cardId);
		String cardNumber = creditCards.getCardNumber();
		
		Integer countRow = iPaymentService.read().size();

		Map<String, Integer> calculatePagination = calculatPagination(request, countRow);
		Integer page = calculatePagination.get(PAGE);
		Integer countPage = calculatePagination.get(COUNT_PAGE);
		Integer countRowOnPage = calculatePagination.get(COUNT_ROW_ON_PAGE);
		Integer displacement = calculatePagination.get(DISPLACEMENT);
		Integer lastPage = calculatePagination.get(LAST_PAGE);

		List<String> paginationBtns = paginationDots(page, countPage);
		List<PaymentReport> pagination = iPaymentService.findPaynemtByCardAndBetweenDate(cardId, startDate, endDate, countRowOnPage, displacement);
		
		List<SumPaymentReportChartPie> sumPaymentReportChartPies = iPaymentService.findPaynemtByCardAndBetweenDateChartPie(cardId, startDate, endDate, countRowOnPage, displacement);

		String[] chartLabel = new String[sumPaymentReportChartPies.size()];
		for (int l = 0; l < sumPaymentReportChartPies.size(); l++) {
			chartLabel[l] = sumPaymentReportChartPies.get(l).getGroup();
		}

		BigDecimal[] chartData = new BigDecimal[sumPaymentReportChartPies.size()];
		for (int d = 0; d < sumPaymentReportChartPies.size(); d++) {
			chartData[d] = sumPaymentReportChartPies.get(d).getSum();
		}

		String jsonChartLabel = new Gson().toJson(chartLabel);
		String jsonChartData = new Gson().toJson(chartData);
		
		request.getSession().setAttribute(PREVIOUS_BUTTON, PREVIOUS_BUTTON);
		request.getSession().setAttribute(NEXT_BUTTON, NEXT_BUTTON);
		request.getSession().setAttribute(PAGE, page);
		request.getSession().setAttribute(FIRST_PAGE, FIRST_PAGE_NAME_VALUE);
		request.getSession().setAttribute(LAST_PAGE, lastPage);
		request.getSession().setAttribute(COUNT_ROW_ON_PAGE, countRowOnPage);
		request.getSession().setAttribute(COUNT_PAGE, countPage);
		request.getSession().setAttribute(REQUEST_ATTRIBUTE_CREDIT_CARD, pagination);
		request.getSession().setAttribute(PAGINATION_BUTTON_ARRAY_ATTIRIBURE_NAME, paginationBtns);

		request.getSession().setAttribute("searchedCardNumber", cardNumber);
		request.getSession().setAttribute("searchedCardId", cardId);
		request.getSession().setAttribute("chartLabel", jsonChartLabel);
		request.getSession().setAttribute("chartData", jsonChartData);
		request.getSession().setAttribute("startDate", startDate);
		request.getSession().setAttribute("endDate", endDate);
		request.getSession().setAttribute("paymentReport", pagination);
		return RedirectPageUrl.PAYMENT_HISTORY_VIEW.getUrl();
	}
	
}
