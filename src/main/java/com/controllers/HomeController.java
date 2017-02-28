package com.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by nikita on 28.02.2017.
 */

@Controller
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(HomeController.class);

    @RequestMapping(value = "/")
    public ModelAndView main(){
        LOGGER.info("Main method started.");
        return new ModelAndView("index");
    }
}
