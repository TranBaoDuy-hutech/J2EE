package com.travel3d.vietlutravel.service;

import com.travel3d.vietlutravel.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> getAllCustomers() {
        return entityManager.createQuery(
                        "SELECT c FROM Customer c ORDER BY c.userName", Customer.class)
                .getResultList();
    }

    public Customer getCustomerById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Transactional
    public void updateCustomerRole(int id, String newRole) {
        Customer customer = getCustomerById(id);
        if (customer == null) {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }
        customer.setRole(newRole);
        // Không cần merge vì trong transaction
    }

    @Transactional
    public void deleteCustomer(int id) {
        Customer customer = getCustomerById(id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }
}