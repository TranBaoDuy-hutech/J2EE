package com.travel3d.vietlutravel.controller;

import com.travel3d.vietlutravel.model.Culture;
import com.travel3d.vietlutravel.service.CultureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Tour3DController {

    private final CultureService cultureService;

    public Tour3DController(CultureService cultureService) {
        this.cultureService = cultureService;
    }

    @GetMapping("/tour3d")
    public String tour3dHome() {
        return "tour3d/index";
    }

    @GetMapping("/tour3d/tour")
    public String tour360() {
        return "tour3d/tour";
    }

    @GetMapping("/tour3d/food")
    public String food3d() {
        return "tour3d/food";
    }

    @GetMapping("/tour3d/culture")
    public String culture360(Model model) {
        model.addAttribute("cultures", cultureService.getAllCultures());
        return "tour3d/culture";
    }

    @GetMapping("/tour3d/culture/{id}")
    public String cultureDetail(@PathVariable("id") int id, Model model) {
        Culture culture = cultureService.getCultureById(id);
        if (culture == null) {
            return "redirect:/tour3d/culture";
        }
        model.addAttribute("culture", culture);
        return "tour3d/culture-detail";
    }
}
