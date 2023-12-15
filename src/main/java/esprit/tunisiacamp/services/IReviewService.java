package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.Review;

import java.util.List;

public interface IReviewService {
    Review saveReview(Review review);
    List<Review> getAllReview();
    void deleteReviewById(long id);
    void affecterReview (Integer reviewId , Integer campingId,Integer userId);
    List<Review> getReview(long id);
}
