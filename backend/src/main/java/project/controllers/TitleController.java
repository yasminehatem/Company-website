package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.models.Title;
import project.services.TitleService;

import java.util.List;

@RestController
public class TitleController {

    @Autowired
    private TitleService titleService;



    @RequestMapping(value = "/titles", method= RequestMethod.GET)
    public List<String> getTitleNames(){
        List<String> l =  titleService.getAllTitles();

        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        return  titleService.getAllTitles();

      }

    @RequestMapping(value = "/titlesObjects", method= RequestMethod.GET)
    public List<Title> getAll(){

        return titleService.getAll();
    }
}
