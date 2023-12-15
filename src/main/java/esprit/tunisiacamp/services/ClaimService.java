package esprit.tunisiacamp.services;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import esprit.tunisiacamp.entities.Claim;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.Category;
import esprit.tunisiacamp.entities.enums.role;
import esprit.tunisiacamp.repositories.ClaimRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClaimService implements IClaimsService {
    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;
    public void MyService(StanfordCoreNLP stanfordCoreNLP) {
        this.stanfordCoreNLP = stanfordCoreNLP;
    }
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }
    public void sendNotificationEmail(Claim claim) {
        String to = claim.getUser().getEmail();
        String subject = "Your claim has been processed";
        String body = "Dear " + claim.getUser().getFirstname() + ",\n\n" +
                "Your claim has been processed. Thank you for contacting us.\n\n" +
                "Best regards,\n" +
                "The Camp-Tunisia team";
        sendEmail(to, subject, body);
    }
   /* public long adminId;
    @Bean
   public long ClaimProcessor(long adminId) {
         this.adminId = adminId;
        return adminId;
    }*/



    @Override
    public void addclaimsandaffecterUser(Claim c, Integer idUser) {
        User user = userRepository.findById(idUser).get();//child
        //affecter child au parent
        c.setUser(user);
        c.setCreation(new Date());
        c.setState(false);
        claimRepository.save(c);
        //sendNotificationEmail(c);
    }

    @Override
    public void modifyclaim(long idClaim, Claim c1) {
        Claim claim = claimRepository.findById(idClaim).get();
        claim.setTitle(c1.getTitle());
         claim.setCategory(c1.getCategory());
        claim.setContent(c1.getContent());
        claim.setCreation(new Date());
        claim.setState(false);
        claim.setAdmin(null);
        claimRepository.save(claim);
    }



    @Override
    public void deleteclaim(long idClaim) {
        claimRepository.deleteById(idClaim);
    }

    @Override
    public List<Claim> retrieveclaimByUser(Integer idUser) {
        return claimRepository.findByUser_IdUser(idUser);
    }
    @Override
    public void modifyetatclaimsbyadmin(Integer idUser, long idClaim) {
        User adminUser = userRepository.findById(idUser).orElse(null);
        if (adminUser != null && adminUser.getRole().getRole() == role.ADMIN) {
            Claim claim = claimRepository.findById(idClaim).orElse(null);
            if (claim != null) {
                claim.setState(true);
                claim.setAdmin(adminUser);
                claimRepository.save(claim);
                String recipient = claim.getUser().getEmail();
                String subject = "Your claim was accepted";
                String body = "Dear " + claim.getUser().getFirstname() + ",\n\n" +
                        "We are pleased to inform you that your claim with claim ID " + claim.getIdClaim() + " has been accepted by our admin.\n\n" +
                        "Thank you for choosing our service.\n\n" +
                        "Best regards,\n" +
                        "The Camp-Tunisia team";

                //sendEmail(recipient, subject, body);
            }
        }
    }
    @Override
    public void addclaim(Claim c) {
        claimRepository.save(c);
    }
    @Override
    public List<Claim> getAllClaims() {

        return (List<Claim>) claimRepository.findAll();
    }
    @Override
    public List<Claim> sortedComplaintsBySentiment(List<Claim> complaints) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        for (Claim complaint : complaints) {
            String text = complaint.getContent();
            int sentimentScore = calculateSentimentScore(pipeline, text);
            complaint.setSentimentScore(sentimentScore);
        }
        List<Claim> sortedComplaints = complaints.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getSentimentScore(), c1.getSentimentScore()))
                .collect(Collectors.toList());
        return sortedComplaints;
    }

    private int calculateSentimentScore(StanfordCoreNLP pipeline, String text) {
        // List<Claim> complaint= (List<Claim>) claimRepository.findAll();
        int sentimentScore = 0;
        if (text != null && text.length() > 0) {
            Annotation annotation = pipeline.process(text);
            List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
            for (CoreMap sentence : sentences) {
                String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                switch (sentiment) {
                    case "Very negative":
                        sentimentScore += -2;
                        break;
                    case "Negative":
                        sentimentScore += -1;
                        break;
                    case "Neutral":
                        sentimentScore += 0;
                        break;
                    case "Positive":
                        sentimentScore += 1;
                        break;
                    case "Very positive":
                        sentimentScore += 2;
                        break;
                }
            }
        }
        return sentimentScore;
    }

    @Override
    public List<Claim> getUnresolvedClaims() {
        return claimRepository.findByState(false);
    }

    @Override
    public void processUnresolvedClaims(Integer adminId) {
        // User adminUser = claimRepository.findByRole(Role.class).orElse(null);
        List<Claim> unresolvedClaims = getUnresolvedClaims();
        for (Claim claim : unresolvedClaims) {
            int sentimentScore = calculateSentimentScore(stanfordCoreNLP, claim.getContent());
            // List<User> adminUser=userRepository.findAll();
            if (sentimentScore < 0) {
                modifyetatclaimsbyadmin(adminId, claim.getIdClaim());
            } else {
                markAsResolved(claim);
            }
        }
    }

    private void markAsResolved(Claim claim) {
        claim.setState(true);
        claimRepository.save(claim);

    }

    @Override
   public List<Object[]> countClaimsByCategory(){
        return claimRepository.countByCategory();
    }
    @Override
    public Map<String, Long> getClaimsByCategory() {
        List<Object[]> results = claimRepository.countByCategory();
        Map<String, Long> counts = new HashMap<>();
        for (Object[] result : results) {
            Category category = (Category) result[0];
            Long count = (Long) result[1];
            counts.put(category.name(), count);
        }
        return counts;
    }

    @Override
    public Map<String, Long> countClaimsByCategoryBetweenDates(Date startDate, Date endDate) {
        List<Object[]> results = claimRepository.countClaimsByCategoryBetweenDates(startDate, endDate);
        Map<String, Long> countByCategory = new HashMap<>();
        for (Object[] result : results) {
            Category category = (Category) result[0];
            Long count = (Long) result[1];
            countByCategory.put(category.name(), count);
        }
        return countByCategory;
    }

    @Override
    public List<Claim> getallbystate(boolean state) {
        List<Claim> c = claimRepository.findByState(state);
        return c;
    }
}