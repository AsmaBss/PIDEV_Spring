package pidev.spring.webcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pidev.spring.entities.Review;
import pidev.spring.services.IReviewServices;



@RestController
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	IReviewServices reveiwServ;
	
	//Afficher Review
	
	@GetMapping("/retrieve-all-review")
	@ResponseBody
	public List<Review> getreview() {
	List<Review> listReview = reveiwServ.retrieveAllReview();
	return listReview;
	}
	
			//update Review Company
			@PutMapping("/modify-reviewC/{idUser}")
			@ResponseBody
			public Review modifyReviewC(@RequestBody Review review, @PathVariable Long idUser) {
			return reveiwServ.updateReviewCompany(review,idUser);
			}
			//Mpdifier Employee
			@PutMapping("/modify-reviewE/{idUser}")
			@ResponseBody
			public Review modifyReviewE(@RequestBody Review review, @PathVariable Long idUser) {
			return reveiwServ.updateReviewEmployee(review,idUser);
			}
	
		//Ajouter Review company
			
			@PostMapping("/add-reviewC/{idUser}")
			@ResponseBody
			public Review addReviewC(@RequestBody Review r, @PathVariable Long idUser)
			{
				Review review = reveiwServ.addReviewCompany(r,idUser);
			return review;
			}
			
			//Ajouter Review company
			
			@PostMapping("/add-reviewE/{idUser}")
			@ResponseBody
			public Review addReviewE(@RequestBody Review r, @PathVariable Long idUser)
			{
				Review review = reveiwServ.addReviewEmployee(r,idUser);
			return review;
			}
			
		//Supprimer Review
			
			// http://localhost:8087/SpringMVC/centre/remove-review/{review-id}
			@DeleteMapping("/remove-review/{review-id}")
			@ResponseBody
			public void RemoveReview(@PathVariable("review-id") Integer id) {
				reveiwServ.deleteReview(id);
			}
			
			
			@PostMapping("/add-reviewetAffecter/{idUser}")
			@ResponseBody
			public Review addReviewAffecE(@RequestBody Review r, @PathVariable Long idUser)
			{
				//Review review = reveiwServ.addReviewCompany(r,idUser);
			return reveiwServ.addReviewEmployeEtAffecter(r, idUser);
			}
			
			
			
	
	
}
