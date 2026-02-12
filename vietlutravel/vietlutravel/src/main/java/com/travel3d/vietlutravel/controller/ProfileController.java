package com.travel3d.vietlutravel.controller;

import com.travel3d.vietlutravel.model.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {

        Customer customer = (Customer) session.getAttribute("user");

        if (customer == null) {
            return "redirect:/login";
        }

        model.addAttribute("customer", customer);

        return "profile";
    }
}
