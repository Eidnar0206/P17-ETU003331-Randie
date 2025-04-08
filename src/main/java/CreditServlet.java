package servlet;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;             
import jakarta.servlet.http.*; 

import credit.*;

public class CreditServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("ajoutCredit.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String libelle = req.getParameter("libelle");
            float montant = Float.parseFloat(req.getParameter("montant"));
            Credit c = new Credit();
            c.setLibelle(libelle);
            c.setMontant(montant);
            c.save();
            RequestDispatcher dispatcher = req.getRequestDispatcher("ajoutCredit.jsp");
            dispatcher.forward(req, res);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }
}
