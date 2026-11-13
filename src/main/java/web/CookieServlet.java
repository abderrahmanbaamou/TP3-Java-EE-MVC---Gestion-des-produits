
package web;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");

        if ("set".equals(action)) {

            Cookie cookie = new Cookie("theme", "dark");

            cookie.setMaxAge(30 * 24 * 60 * 60);
            cookie.setPath("/");

            resp.addCookie(cookie);
        }

        resp.setContentType("text/html;charset=UTF-8");

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                resp.getWriter().println(
                    c.getName() + ": " + c.getValue() + "<br>"
                );
            }
        }
    }
}