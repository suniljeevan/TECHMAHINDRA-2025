package com.university.result_management.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.university.result_management.models.Result;
import com.university.result_management.models.Student;

@Service
public class PdfGeneratorService {

    public ByteArrayInputStream generateGradeCard(Student student, List<Result> results, int semester, double sgpa, double cgpa, int totalCredits) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);

        // === ADD LOGO ===
        String logoPath = "src/main/resources/static/images/logo.png";
        ImageData logoData = ImageDataFactory.create(logoPath);
        Image logo = new Image(logoData).scaleToFit(550, 350); // Increased the size of the logo
        
        // Calculate the position to center the logo
        float pageWidth = pdf.getDefaultPageSize().getWidth();
        float logoWidth = logo.getImageScaledWidth();
        float xPosition = (pageWidth - logoWidth) / 2; // Center horizontally
        logo.setFixedPosition(xPosition, pdf.getDefaultPageSize().getHeight()-80); // Top-center, adjust as needed
        doc.add(logo);

     // === ADD SPACE BELOW THE LOGO ===
     doc.add(new Paragraph("\n\n\n\n")); // Add more space below logo

     // === TITLE & HEADER ===
     Paragraph title = new Paragraph("GRADE CARD")
             .setTextAlignment(TextAlignment.CENTER)
             .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
             .setFontSize(20);
     doc.add(title);

     // === ADD WATERMARK TO ALL PAGES (after doc.add(title) ensures title doesn't overlap) ===
     int totalPages = pdf.getNumberOfPages();
     for (int i = 1; i <= totalPages; i++) {
         PdfCanvas canvas = new PdfCanvas(pdf.getPage(i));
         canvas.saveState();
         Canvas watermark = new Canvas(canvas, pdf.getDefaultPageSize());
         Paragraph watermarkText = new Paragraph("PROVISIONAL")
                 .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                 .setFontSize(60)
                 .setFontColor(ColorConstants.LIGHT_GRAY)
                 .setRotationAngle(Math.toRadians(45))
                 .setOpacity(0.2f);
         watermark.showTextAligned(watermarkText, 297, 420, TextAlignment.CENTER);
         watermark.close();
         canvas.restoreState();
     }


        // === TITLE & HEADER ===
       

        // === ADD ADDITIONAL INFO ===
        doc.add(new Paragraph("End Term Final Examinations").setTextAlignment(TextAlignment.CENTER).setFontSize(12).setBold());
        doc.add(new Paragraph("Semester: " + semester).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        doc.add(new Paragraph("\n"));

        // === STUDENT INFO ===
        doc.add(new Paragraph("Student Name: " + student.getName()));
        doc.add(new Paragraph()
        	    .add(new Text("Roll Number: ").setBold())
        	    .add(new Text(student.getRollNumber())));

        	doc.add(new Paragraph()
        	    .add(new Text("Department: ").setBold())
        	    .add(new Text(student.getDepartment().getName())));
        doc.add(new Paragraph("\n"));

        // === TABLE ===
        Table table = new Table(UnitValue.createPercentArray(new float[]{3, 6, 2, 2}));
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell("Course Code").setBold();
        table.addHeaderCell("Course Name").setBold();
        table.addHeaderCell("Credits").setBold();
        table.addHeaderCell("Grade").setBold();

        for (Result r : results) {
            table.addCell(r.getCourse().getCode());
            table.addCell(r.getCourse().getName());
            table.addCell(String.valueOf(r.getCourse().getCredits()));

            // Fetch grade from CourseEnrollment
            String grade = (r.getCourseEnrollment() != null && r.getCourseEnrollment().getGrade() != null)
                    ? r.getCourseEnrollment().getGrade()
                    : "-";
            table.addCell(grade);
        }
        doc.add(table);

        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("Total Credits Earned: " + totalCredits).setBold());
        doc.add(new Paragraph("SGPA: " + String.format("%.2f", sgpa)));
        doc.add(new Paragraph("CGPA: " + String.format("%.2f", cgpa)));

        doc.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

}
