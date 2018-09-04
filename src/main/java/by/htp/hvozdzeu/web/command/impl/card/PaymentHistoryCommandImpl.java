package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.model.report.SumPaymentReportChartPie;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.IPaymentService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static by.htp.hvozdzeu.web.pagination.CalculatePagination.*;
import static by.htp.hvozdzeu.web.pagination.NavigationEvent.NEXT_BUTTON;
import static by.htp.hvozdzeu.web.pagination.NavigationEvent.PREVIOUS_BUTTON;
import static by.htp.hvozdzeu.web.pagination.PaginationDots.paginationDots;

public class PaymentHistoryCommandImpl implements BaseCommand {

	private IPaymentService iPaymentService = ServiceFactory.getPaymentService();
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Long cardId = Long.valueOf(request.getParameter("idCard"));
		LocalDate startDate = LocalDate.parse(request.getParameter("startHistory"));
		LocalDate endDate = LocalDate.parse(request.getParameter("endHistory"));
		
		CreditCard creditCards = iCreditCardService.findById(cardId);
		String cardNumber = creditCards.getCardNumber();
		
		Integer countRow = iPaymentService.read().size();

		Map<String, Integer> calculatePagination = calculatePagination(request, countRow);
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
		request.getSession().setAttribute("creditCards", pagination);
		request.getSession().setAttribute(PAGINATION_BUTTON_ARRAY_ATTRIBUTE_NAME, paginationBtns);

		request.getSession().setAttribute("searchedCardNumber", cardNumber);
		request.getSession().setAttribute("searchedCardId", cardId);
		request.getSession().setAttribute("chartLabel", jsonChartLabel);
		request.getSession().setAttribute("chartData", jsonChartData);
		request.getSession().setAttribute("startDate", startDate);
		request.getSession().setAttribute("endDate", endDate);
		request.getSession().setAttribute("paymentReport", pagination);
		return PagePathConstantPool.PAYMENT_HISTORY_VIEW;
	}
	
}
