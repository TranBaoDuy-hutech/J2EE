package com.travel3d.vietlutravel.controller;

import com.travel3d.vietlutravel.model.Tours;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ToursController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/tours")
    public String toursPage(Model model) {

        List<Tours> tours = entityManager
                .createQuery("FROM Tours", Tours.class)
                .getResultList();

        model.addAttribute("tours", tours);
        return "tours";
    }


}
