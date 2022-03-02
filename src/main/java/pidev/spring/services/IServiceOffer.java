package pidev.spring.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;

import pidev.spring.entities.CategoryOffer;
import pidev.spring.entities.Offer;

public interface IServiceOffer {
	
	List<Offer> retrieveAllOffers();
	Offer addOffer(Offer o, Long idUser);
	Offer updateOffer(Offer o);
	Offer retrieveOffer(int id);
	void deleteOffer(int id);
	
	void affectUserToOffer(int idOffer, Long idUser);
	List<Offer> retrieveByCategory(CategoryOffer category);
	List<Offer> retrieveFullOffer(Long idUser);
	void getCoupon(HttpServletResponse response, int idOffer, Long idUser) throws DocumentException, IOException ;
	
	List<Offer> searchOffer(String title);
	//void reserverOffer(int idOffer,int idUser); // nbrPerso+1
	
}
