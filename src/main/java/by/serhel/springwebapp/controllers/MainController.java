package by.serhel.springwebapp.controllers;

import by.serhel.springwebapp.entities.Advert;
import by.serhel.springwebapp.repositories.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private AdvertRepository advertRepository;

    @GetMapping("/")
    public String homePage(Map<String, Object> model){
        return "home";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<Advert> adverts = advertRepository.findAll();

        model.put("adverts", adverts);

        return "main";
    }


        @PostMapping("/main")
    public String add(@RequestParam String bookName, @RequestParam String authorName,
                            @RequestParam String genre, Map<String, Object> model){

        Advert advert = new Advert(bookName, authorName, genre);
        advertRepository.save(advert);

        Iterable<Advert> adverts = advertRepository.findAll();

        model.put("adverts", adverts);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Advert> adverts;
        if(filter != null && !filter.isEmpty()){
            adverts = advertRepository.findByGenre(filter);
        }
        else{
            adverts = advertRepository.findAll();      }

        model.put("adverts", adverts);

        return "main";
    }
}
