package pidev.spring.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.DocumentException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pidev.spring.config.PDFExporter;
import pidev.spring.entities.CategoryOffer;
import pidev.spring.entities.Offer;
import pidev.spring.services.ServiceOffer;

@RestController
@RequestMapping("/Offer")
@Api(tags = "Offer Controller")
public class OfferController {

	//http://localhost:8089/WorkMood/swagger-ui/index.html
	
	@Autowired
	ServiceOffer offerService;
	
	@GetMapping("/ShowAllOffers")
	@ResponseBody
	@ApiOperation(value="Show all offers")
	List<Offer> retrieveAllOffers(){
		return offerService.retrieveAllOffers();
	}
	
	@PostMapping("/AddOffer/{idUser}")
	@ResponseBody
	@ApiOperation(value="Add offer")
	Offer addOffer(@RequestBody Offer o, @PathVariable Long idUser, @RequestParam MultipartFile file){
		return offerService.addOffer(o, idUser);
	}
	
	@PutMapping("/UpdateOffer")
	@ResponseBody
	@ApiOperation(value="Update offer")
	Offer updateOffer(@RequestBody Offer o){
		return offerService.updateOffer(o);
	}
	
	@GetMapping("/ShowOffer/{id}")
	@ResponseBody
	@ApiOperation(value="Show offer by id ")
	Offer retrieveOffer(@PathVariable(name="id") int id){
		return offerService.retrieveOffer(id);
	}
	
	@DeleteMapping("/DeleteOffer/{id}")
	@ApiOperation(value="Delete offer")
	void deleteOffer(@PathVariable(name="id") int id){
		offerService.deleteOffer(id);
	}
	
	@PostMapping("/AffectUserToOffer/{idUser}/{idOffer}")
	@ResponseBody
	@ApiOperation(value="Affect offer to user")
	void affectUserToOffer(@PathVariable int idOffer, @PathVariable Long idUser){
		offerService.affectUserToOffer(idOffer, idUser);
	}
	
	@GetMapping("/FindOfferByCategory")
	@ResponseBody
	@ApiOperation(value="Find by category")
	List<Offer> retrieveByCategory(@RequestParam CategoryOffer category){
		return offerService.retrieveByCategory(category);
	}
	
	@GetMapping("/ShowFullOffers")
	@ResponseBody
	@ApiOperation(value="Show full offers")
	List<Offer> retrieveFullOffer(Long idUser){
		return offerService.retrieveFullOffer(idUser);
	}
	
	/*@GetMapping("/GetCoupon/{idUser}/{idOffer}")
	public void getCoupon(@PathVariable int idOffer, @PathVariable Long idUser){
		offerService.getCoupon(idOffer, idUser);
	}*/
	
	
	@GetMapping("/GetCoupon/{idUser}/{idOffer}")
    public void exportToPDF(HttpServletResponse response, @PathVariable int idOffer, @PathVariable Long idUser) throws DocumentException, IOException {
        offerService.getCoupon(response, idOffer, idUser);
		/*response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=offers_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Offer> listUsers = offerService.retrieveAllOffers();
         
        PDFExporter exporter = new PDFExporter(listUsers);
        exporter.export(response);*/
         
    }
	

}