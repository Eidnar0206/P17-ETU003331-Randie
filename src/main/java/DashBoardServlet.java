package servlet;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;             
import jakarta.servlet.http.*; 

import credit.*;
import depense.*;

public class DashBoardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            List<Credit> allCredit = Credit.findAll();
            List<Depense> depenses = Depense.findAllOrderByDate();

            req.setAttribute("credits", allCredit);
            req.setAttribute("depenses", depenses);

            RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }
}
