package app.servlets;

import app.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin", "/admin/*"})
public class AdminFilterServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        User user = (User) request.getSession().getAttribute("user");
        String role = user.getRole();

        if (user != null & "admin".equals(role)) {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}