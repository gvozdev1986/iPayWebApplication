package by.htp.hvozdzeu.web.command.impl.card;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.model.report.SumPaymentReportChartPie;
import by.htp.hvozdzeu.service.IPaymentService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

public class PaymentHistory implements Command {

	private IPaymentService iPaymentService = ServiceFactory.getPaymentService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Long cardId = Long.valueOf(request.getParameter("idCard"));
		LocalDate startDate = LocalDate.parse(request.getParameter("startHistory"));
		LocalDate endDate = LocalDate.parse(request.getParameter("endHistory"));

		List<PaymentReport> payments = iPaymentService.findPaynemtByCardAndBetweenDate(cardId, startDate, endDate);

		List<SumPaymentReportChartPie> sumPaymentReportChartPies = iPaymentService
				.findPaynemtByCardAndBetweenDateChartPie(cardId, startDate, endDate);

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
			
		request.getSession().setAttribute("chartLabel", jsonChartLabel);
		request.getSession().setAttribute("chartData", jsonChartData);
		request.getSession().setAttribute("startDate", startDate);
		request.getSession().setAttribute("endDate", endDate);
		request.getSession().setAttribute("paymentReport", payments);
		return RedirectPageUrl.PAYMENT_HISTORY_VIEW.getUrl();
	}

}
