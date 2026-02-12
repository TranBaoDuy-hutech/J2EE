package com.travel3d.vietlutravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Tour3DController {

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
    public String culture360() {
        return "tour3d/culture";
    }
}
