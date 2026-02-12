package com.travel3d.vietlutravel.service;

import com.travel3d.vietlutravel.model.Booking;
import com.travel3d.vietlutravel.model.Customer;
import com.travel3d.vietlutravel.model.Tours;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmailService emailService;
    /**
     * Đặt tour mới + gửi mail xác nhận
     */
    @Transactional
    public Booking bookTour(int tourID, int customerID, int numberOfPeople) {

        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException("Số người phải lớn hơn 0");
        }

        Tours tour = entityManager.find(Tours.class, tourID);
        Customer customer = entityManager.find(Customer.class, customerID);

        if (tour == null || customer == null) {
            throw new IllegalArgumentException("Tour hoặc Customer không tồn tại");
        }

        Booking booking = new Booking();
        booking.setTourID(tourID);
        booking.setCustomerID(customerID);
        booking.setBookingDate(LocalDate.now());
        booking.setNumberOfPeople(numberOfPeople);
        booking.setTotalPrice(tour.getPrice().doubleValue() * numberOfPeople);
        booking.setStatus("Pending");

        entityManager.persist(booking);
        entityManager.flush(); // có ID

        // 🔥 QUAN TRỌNG NHẤT
        // GÁN TAY OBJECT vào booking để gửi mail được
        booking.setTour(tour);
        booking.setCustomer(customer);

        // Gửi mail
        emailService.sendBookingConfirmation(booking);

        return booking;
    }
    /**
     * Hủy booking (đúng luật 7 ngày)
     */
    @Transactional
    public boolean cancelBooking(int bookingID, int currentCustomerID) {

        Booking booking = entityManager.find(Booking.class, bookingID);
        if (booking == null) return false;

        if (booking.getCustomerID() != currentCustomerID) return false;

        if (!"Pending".equals(booking.getStatus())) return false;

        Tours tour = entityManager.find(Tours.class, booking.getTourID());
        if (tour == null || tour.getStartDate() == null) return false;

        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), tour.getStartDate());

        if (daysLeft < 7) return false;

        booking.setStatus("Cancelled");
        return true;
    }

    /**
     * Lấy tất cả booking (admin)
     */
    public List<Booking> getAllBookings() {
        return entityManager.createQuery(
                "SELECT b FROM Booking b ORDER BY b.bookingDate DESC", Booking.class
        ).getResultList();
    }

    /**
     * Lấy booking theo ID
     */
    public Booking getBookingById(int id) {
        return entityManager.find(Booking.class, id);
    }

    /**
     * Admin cập nhật trạng thái
     */
    @Transactional
    public void updateBookingStatus(int id, String newStatus) {

        Booking booking = getBookingById(id);
        if (booking == null) {
            throw new IllegalArgumentException("Booking không tồn tại (ID: " + id + ")");
        }

        if (!List.of("Pending", "Confirmed", "Cancelled").contains(newStatus)) {
            throw new IllegalArgumentException("Trạng thái không hợp lệ");
        }

        booking.setStatus(newStatus);
    }

    /**
     * Admin xóa booking
     */
    @Transactional
    public void deleteBooking(int id) {
        Booking booking = getBookingById(id);
        if (booking != null) {
            entityManager.remove(booking);
        }
    }
}
