package com.travel3d.vietlutravel.service;

import com.travel3d.vietlutravel.model.Booking;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendBookingConfirmation(Booking booking) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(booking.getCustomer().getEmail());
            helper.setFrom("vietlutravell@gmail.com");
            helper.setSubject("[Việt Lữ Travel] Xác Nhận Đặt Tour: " + booking.getTour().getTourName());

            String htmlContent = """
                <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 8px;">
                    <h2 style="color: #667eea; text-align: center;">Xác nhận đặt tour thành công!</h2>
                    <p>Xin chào <strong>%s</strong>,</p>
                    <p>Cảm ơn bạn đã tin tưởng và đặt tour cùng <strong>Việt Lữ Travel</strong>!</p>
                    
                    <h3 style="color: #764ba2;">Thông tin đặt tour:</h3>
                    <table style="width: 100%%; border-collapse: collapse;">
                        <tr><td style="padding: 8px;"><strong>Tour:</strong></td><td>%s</td></tr>
                        <tr><td style="padding: 8px;"><strong>Ngày khởi hành:</strong></td><td>%s</td></tr>
                        <tr><td style="padding: 8px;"><strong>Số người:</strong></td><td>%d</td></tr>
                        <tr><td style="padding: 8px;"><strong>Tổng tiền:</strong></td><td>%,d VNĐ</td></tr>
                        <tr><td style="padding: 8px;"><strong>Ngày đặt:</strong></td><td>%s</td></tr>
                        <tr><td style="padding: 8px;"><strong>Mã booking:</strong></td><td>#%d</td></tr>
                        <tr><td style="padding: 8px;"><strong>Trạng thái:</strong></td><td>Pending (Đang chờ xác nhận)</td></tr>
                    </table>
                    
                    <p style="margin-top: 20px;">Chúng tôi sẽ liên hệ với bạn sớm nhất để xác nhận thông tin và hướng dẫn thanh toán.</p>
                    <p>Nếu có bất kỳ thắc mắc nào, vui lòng liên hệ hotline: <strong>096 123 4567</strong> hoặc email: <strong>vietlutravell@gmail.com</strong>.</p>
                    
                    <p style="text-align: center; margin-top: 30px; color: #666;">
                        Trân trọng,<br>
                        <strong>Đội ngũ Việt Lữ Travel</strong>
                    </p>
                </div>
                """.formatted(
                    booking.getCustomer().getUserName(),
                    booking.getTour().getTourName(),
                    booking.getTour().getStartDate(),
                    booking.getNumberOfPeople(),
                    (long) booking.getTotalPrice(),
                    booking.getBookingDate(),
                    booking.getBookingID()
            );

            helper.setText(htmlContent, true);  // true = gửi HTML
            mailSender.send(message);

            System.out.println("Đã gửi email xác nhận tới: " + booking.getCustomer().getEmail());

        } catch (MessagingException e) {
            System.err.println("Lỗi gửi email xác nhận booking: " + e.getMessage());
            // Có thể log hoặc throw tùy nhu cầu
        }
    }
}