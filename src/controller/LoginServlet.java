package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            // tao Session
            HttpSession session = request.getSession();

            if (username.equals("admin") && password.equals("admin")) {

                // tao Cookie lay thong tin cua nguoi dung de nho lai
                Cookie user = new Cookie("user", "admin");
                Cookie pass = new Cookie("pass", "admin");
                if (request.getParameter("chkRemember") != null) {
                    user.setMaxAge(60 * 60 * 24);
                    pass.setMaxAge(60 * 60 * 24);
                } else {
                    user.setMaxAge(0);
                    pass.setMaxAge(0);
                }
                response.addCookie(user);
                response.addCookie(pass);

                // luu vao session
                session.setAttribute("username", "admin");

                RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
                dis.forward(request, response);
            } else {
                request.setAttribute("error", "Username and Password invalid !");
                RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
                dis.forward(request, response);
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
