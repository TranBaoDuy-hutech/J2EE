package com.travel3d.vietlutravel.controller;

import com.travel3d.vietlutravel.model.Customer;
import com.travel3d.vietlutravel.model.Tours;
import com.travel3d.vietlutravel.service.BookingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * HIỂN THỊ FORM ĐẶT TOUR
     */
    @GetMapping("/booking-tour/{id}")
    public String showBookingForm(@PathVariable int id,
                                  HttpSession session,
                                  Model model) {

        Customer customer = (Customer) session.getAttribute("user");

        // CHẶN Ở ĐÂY
        if (customer == null) {
            return "redirect:/login";
        }

        Tours tour = entityManager.find(Tours.class, id);

        model.addAttribute("tour", tour);
        model.addAttribute("customer", customer);

        return "booking-form";
    }



    /**
     * XỬ LÝ ĐẶT TOUR
     */
    @PostMapping("/booking-tour")
    public String bookTour(@RequestParam("tourID") int tourID,
                           @RequestParam("numberOfPeople") int numberOfPeople,
                           HttpSession session) {

        // Lấy khách hàng đã đăng nhập
        Customer customer = (Customer) session.getAttribute("user");

        if (customer == null) {
            return "redirect:/login";
        }

        // Gọi service để lưu booking
        bookingService.bookTour(
                tourID,
                customer.getCustomerID(),
                numberOfPeople
        );

        return "redirect:/?bookingSuccess";
    }
}
