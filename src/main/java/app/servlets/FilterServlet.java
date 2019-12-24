package app.servlets;

import app.entities.User;
import app.service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class FilterServlet extends HttpServlet {
    private UserServiceImpl instance = UserServiceImpl.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User(login, password);

        if(instance.validate(user)) {
            resp.sendRedirect("/admin");
        } else {
            resp.sendRedirect("/user");
        }
    }
}
