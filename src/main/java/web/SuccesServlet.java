
package web;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/succes")
public class SuccesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession();

        String utilisateur = (String)
            session.getAttribute("utilisateur");

        if (utilisateur == null) {
            resp.sendRedirect("/TP/inscription");
            return;
        }

        resp.setContentType("text/html;charset=UTF-8");

        resp.getWriter().println(
            "<h1>Bienvenue " + utilisateur + "!</h1>"
        );
    }
}
