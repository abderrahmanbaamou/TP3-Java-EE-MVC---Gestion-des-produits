
package web;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        resp.sendRedirect("/TP/formulaire.html");
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        String nom = req.getParameter("nom");
        String email = req.getParameter("email");
        String ageStr = req.getParameter("age");

        if (nom == null || nom.isEmpty()) {
            resp.sendRedirect("?erreur=nom_vide");
            return;
        }

        if (email == null || !email.contains("@")) {
            resp.sendRedirect("?erreur=email_invalide");
            return;
        }

        int age = 0;

        try {
            age = Integer.parseInt(ageStr);

            if (age < 18) {
                resp.sendRedirect("?erreur=age_min");
                return;
            }

        } catch (NumberFormatException e) {
            resp.sendRedirect("?erreur=age_invalide");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("utilisateur", nom);

        resp.sendRedirect("/TP/succes");
    }
}
