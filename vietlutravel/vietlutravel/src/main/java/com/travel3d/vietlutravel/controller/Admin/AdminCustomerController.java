package com.travel3d.vietlutravel.controller.Admin;

import com.travel3d.vietlutravel.model.Customer;
import com.travel3d.vietlutravel.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminCustomerController {

    @Autowired
    private CustomerService customerService;

    // Kiểm tra quyền admin
    private boolean isAdmin(HttpSession session) {
        Customer user = (Customer) session.getAttribute("user");
        return user != null && "ADMIN".equalsIgnoreCase(user.getRole());
    }

    // Danh sách tất cả khách hàng
    @GetMapping("/customers")
    public String listCustomers(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        List<Customer> allCustomers = customerService.getAllCustomers();

        // Lọc bỏ ADMIN – chỉ giữ USER và STAFF
        List<Customer> filteredCustomers = allCustomers.stream()
                .filter(c -> c.getRole() != null && !"ADMIN".equalsIgnoreCase(c.getRole()))
                .toList();

        model.addAttribute("customers", filteredCustomers);
        model.addAttribute("pageTitle", "Quản lý Người dùng");

        return "admin/customers";
    }

    // Xem chi tiết khách hàng
    @GetMapping("/customers/{id}")
    public String viewCustomerDetail(
            @PathVariable int id,
            Model model,
            HttpSession session) {

        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            model.addAttribute("errorMessage", "Không tìm thấy người dùng với ID: " + id);
            return "redirect:/admin/customers";
        }

        model.addAttribute("customer", customer);
        model.addAttribute("pageTitle", "Chi tiết Người dùng #" + id);

        return "admin/customer-detail";
    }

    // Cập nhật role (USER / STAFF / ADMIN)
    @PostMapping("/customers/{id}/update-role")
    public String updateRole(
            @PathVariable int id,
            @RequestParam String role,
            RedirectAttributes ra,
            HttpSession session) {

        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        Customer currentUser = (Customer) session.getAttribute("user");

        try {
            // Chỉ cho phép các role hợp lệ
            if (!List.of("USER", "STAFF", "ADMIN").contains(role)) {
                throw new IllegalArgumentException("Vai trò không hợp lệ");
            }

            Customer targetCustomer = customerService.getCustomerById(id);
            if (targetCustomer == null) {
                throw new IllegalArgumentException("Người dùng không tồn tại");
            }

            // Bảo vệ 1: Không cho thay đổi role của chính mình
            if (targetCustomer.getCustomerID() == currentUser.getCustomerID()) {
                throw new IllegalArgumentException("Bạn không thể thay đổi vai trò của chính mình!");
            }

            // Bảo vệ 2: Không cho thay đổi role của bất kỳ tài khoản ADMIN nào
            if ("ADMIN".equalsIgnoreCase(targetCustomer.getRole())) {
                throw new IllegalArgumentException("Không thể thay đổi vai trò của tài khoản Admin!");
            }

            customerService.updateCustomerRole(id, role);
            ra.addFlashAttribute("successMessage", "Cập nhật vai trò thành công!");

        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Lỗi cập nhật vai trò: " + e.getMessage());
        }

        return "redirect:/admin/customers";
    }

    // Xóa khách hàng
    @PostMapping("/customers/{id}/delete")
    public String deleteCustomer(
            @PathVariable int id,
            RedirectAttributes ra,
            HttpSession session) {

        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        Customer currentUser = (Customer) session.getAttribute("user");

        try {
            Customer targetCustomer = customerService.getCustomerById(id);
            if (targetCustomer == null) {
                throw new IllegalArgumentException("Người dùng không tồn tại");
            }

            // Bảo vệ 1: Không cho xóa chính mình
            if (targetCustomer.getCustomerID() == currentUser.getCustomerID()) {
                throw new IllegalArgumentException("Bạn không thể xóa tài khoản của chính mình!");
            }

            // Bảo vệ 2: Không cho xóa bất kỳ tài khoản ADMIN nào
            if ("ADMIN".equalsIgnoreCase(targetCustomer.getRole())) {
                throw new IllegalArgumentException("Không thể xóa tài khoản Admin!");
            }

            customerService.deleteCustomer(id);
            ra.addFlashAttribute("successMessage", "Xóa người dùng thành công!");

        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Lỗi xóa người dùng: " + e.getMessage());
        }

        return "redirect:/admin/customers";
    }
}