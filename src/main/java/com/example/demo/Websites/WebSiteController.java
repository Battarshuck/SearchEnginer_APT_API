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

    @GetMapping("/words")
    public List<Samples> findByWordIn(@RequestParam("q") String words) {
        return webSiteService.findByWordIn(words);
    }

    @GetMapping("/count")
    public long count() {
        return webSiteService.count();
    }
}
