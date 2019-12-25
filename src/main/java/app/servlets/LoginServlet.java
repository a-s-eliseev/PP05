package app.servlets;

import app.entities.User;
import app.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserServiceImpl instance = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User(login, password);
        String role = instance.validate(user);
        user.setRole(role);

        if ("admin".equals(role)) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/admin");
        } else if ("user".equals(role)) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/user");
        } else {
            resp.sendRedirect("/index.html");
        }
    }
}
