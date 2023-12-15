package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.Claim;
import esprit.tunisiacamp.entities.enums.Category;
import esprit.tunisiacamp.services.IClaimsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name="Manager of Claim")
@RequestMapping("/api/claims")
@CrossOrigin(origins ="*")
public class ClaimRestController {
    @Autowired
    IClaimsService iClaimsService;

    @GetMapping("/users/{userId}/claims")
    public List<Claim> retrieveclaimByUser(@PathVariable("userId") Integer idUser){

        return iClaimsService.retrieveclaimByUser(idUser);
    }
    @PostMapping("/addclaimtouser")
    public void addclaimtouser(@RequestBody Claim c,@RequestParam Integer idUser){

    iClaimsService.addclaimsandaffecterUser(c,idUser);
    }
    @PutMapping("/updateclaim/{claimId}")
    public  void modifyclaim(@PathVariable("claimId")long idClaim,@RequestBody Claim c1){

    iClaimsService.modifyclaim(idClaim, c1);
    }

    @DeleteMapping("/deleteclaimbyId")
    public void supprimerclaimAvecId(@RequestParam long idClaim){

        iClaimsService.deleteclaim(idClaim);
    }

    @PostMapping("/addclaim")
    public void addclaim(@RequestBody Claim c){
        iClaimsService.addclaim(c);
    }
    @PutMapping("/modifyetat")
    public void modifyetatclaimsbyadmin(@RequestParam Integer idUser,@RequestParam long idClaim) {
        iClaimsService.modifyetatclaimsbyadmin(idUser, idClaim);
    }

    @GetMapping("/sentiment")
    public List<Claim> getClaimsSortedBySentiment() {
        //List<Claim> claim = (List<Claim>) claimRepository.findAll();
        List<Claim> claim=iClaimsService.getAllClaims();
        return iClaimsService.sortedComplaintsBySentiment(claim);
    }
    @GetMapping("/retriveallclaims")
    public List<Claim>  retriveclaims(){

        return iClaimsService.getAllClaims();
    }
    @PutMapping("/resolveclaims")
    public void processUnresolvedClaims(Integer adminId) {
    iClaimsService.processUnresolvedClaims(adminId);
    }
    @GetMapping("/claims/count-by-category")
    public List<Object[]> countByCategory() {
        return iClaimsService.countClaimsByCategory();
    }
    //@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/statisticNumbercategory")
    public Map<String, Long> getClaimsByCategory() {
    return iClaimsService.getClaimsByCategory();
    }
   // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/NumberBetweenDate")
    public Map<String, Long> countClaimsByCategoryBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate) {
    return iClaimsService.countClaimsByCategoryBetweenDates(startDate,endDate);
    }
    @GetMapping("/findbystate")
    public List<Claim> getAllByState(@RequestParam boolean state){
        return iClaimsService.getallbystate(state);
    }
}
