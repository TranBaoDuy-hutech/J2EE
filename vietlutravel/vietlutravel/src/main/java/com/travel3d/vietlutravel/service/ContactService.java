package com.travel3d.vietlutravel.service;

import com.travel3d.vietlutravel.model.Contact;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Contact> getAllContacts() {
        return entityManager.createQuery(
                        "SELECT c FROM Contact c ORDER BY c.createdAt DESC", Contact.class)
                .getResultList();
    }

    @Transactional
    public void deleteContact(int id) {
        Contact contact = entityManager.find(Contact.class, id);
        if (contact != null) {
            entityManager.remove(contact);
        }
    }

    // (Tùy chọn) Đếm tổng số liên hệ cho dashboard
    public long countContacts() {
        return entityManager.createQuery("SELECT COUNT(c) FROM Contact c", Long.class)
                .getSingleResult();
    }
}