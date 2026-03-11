-- Bảng Culture cho module Văn hóa An Giang
-- Chạy script này trong phpMyAdmin (XAMPP), chọn database: vietlutravel3d

USE vietlutravel3d;

CREATE TABLE IF NOT EXISTS Culture (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(500) NOT NULL,
    Thumbnail VARCHAR(255) DEFAULT NULL,
    ShortDescription VARCHAR(1000) DEFAULT NULL,
    VideoUrl VARCHAR(500) DEFAULT NULL,
    FullDescription TEXT DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Sau khi tạo bảng, khởi động ứng dụng Spring Boot.
-- CultureDataLoader sẽ tự động chèn 17 bản ghi mẫu nếu bảng đang trống.
