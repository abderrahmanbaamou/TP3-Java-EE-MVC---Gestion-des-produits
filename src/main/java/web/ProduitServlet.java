
package web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Produit;
import model.ProduitDAO;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    private ProduitDAO dao;

    @Override
    public void init() throws ServletException {
        dao = (ProduitDAO) getServletContext()
                .getAttribute("produitDAO");

        if (dao == null) {
            throw new ServletException("ProduitDAO introuvable");
        }
    }

    //
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null || action.equals("list")) {

            req.setAttribute("produits", dao.findAll());

            req.getRequestDispatcher(
                "/WEB-INF/views/produits/list.jsp"
            ).forward(req, resp);

        } else if (action.equals("new")) {

            req.setAttribute("produit", new Produit());

            req.getRequestDispatcher(
                "/WEB-INF/views/produits/form.jsp"
            ).forward(req, resp);

        } else if (action.equals("edit")) {

            try {
                Long id = Long.parseLong(req.getParameter("id"));
                Produit produit = dao.findById(id);

                if (produit == null) {
                    resp.sendError(404);
                    return;
                }

                req.setAttribute("produit", produit);

                req.getRequestDispatcher(
                    "/WEB-INF/views/produits/form.jsp"
                ).forward(req, resp);

            } catch (NumberFormatException e) {
                resp.sendError(404);
            }

        } else {
            resp.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if ("delete".equals(action)) {

            try {
                Long id = Long.parseLong(req.getParameter("id"));

                Produit produit = dao.findById(id);

                if (produit == null) {
                    resp.sendError(404);
                    return;
                }

                dao.delete(id);

                req.getSession().setAttribute(
                    "flash", "Produit supprime avec succes !"
                );

                resp.sendRedirect(
                    req.getContextPath() + "/produits"
                );

            } catch (NumberFormatException e) {
                resp.sendError(404);
            }

            return;
        }

        String idStr = req.getParameter("id");
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        String prixStr = req.getParameter("prix");
        String stockStr = req.getParameter("stock");

        Map<String, String> erreurs = new HashMap<>();

        Long id = null;
        BigDecimal prix = null;
        int stock = 0;

        if (idStr != null && !idStr.isBlank()) {
            try {
                id = Long.parseLong(idStr);

                if (dao.findById(id) == null) {
                    resp.sendError(404);
                    return;
                }

            } catch (NumberFormatException e) {
                resp.sendError(404);
                return;
            }
        }

        if (nom == null || nom.trim().isEmpty()) {
            erreurs.put("nom", "Le nom est obligatoire.");

        } else if (nom.trim().length() < 2 ||
                   nom.trim().length() > 50) {

            erreurs.put(
                "nom", "Le nom doit contenir entre 2 et 50 caracteres."
            );
        }

        if (description != null && description.length() > 255) {
            erreurs.put(
                "description",
                "La description ne doit pas depasser 255 caracteres."
            );
        }

        if (prixStr == null || prixStr.isBlank()) {

            erreurs.put("prix", "Le prix est obligatoire.");

        } else {
            try {
                prix = new BigDecimal(prixStr.trim());

                if (prix.compareTo(BigDecimal.ZERO) <= 0) {
                    erreurs.put(
                        "prix", "Le prix doit etre superieur a 0."
                    );
                }

            } catch (NumberFormatException e) {
                erreurs.put("prix", "Le prix doit etre un nombre.");
            }
        }

        if (stockStr != null && !stockStr.isBlank()) {

            try {
                stock = Integer.parseInt(stockStr.trim());

                if (stock < 0) {
                    erreurs.put(
                        "stock", "Le stock ne peut pas etre negatif."
                    );
                }

            } catch (NumberFormatException e) {
                erreurs.put(
                    "stock", "Le stock doit etre un entier."
                );
            }
        }

        Produit produit = new Produit(
            id,
            nom,
            description,
            prix,
            stock
        );

        if (!erreurs.isEmpty()) {

            req.setAttribute("erreurs", erreurs);
            req.setAttribute("produit", produit);

            req.getRequestDispatcher(
                "/WEB-INF/views/produits/form.jsp"
            ).forward(req, resp);

            return;
        }

        dao.save(produit);

        req.getSession().setAttribute(
            "flash",
            "Produit " + produit.getNom() + " enregistre avec succes !"
        );

        resp.sendRedirect(
            req.getContextPath() + "/produits"
        );
    }
}
