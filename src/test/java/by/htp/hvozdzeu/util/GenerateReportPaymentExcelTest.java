package by.htp.hvozdzeu.util;

import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.service.IPaymentService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.FORMAT_FILE_EXCEL;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.PATH_TO_SAVE_REPORT;

public class GenerateReportPaymentExcelTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateReportPaymentExcelTest.class);
    private IPaymentService iPaymentService = ServiceFactory.getPaymentService();
    private List<PaymentReport> paymentReports;
    private Long cardId;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    @Before
    public void setUp() throws Exception {
        cardId = 17L;
        dateStart = LocalDate.of(2018, 8, 1);
        dateEnd = LocalDate.of(2018, 8, 31);
        paymentReports = iPaymentService.findPaymentByCardAndBetweenDate(cardId, dateStart, dateEnd, 100000000, 0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void generateExcelReport() {
        try {
            String filename = PATH_TO_SAVE_REPORT + cardId + FORMAT_FILE_EXCEL;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Payment report");

            HSSFRow rowHeadTitleReport = sheet.createRow((short) 0);
            CellRangeAddress region = CellRangeAddress.valueOf("A1:F1");
            sheet.addMergedRegion(region);
            rowHeadTitleReport.createCell(0).setCellValue("Payment report from " + dateStart + " to " + dateEnd);

            HSSFRow rowHead = sheet.createRow((short) 1);
            rowHead.createCell(0).setCellValue("No.");
            rowHead.createCell(1).setCellValue("Date");
            rowHead.createCell(2).setCellValue("Time");
            rowHead.createCell(3).setCellValue("Brief about payment");
            rowHead.createCell(4).setCellValue("Group");
            rowHead.createCell(5).setCellValue("Amount");

            for (int i = 0; i < paymentReports.size(); i++) {
                HSSFRow row = sheet.createRow((short) i + 1);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(String.valueOf(paymentReports.get(i).getDatePayment()));
                row.createCell(2).setCellValue(String.valueOf(paymentReports.get(i).getTimePayment()));
                row.createCell(3).setCellValue(paymentReports.get(i).getDescriptionPayment());
                row.createCell(4).setCellValue(paymentReports.get(i).getPaymentDataGroup());
                row.createCell(5).setCellValue(String.valueOf(paymentReports.get(i).getAmountPayment()).replace(".", ","));
            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}