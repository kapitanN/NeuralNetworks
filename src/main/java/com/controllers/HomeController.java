package com.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.services.AjaxArray;
import com.services.AjaxResponseBody;
import com.services.ConverterBean;
import com.services.Views;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by nikita on 28.02.2017.
 */

@Controller
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(HomeController.class);

    @RequestMapping(value = "/")
    public ModelAndView main(){
        LOGGER.info("Main method started.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("array", new AjaxArray());
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @JsonView(Views.Public.class)
    @ResponseBody
    @RequestMapping(value = "/training")
    public AjaxResponseBody training(@RequestBody ConverterBean converterBean){
        LOGGER.info("training");
        AjaxResponseBody responseBody = new AjaxResponseBody();
        int a = converterBean.getValue();
        responseBody.setResult(a);

//        int res = array.getArray();
//        System.out.println(res);
//        redirectAttributes.addFlashAttribute("res",res);
        return responseBody;
    }
}
