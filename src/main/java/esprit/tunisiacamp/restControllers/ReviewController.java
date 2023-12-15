package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.Review;
import esprit.tunisiacamp.services.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private IReviewService iReviewService;

    @PostMapping("/saveReview")
    public ResponseEntity<Review> saveReview(@RequestBody Review review) {
        Review review1= iReviewService.saveReview(review);
        return ResponseEntity.ok(review1);
    }

    @GetMapping("/getAllReview")
    public List<Review> getAllReview() {
        return iReviewService.getAllReview();
    }

    @DeleteMapping("deleteReview/{id}")
    public void deleteReviewById(@RequestParam long id) {
        iReviewService.deleteReviewById(id);
    }
    @PutMapping("affecterreview")
    public void affecterReview(@RequestParam Integer reviewId, @RequestParam Integer campingId, @RequestParam Integer userId){
        iReviewService.affecterReview(reviewId,campingId,userId);}
    @GetMapping("ReviewCamp")
    public List<Review> getReview(long id) {
        return iReviewService.getReview(id);
    }
}
