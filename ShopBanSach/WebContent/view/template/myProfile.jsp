<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<form action="/ShopBanSach/accountServlet" method="post">

                    <h1>Thông tin tài khoản</h1>
                    <%
                    HttpSession ses = request.getSession();
                    User u = new User();
                    if(ses!=null){
                    	if(ses.getAttribute("user")!=null){
                    		u = (User)ses.getAttribute("user");
                    	}
                    	out.print(u.getName());
                    } %>
                    
                <p class="error">${error } </p>
                
                    <div>
                        <label for="username">Tên đăng nhập</label>
                        <input type="text" name="username" id="username"  value="<%if(u.getUsername() !=null) out.print(u.getUsername()); %>" >
                    </div>
                   <div>
                        <label for="email">Email</label>
                        <input type="text" name="email" id="email"  value="<%if(u.getEmail() !=null) out.print(u.getEmail()); %>" >
                    </div>
                    <div>
                        <label for="phone">Số điện thoại</label>
                        <input type="text" name="phone" id="phone"  value="<%if(u.getPhone() !=null) out.print(u.getPhone()); %>" >
                    </div>
                    <div>
                       <label for="address">Địa chỉ</label>
                        <input type="text" name="address" id="address"  value="<%if(u.getAddress() !=null) out.print(u.getAddress()); %>">
                    </div>
                    <div class="btn-user"><input type="submit" name="action" value="Cập nhật" onsubmit="return confirmSubmit()"></div>
                </form>
                <script>
        function confirmSubmit() {
            // Hiển thị hộp thoại xác nhận
            var result = confirm('bạn có muốn thay đổi không ?');

            // Trả về kết quả xác nhận, true nếu người dùng nhấn "OK", ngược lại là false
            return result;
        }
    </script>