package com.travel3d.vietlutravel.controller;

import com.travel3d.vietlutravel.model.Customer;
import com.travel3d.vietlutravel.model.Messages;
import com.travel3d.vietlutravel.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/chat")
    public String chat(HttpSession session, Model model) {

        Customer user = (Customer) session.getAttribute("user");

        if (user == null) return "redirect:/login";

        model.addAttribute("myId", user.getCustomerID());
        return "chat";
    }

    @GetMapping("/chat/history")
    @ResponseBody
    public List<Messages> getHistory(HttpSession session) {

        Customer user = (Customer) session.getAttribute("user");
        return messageService.getHistoryByCustomer(user.getCustomerID());
    }
}
