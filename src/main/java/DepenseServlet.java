package servlet;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.*;             
import jakarta.servlet.http.*; 

import credit.*;
import depense.*;

public class DepenseServlet extends HttpServlet  {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            List<Credit> allCredit = Credit.findAll();
            req.setAttribute("credits", allCredit);
            RequestDispatcher dispatcher = req.getRequestDispatcher("ajoutDepense.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int idCredit = Integer.parseInt(req.getParameter("idCredit"));
            float montant = Float.parseFloat(req.getParameter("montant"));
            Date date = Date.valueOf(req.getParameter("dateDepense"));

            Depense d = new Depense();
            d.setIdCredit(idCredit);
            d.setMontant(montant);
            d.setDate(date);

            d.save();
       
            res.sendRedirect("ajoutDepense");
                        
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }    
}
