package servlet;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;             
import jakarta.servlet.http.*;
import login.Login;
import credit.*;
import depense.*;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String n = req.getParameter("nom");
            String mdp = req.getParameter("mdp");
            if(Login.checkLogin(n, mdp)){
                HttpSession session = req.getSession();
                session.setAttribute("Auth", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("choix.jsp");
                dispatcher.forward(req, res);
            }
            else{
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }
}
