
package web;

import java.io.IOException;
import java.nio.file.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException, ServletException {

        Part filePart = req.getPart("file");

        String fileName = Path.of(
            filePart.getSubmittedFileName()
        ).getFileName().toString();

        if (fileName.isBlank()) {
            resp.sendError(400, "Fichier invalide");
            return;
        }

        Path dossier = Path.of(
            System.getProperty("user.home"), "tp-uploads"
        );

        Files.createDirectories(dossier);

        Path destination = dossier.resolve(fileName);

        try (var input = filePart.getInputStream()) {
            Files.copy(input, destination,
                StandardCopyOption.REPLACE_EXISTING);
        }

        resp.setContentType("text/html;charset=UTF-8");

        resp.getWriter().println(
            "Fichier uploade: " + fileName
        );
    }
}
