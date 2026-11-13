
package web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            resp.sendRedirect(
                req.getContextPath() + "/login"
            );
            return;
        }

        String user = (String)
            session.getAttribute("user");

        req.setAttribute("user", user);

        req.getRequestDispatcher(
            "/WEB-INF/views/dashboard.jsp"
        ).forward(req, resp);
    }
}
