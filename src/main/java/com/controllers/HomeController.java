package com.controllers;

import com.algorithms.HebbianNetwork;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonView;
import com.services.AjaxArray;
import com.services.AjaxResponseBody;
import com.services.ConverterBean;
import com.services.Views;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.ValidationException;

/**
 * Created by nikita on 28.02.2017.
 */

@Controller
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(HomeController.class);

//    HebbianNetwork hebbianNetwork;
//
//
//    public HomeController(HebbianNetwork hebbianNetwork){
//        if (hebbianNetwork == null){
//            LOGGER.info("hebbianNetwork is null");
//            throw new ValidationException("HebbianNetwork cannot be null");
//        }
//        this.hebbianNetwork = hebbianNetwork;
//    }

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
        HebbianNetwork hebbianNetwork = new HebbianNetwork();
        AjaxResponseBody responseBody = new AjaxResponseBody();
        int a[][] = converterBean.getValue();
        int b[][] = converterBean.getRecognize();
        int c[] = new int[25];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                c[j] = b[i][j];
            }
        }
        System.out.println("inputs");
        for (int i = 0; i<a.length; i++){
            System.out.println();
            for (int j = 0; j<a[i].length-1; j++){
                System.out.print(a[i][j]);
            }
        }
        try{
            System.out.println("weights");
            for (int i = 0; i<2; i++){
                System.out.println();
                for (int j = 0; j<a[i].length; j++){
                    System.out.print(hebbianNetwork.weights[i][j]);
                }
            }
        }
        catch (Exception e){
            LOGGER.info(e.toString());
        }
        hebbianNetwork.training(a);
        String result1 = hebbianNetwork.recognize(c);
        responseBody.setResult(result1);
        return responseBody;
    }

    @JsonView(Views.Public.class)
    @ResponseBody
    @RequestMapping(value = "/recognising")
    public AjaxResponseBody recognising(@RequestBody ConverterBean converterBean){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        return ajaxResponseBody;
    }
}
