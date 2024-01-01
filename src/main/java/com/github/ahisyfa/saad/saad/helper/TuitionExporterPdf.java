package com.github.ahisyfa.saad.saad.helper;

import com.github.ahisyfa.saad.saad.entity.Tuition;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.CollectionUtils;


import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * TuitionExporterPdf
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: StudentExporterPdf.java, v 0.1 2023-07-22  10.43 Ahmad Isyfalana Amin Exp $
 */
public class TuitionExporterPdf {

    public static int   BODY_FONT_FAMILY    = Font.TIMES_ROMAN;
    public static int   BODY_FONT_SIZE      = 12;
    public static float BODY_CELL_PADDING   = 3f;

    public static Font  BODY_FONT           = new Font(BODY_FONT_FAMILY, BODY_FONT_SIZE);
    public static Font  BODY_FONT_BOLD      = new Font(BODY_FONT_FAMILY, BODY_FONT_SIZE, Font.BOLD);

    public static void export(String title, List<Tuition> tuitions, HttpServletResponse httpServletResponse) throws IOException {
        Locale locale = new Locale("in", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        formatter.setParseIntegerOnly(true);
        formatter.setMaximumFractionDigits(0);

        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, httpServletResponse.getOutputStream());

        document.open();

        Paragraph paragraphTitle = new Paragraph(title, new Font(Font.TIMES_ROMAN, 16, Font.BOLD));
        paragraphTitle.setAlignment(Element.ALIGN_CENTER);
        paragraphTitle.setSpacingAfter(10);

        document.add(paragraphTitle);

        PdfPTable table = new PdfPTable(6);
        table.setSpacingBefore(5);
        table.setWidthPercentage(90);
        table.setWidths(new int[]{1,2,4,2,2,2});

        writeTableHeader(table);
        if (!CollectionUtils.isEmpty(tuitions)) {
            Long totalAmount = 0L;

            for (int i = 0; i < tuitions.size(); i++) {
                writeTableBody(table, i+1, tuitions.get(i), formatter);
                totalAmount += tuitions.get(i).getAmount();
            }

            PdfPCell totalCell = new PdfPCell(new Phrase("TOTAL", BODY_FONT_BOLD));
            totalCell.setColspan(5);
            totalCell.setPadding(BODY_CELL_PADDING);
            totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(totalCell);

            PdfPCell totalCellNominal = new PdfPCell(new Phrase(formatter.format(totalAmount), BODY_FONT_BOLD));
            totalCellNominal.setPadding(BODY_CELL_PADDING);
            totalCellNominal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalCellNominal);
        }

        document.add(table);

        document.close();
    }

    private static void writeTableHeader(PdfPTable table) {
        PdfPCell cell1 = new PdfPCell(new Phrase("No.", BODY_FONT_BOLD));
        cell1.setPadding(BODY_CELL_PADDING);

        PdfPCell cell2 = new PdfPCell(new Phrase("No. Kwitansi", BODY_FONT_BOLD));
        cell2.setPadding(BODY_CELL_PADDING);

        PdfPCell cell3 = new PdfPCell(new Phrase("Nama Siswa", BODY_FONT_BOLD));
        cell3.setPadding(BODY_CELL_PADDING);

        PdfPCell cell4 = new PdfPCell(new Phrase("Periode", BODY_FONT_BOLD));
        cell4.setPadding(BODY_CELL_PADDING);

        PdfPCell cell5 = new PdfPCell(new Phrase("Tanggal Bayar", BODY_FONT_BOLD));
        cell5.setPadding(BODY_CELL_PADDING);

        PdfPCell cell6 = new PdfPCell(new Phrase("Nominal", BODY_FONT_BOLD));
        cell6.setPadding(BODY_CELL_PADDING);
        cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);


        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
    }

    private static void writeTableBody(PdfPTable table, int index, Tuition tuition, NumberFormat formatter) {
        PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(index), BODY_FONT));
        cell1.setPadding(BODY_CELL_PADDING);

        PdfPCell cell2 = new PdfPCell(new Phrase(String.format("%06d", tuition.getId()), BODY_FONT));
        cell2.setPadding(BODY_CELL_PADDING);

        PdfPCell cell3 = new PdfPCell(new Phrase(tuition.getStudent().getFullName(), BODY_FONT));
        cell3.setPadding(BODY_CELL_PADDING);

        PdfPCell cell4 = new PdfPCell(new Phrase(tuition.getPeriod().toString(), BODY_FONT));
        cell4.setPadding(BODY_CELL_PADDING);

        PdfPCell cell5 = new PdfPCell(new Phrase(tuition.getPaymentDate().toString(), BODY_FONT));
        cell5.setPadding(BODY_CELL_PADDING);

        PdfPCell cell6 = new PdfPCell(new Phrase(formatter.format(tuition.getAmount()), BODY_FONT));
        cell6.setPadding(BODY_CELL_PADDING);
        cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
    }
}