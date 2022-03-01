package pidev.spring.services;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import pidev.spring.config.PDFExporter;
import pidev.spring.entities.CategoryOffer;
import pidev.spring.entities.Offer;
import pidev.spring.entities.User;
import pidev.spring.repositories.OfferRepo;
import pidev.spring.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ServiceOffer implements IServiceOffer {

	@Autowired
	OfferRepo offerRepo;
	@Autowired
	UserRepo userRepo;

	@Override
	public List<Offer> retrieveAllOffers() {
		List<Offer> offers = (List<Offer>) offerRepo.findAll();
		return offers;
	}

	@Override
	public Offer addOffer(Offer o, Long idUser) {
		o.setImage(null); // à modifier
		o.getPersonsNumber();
		offerRepo.save(o);
		System.out.println(o.getIdOffer());
		User u = userRepo.findById(idUser).orElse(null);
		System.out.println(u.getIdUser());
		System.out.println(o.getIdOffer());
		o.getUsers().add(u);
		return offerRepo.save(o);
	}

	@Override
	public void affectUserToOffer(int idOffer, Long idUser) {
		Offer o = offerRepo.findById(idOffer).orElse(null);
		User u = userRepo.findById(idUser).orElse(null);
		// System.out.println(u.getIdUser());
		// System.out.println(o.getIdOffer());
		o.getUsers().add(u);
		offerRepo.save(o);
	}

	@Override
	public Offer updateOffer(Offer o) {
		Offer offer = offerRepo.findById(o.getIdOffer()).orElse(null);
		offer.setTitle(o.getTitle());
		offer.setDescription(o.getDescription());
		offer.setDateExp(o.getDateExp());
		offer.setCategory(o.getCategory());
		offer.setPoint(o.getPoint());
		offer.setAddress(o.getAddress());
		offer.setLimitedNumber(o.getLimitedNumber());
		offer.setImage(null); // à modifier
		return offerRepo.save(offer);
	}

	@Override
	public Offer retrieveOffer(int id) {
		return offerRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteOffer(int id) {
		offerRepo.deleteById(id);
	}

	@Override
	public List<Offer> retrieveByCategory(CategoryOffer category) {
		return offerRepo.findAllByCategory(category);
	}

	@Override
	public List<Offer> retrieveFullOffer(Long idUser) {
		List<Offer> full = new ArrayList<>();
		List<Offer> offers = offerRepo.findAll();
		for (Offer o : offers) {
			if (o.getLimitedNumber() == o.getPersonsNumber()) {
				full.add(o);
			}
		}
		return full;
	}

	@Override
	public void getCoupon(HttpServletResponse response, int idOffer, Long idUser) throws DocumentException, IOException {
		Offer o = offerRepo.findById(idOffer).orElse(null);
		User u = userRepo.findById(idUser).orElse(null);

		if (u.getBadge().getPoint() >= o.getPoint()) {
			//creation coupon
			response.setContentType("application/pdf");
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());

			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=offers_" + currentDateTime + ".pdf";
			response.setHeader(headerKey, headerValue);

			PDFExporter exporter = new PDFExporter(o, u);
			exporter.export(response);
	
			// update du point user
			int up = u.getBadge().getPoint() - o.getPoint();
			u.getBadge().setPoint(up);
		}

	}

	private void export(HttpServletResponse response, int idOffer, Long idUser) throws DocumentException, IOException {
		Offer o = offerRepo.findById(idOffer).orElse(null);
		User u = userRepo.findById(idUser).orElse(null);
		Document document = new Document(PageSize.A6.rotate());
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font1.setSize(20);
		font1.setColor(Color.PINK);

		Paragraph p = new Paragraph(o.getTitle(), font1);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		Font font2 = FontFactory.getFont(FontFactory.HELVETICA);
		font2.setSize(20);
		font2.setColor(Color.BLACK);

		Paragraph p1 = new Paragraph(u.getFirstname() + " " + u.getLastName(), font2);
		Paragraph p2 = new Paragraph(String.valueOf(u.getBadge().getPoint()), font2);
		Paragraph p3 = new Paragraph(o.getAddress(), font2);
		// Paragraph p4 = new Paragraph(o.getTitle(), font2);

		document.add(p1);
		document.add(p2);
		document.add(p3);
		// document.add(p4);

		document.close();

	}

}