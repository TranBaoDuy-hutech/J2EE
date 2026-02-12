package com.travel3d.vietlutravel.service;

import com.travel3d.vietlutravel.model.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private DataSource dataSource;

    // LƯU TIN NHẮN
    public void save(Messages m) {

        String sql = """
            INSERT INTO Messages
            (CustomerID, UserID, Content, SentAt, IsFromAdmin, IsRead, CreatedAt)
            VALUES (?, ?, ?, ?, ?, 0, NOW())
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, m.getCustomerID());
            ps.setInt(2, m.getUserID());
            ps.setString(3, m.getContent());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.setBoolean(5, m.getIsFromAdmin());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LẤY DANH SÁCH KHÁCH ĐÃ CHAT (CHO STAFF)
    public List<Integer> getCustomerList() {

        List<Integer> list = new ArrayList<>();

        String sql = "SELECT DISTINCT CustomerID FROM Messages ORDER BY CustomerID";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getInt("CustomerID"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // LẤY LỊCH SỬ CHAT THEO KHÁCH
    public List<Messages> getHistory(int customerId) {

        List<Messages> list = new ArrayList<>();

        String sql = """
            SELECT * FROM Messages
            WHERE CustomerID = ?
            ORDER BY SentAt
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Messages m = new Messages();
                m.setCustomerID(rs.getInt("CustomerID"));
                m.setUserID(rs.getInt("UserID"));
                m.setContent(rs.getString("Content"));
                m.setIsFromAdmin(rs.getBoolean("IsFromAdmin"));
                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Messages> getHistoryByCustomer(int customerId) {

        List<Messages> list = new ArrayList<>();

        String sql = """
        SELECT * FROM Messages
        WHERE CustomerID = ?
        ORDER BY SentAt
    """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Messages m = new Messages();
                m.setContent(rs.getString("Content"));
                m.setIsFromAdmin(rs.getBoolean("IsFromAdmin"));
                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
