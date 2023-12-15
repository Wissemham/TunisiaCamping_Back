package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Review;
import esprit.tunisiacamp.repositories.CampingGroundRepositories;
import esprit.tunisiacamp.repositories.ReviewRepositories;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements IReviewService{
    @Autowired
    private ReviewRepositories reviewRepositories;
    @Autowired
    private CampingGroundRepositories campingGroundRepositories;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Review saveReview(Review review) {
        System.out.println(review.getIdReview());
        Date date = new Date();
        review.setCreation(date);
        return reviewRepositories.save(review);
    }

    @Override
    public List<Review> getAllReview() {
        return (List<Review>) reviewRepositories.findAll();
    }

    @Override
    public void deleteReviewById(long id) {
        reviewRepositories.deleteById(id);
    }

    @Override
    public void affecterReview(Integer reviewId, Integer campingId, Integer userId) {
        Review re = reviewRepositories.findById(Long.valueOf(reviewId)).get();
        CampingGround cp = campingGroundRepositories.findById(Long.valueOf(campingId)).get();
        User us = userRepository.findById(userId).get();
        re.setUser(us);
        re.setReview_Ground(cp);
        reviewRepositories.save(re);
    }

    @Override
    public List<Review> getReview(long id) {
        return reviewRepositories.findByCamp(id);
    }
}
