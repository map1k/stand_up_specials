package com.netflex.stand_up.controller;


import com.netflex.stand_up.model.Special;
import com.netflex.stand_up.service.LoadAndProcessSpecails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repositories.SpecialRepository;

import java.util.List;

@Slf4j
@Controller
public class LoadController {

    private final LoadAndProcessSpecails specialService;

    private final SpecialRepository specialRepository;

    @Value("${welcome.message}")
    private String message;

    public LoadController(LoadAndProcessSpecails recipeService, SpecialRepository specialRepository) {
        this.specialService = recipeService;
        this.specialRepository = specialRepository;
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping({"/specials"})
    public String getIndexPage(Model model) {


        List<Special> processedSpecials = specialService.getProcessedSpecials();
        log.debug(processedSpecials.size() + " Specials loaded");

        specialRepository.saveAll(processedSpecials);
        log.debug("SAVED");

        model.addAttribute("specials", processedSpecials);

        return "specials";
    }
}
