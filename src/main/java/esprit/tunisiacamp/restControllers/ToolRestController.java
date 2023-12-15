package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.shopping.Tool;
import esprit.tunisiacamp.services.ToolIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ToolRestController {
    @Autowired
    ToolIService toolservice;

    @CrossOrigin(origins = "*")
    @PostMapping("/tool/addtool")
    public Tool addTool(@RequestBody Tool tool){return toolservice.addTool(tool);}
    @DeleteMapping("/tool/deletetool")
    public void deleteTool(@RequestBody  Tool tool){toolservice.deleteTool(tool);}
    @PutMapping("/tool/updatetool")
    public Tool updateTool(@RequestBody Tool tool){return toolservice.updateTool(tool);}

    @GetMapping("/tool/gettools")
    public List<Tool> getTools(){return toolservice.getTools();}
    @GetMapping("/tool/gettoolsbyshop")
    public List<Tool> getToolsByShop(@RequestParam long shop_id){return toolservice.getToolsByShop(shop_id);}

    @GetMapping("/tool/threecriteriatoolsearch")
    public List<Tool> threeCriteriaToolSearch(@RequestParam String criteria1,@RequestParam String criteria2,@RequestParam String criteria3,
                                              @RequestParam String input1,@RequestParam String input2,@RequestParam String input3,@RequestParam int price)
    {return toolservice.threeCriteriaToolSearch(criteria1,criteria2,criteria3,input1,input2,input3,price);}

}
