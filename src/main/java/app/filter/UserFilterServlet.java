package app.filter;

import app.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/user")
public class UserFilterServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        User user = (User) request.getSession().getAttribute("user");
        String role = user.getRole();

        if (user != null && ("admin".equals(role) || "user".equals(role))) {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}
