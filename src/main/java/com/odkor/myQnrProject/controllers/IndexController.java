package com.odkor.myQnrProject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@Profile("nonrest")
public class IndexController {

    @RequestMapping({"/", "", "/index", "index.html"})
    public String showIndexPage() {
        log.info("Displaying index page...");
        return "index";
    }
}
