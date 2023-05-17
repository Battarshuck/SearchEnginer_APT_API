package com.example.demo.Websites;

import com.example.demo.DataBase.Samples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class WebSiteController {
    @Autowired
    private final WebSiteService webSiteService;

    public WebSiteController(WebSiteService webSiteService) {
        this.webSiteService = webSiteService;
    }
    //update the following to search for exact words if "" are used
    @GetMapping("/words")
    public List<Website> findByWordIn(@RequestParam("q") String words) {
        if (words.charAt(0) == '"' && words.charAt(words.length() - 1) == '"') {
            
            return webSiteService.findByWordIn(words, true);
        }

        return webSiteService.findByWordIn(words, false);
    }

    @GetMapping("/count")
    public long count() {
        return webSiteService.count();
    }
}
