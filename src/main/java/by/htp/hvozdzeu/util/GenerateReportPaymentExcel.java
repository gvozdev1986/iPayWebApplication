package by.htp.hvozdzeu.util;

import by.htp.hvozdzeu.model.report.PaymentReport;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

public class GenerateReportPaymentExcel {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateReportPaymentExcel.class);

    private GenerateReportPaymentExcel() {
    }

    public static void generateExcelReport(List<PaymentReport> paymentReportList, Long cardId, LocalDate dateStart, LocalDate dateEnd){
        try {
            String filename = "E:/PaymentHistory_" + cardId + ".xls";
            FileOutputStream fileOut;
            try (HSSFWorkbook workbook = new HSSFWorkbook()) {
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

                for (int i = 0; i < paymentReportList.size(); i++) {
                    HSSFRow row = sheet.createRow((short) i + 2);
                    row.createCell(0).setCellValue(i);
                    row.createCell(1).setCellValue(String.valueOf(paymentReportList.get(i).getDatePayment()));
                    row.createCell(2).setCellValue(String.valueOf(paymentReportList.get(i).getTimePayment()));
                    row.createCell(3).setCellValue(paymentReportList.get(i).getDescriptionPayment());
                    row.createCell(4).setCellValue(paymentReportList.get(i).getPaymentDataGroup());
                    row.createCell(5).setCellValue(String.valueOf(paymentReportList.get(i).getAmountPayment()).replace(".", ","));
                }

                fileOut = new FileOutputStream(filename);
                workbook.write(fileOut);
            }
            fileOut.close();
            LOGGER.debug("Your excel file has been generated!");

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
