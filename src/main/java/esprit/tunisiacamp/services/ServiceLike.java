package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.Post;
import esprit.tunisiacamp.entities.forum.Rating;
import esprit.tunisiacamp.repositories.RtingRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ServiceLike implements IserviceLike {
    @Autowired
    RtingRepository ratingRepository;
    @Autowired
    esprit.tunisiacamp.repositories.postRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void likerPost(Rating l, long idPost, Integer idUser) {
        Post post = postRepository.findById(idPost).get();
        User user = userRepository.findById(idUser).get();

        l.setPost_rate(post);
        l.setUser(user);

        ratingRepository.save(l);
    }

    @Override
    public void modifrate(Rating rating) {
        Rating ratingg = ratingRepository.findById(rating.getIdRating()).get();
        ratingg.setValue(rating.getValue());
        ratingRepository.save(ratingg);
    }

    @Override
    public List<Rating> listelike(long idPoste) {
        Post post =postRepository.findById(idPoste).get() ;
    List<Rating> likes=     post.getRatings() ;
        return likes;
    }

    @Override
    public void supprimerlike(long idlike) {
        ratingRepository.deleteById(idlike);
    }

    @Override
    public List<Rating> getLikes() {
        return (List<Rating>) ratingRepository.findAll();
    }

}
