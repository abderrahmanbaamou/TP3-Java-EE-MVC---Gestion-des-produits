&#x20;TP3 — Du Servlet au MVC

&#x20;Réponses aux questions



\_\_\_ Question 1 — Forward et Redirect



Le `forward()` transmet la requête à une JSP du côté serveur, sans modifier l'URL du navigateur.



Le `sendRedirect()` demande au navigateur d'effectuer une nouvelle requête vers une autre URL.



Dans notre projet, nous utilisons `forward()` pour afficher les formulaires et `sendRedirect()` après l'ajout ou la modification d'un produit.



\_\_\_ Question 2 — DAO et ServletContext



Le DAO permet de séparer la gestion des données de la logique Web.



Le `ServletContext` permet de partager une même instance de `ProduitDAO` entre plusieurs servlets.



Dans notre projet, `AppInitListener` crée le DAO au démarrage de l'application.



\_\_\_ Question 3 — Protection contre XSS



Avec `${produit.nom}`, une valeur contenant du HTML peut être interprétée par le navigateur si elle n'est pas échappée.



Avec `<c:out value="${produit.nom}"/>`, les caractères spéciaux sont échappés.



Cela permet de limiter les risques d'attaques XSS.



\_\_\_ Question 4 — Filtre d'authentification



Le filtre permet de centraliser la vérification de l'authentification.



Il évite de répéter le même code dans chaque servlet.



Deux autres utilisations classiques des filtres sont :

\- L'encodage des caractères.

\- La journalisation des requêtes (logs).

