
package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.ProduitDAO;

@WebListener
public class AppInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ProduitDAO dao = new ProduitDAO();

        sce.getServletContext().setAttribute("produitDAO", dao);

        System.out.println("ProduitDAO initialise avec succes !");
    }
}
