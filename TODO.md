# Mục tiêu

Phát triển thêm cho ứng dụng **Quản lý sinh viên (JSP + Servlet + JDBC)** các tính năng mới:
1. Cập nhật thông tin sinh viên (Update)  
2. Tìm kiếm sinh viên theo tên (Search)  
3. Hiển thị danh sách có phân trang (Pagination)

# Yêu cầu chức năng

## Cập nhật sinh viên

Cho phép người dùng nhấn **Edit**, hiển thị lại form chứa thông tin sinh viên, và chỉnh sửa nội dung.

- Cập nhật phương thức `doGet` và `doPost` trong `StudentController.java` để xử lý việc hiển thị form chỉnh sửa và lưu thông tin sau khi chỉnh sửa.

- Cập nhật file `student-form.jsp` để hiển thị thông tin sinh viên khi chỉnh sửa.

- Thêm nút **Edit** trong bảng danh sách sinh viên `student-list.jsp` để chuyển trang đến form chỉnh sửa.

## Phân trang

Hiển thị danh sách sinh viên theo trang, mỗi trang hiển thị 5 sinh viên.

- Thêm 2 phương thức `findAll(int page, int pageSize)` và `countAll()` trong `StudentDAO.java` và `StudentDAOImpl.java` để lấy danh sách sinh viên theo trang và đếm tổng số sinh viên.

- Cập nhật phương thức `doGet` trong `StudentController.java` để xử lý phân trang.

- Cập nhật file `student-list.jsp` để hiển thị các liên kết phân trang.

## Tìm kiếm sinh viên theo tên

Thêm chức năng tìm kiếm sinh viên theo tên trong trang danh sách sinh viên.

- Thêm 2 phương thức `searchByName(String keyword, int page, int pageSize)` và `int countByName(String keyword)` trong `StudentDAO.java` và `StudentDAOImpl.java` để tìm kiếm sinh viên theo tên và đếm số lượng kết quả tìm kiếm.

- Cập nhật phương thức `doGet` trong `StudentController.java` để xử lý tìm kiếm.

- Cập nhật file `student-list.jsp` để hiển thị kết quả tìm kiếm.