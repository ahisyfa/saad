package com.github.ahisyfa.saad.saad.helper;

import com.github.ahisyfa.saad.saad.entity.Student;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * StudentExporterPdf
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: StudentExporterPdf.java, v 0.1 2023-07-22  10.43 Ahmad Isyfalana Amin Exp $
 */
public class StudentExporterPdf {

    public static void export(List<Student> students, HttpServletResponse httpServletResponse) throws IOException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, httpServletResponse.getOutputStream());

        document.open();

        document.add(new Paragraph("Student List"));
        document.add(new LineSeparator());

        PdfPTable table = new PdfPTable(6);
        table.setHorizontalAlignment(0);
        table.setWidthPercentage(100);

        writeTableHeader(table);
        if (!CollectionUtils.isEmpty(students)) {
            for (int i = 0; i < students.size(); i++) {
                writeTableBody(table, i+1, students.get(i));
            }
        }

        document.add(table);

        document.close();
    }

    public static void writeTableHeader(PdfPTable table) {
        table.addCell(new Phrase("No"));
        table.addCell(new Phrase("Full Name"));
        table.addCell(new Phrase("Gender"));
        table.addCell(new Phrase("Birth Place"));
        table.addCell(new Phrase("Birth Date"));
        table.addCell(new Phrase("Registration Year"));
    }

    public static void writeTableBody(PdfPTable table, int index, Student student) {
        table.addCell(new Phrase(String.valueOf(index)));
        table.addCell(new Phrase(student.getFullName()));
        table.addCell(new Phrase(student.getGender().toString()));
        table.addCell(new Phrase(student.getBirthPlace()));
        table.addCell(new Phrase(student.getBirthDate().toString()));
        table.addCell(new Phrase(student.getRegistrationYear().toString()));
    }
}