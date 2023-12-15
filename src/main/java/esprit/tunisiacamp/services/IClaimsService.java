package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.Claim;
import esprit.tunisiacamp.entities.enums.Category;
import java.util.Date;
import java.util.List;
import java.util.Map;
public interface IClaimsService {
    public List<Claim> retrieveclaimByUser(Integer idUser);
    void addclaimsandaffecterUser(Claim c,Integer idUser);

    void deleteclaim(long idClaim);
    void modifyclaim(long idClaim,Claim c1);
    //void modifyetatclaimsbyadmin(long idUser,Claim cla1);
    void modifyetatclaimsbyadmin(Integer idUser,long idClaim);
    void addclaim(Claim c);
   // public  List<Claim> sortedClaimbysentiment(List<Claim> claims);
    List<Claim> sortedComplaintsBySentiment(List<Claim> complaints);
    public List<Claim> getAllClaims();
    List<Claim> getUnresolvedClaims();
     void processUnresolvedClaims(Integer adminId) ;
    //long ClaimProcessor(long adminId);
    List<Object[]> countClaimsByCategory();
     Map<String, Long> getClaimsByCategory();
    Map<String, Long> countClaimsByCategoryBetweenDates(Date startDate, Date endDate);
    List<Claim>getallbystate(boolean state);
}
