# DATABASE

```sql
-- ===============================================
-- TẠO DATABASE VÀ CẤU TRÚC BẢNG CHO ỨNG DỤNG STUDENT MANAGEMENT MVC
-- ===============================================

-- Tạo database (nếu chưa tồn tại)
DROP DATABASE IF EXISTS studentdb;
CREATE DATABASE studentdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE studentdb;

-- Tạo bảng class
CREATE TABLE class (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Tạo bảng student
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gpa DOUBLE DEFAULT 0,
    class_id INT DEFAULT NULL,
    CONSTRAINT fk_student_class
        FOREIGN KEY (class_id)
        REFERENCES class(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- Thêm dữ liệu mẫu
INSERT INTO class (name) VALUES
('Lớp 12A1'),
('Lớp 12A2'),
('Lớp 12A3');

INSERT INTO student (name, gpa, class_id) VALUES
('Nguyễn Văn A', 9.5, 1),
('Nguyễn Văn B', 8.7, 1),
('Trần Thị C', 7.8, 2),
('Lê Văn D', 9.0, 2),
('Phạm Thị E', 8.2, 3),
('Hoàng Văn F', 9.1, 3),
('Võ Thị G', 7.5, NULL),
('Đỗ Văn H', 6.9, NULL),
('Nguyễn Thị I', 9.4, 1),
('Phan Văn K', 8.0, 2),
('Đặng Thị L', 7.3, 2),
('Lý Văn M', 9.2, 3),
('Nguyễn Thị N', 8.5, 1),
('Phạm Văn O', 7.1, 2),
('Võ Thị P', 8.9, 3),
('Trương Văn Q', 6.8, 1),
('Hoàng Thị R', 9.0, 2),
('Ngô Văn S', 7.9, 3),
('Lâm Thị T', 8.4, 2),
('Đinh Văn U', 9.3, 1);

```