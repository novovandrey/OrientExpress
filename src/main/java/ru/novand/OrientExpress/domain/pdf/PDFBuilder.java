package ru.novand.OrientExpress.domain.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ru.novand.OrientExpress.domain.dto.OrderDTO;
import ru.novand.OrientExpress.domain.entities.Passenger;

public class PDFBuilder extends AbstractITextPdfView {

	public PDFBuilder() {
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get data model which is passed by the Spring container
		List<OrderDTO> listBooks = (List<OrderDTO>) model.get("listBooks");
		
		doc.add(new Paragraph("Order"));
		
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 1.0f, 2.0f});
		table.setSpacingBefore(10);
		
		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);
		
		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(6);
		
		// write table header 

		cell.setPhrase(new Phrase("FamilyName", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("FirstName", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("BirthDate", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Train #", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Departure date", font));
		table.addCell(cell);

		// write table row data
		for (OrderDTO aBook : listBooks) {
			table.addCell(aBook.getFamilyName());
			table.addCell(aBook.getFirstName());
			table.addCell(aBook.getBirthDate());
			table.addCell(aBook.getTrainCode());
			table.addCell(aBook.getDepartureDate());
		}
		
		doc.add(table);
		
	}

}