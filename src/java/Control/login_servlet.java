
package Control;
 
import Model.PasswordModifier;
import Model.User;
import Model.UserDB; 
import java.io.IOException; 
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
 
public class login_servlet extends HttpServlet
{  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        final String email = request.getParameter("email");
        final String pass = request.getParameter("password");
        
        try
       {
           User us = UserDB.GetUser(email);
           if (us == null)
           {
               request.setAttribute("errorMessage", "Correo/Contraseña invalido!");
               request.setAttribute("email", email);
               request.getRequestDispatcher("index.jsp").forward(request, response);
           } 
           
           String hashed = PasswordModifier.SecurePassword(pass);
            
            if (hashed.compareTo(us.getPass()) == 0)
            {
                //RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
                //dispatcher.forward(request, response);

                HttpSession session = request.getSession();

                session.setAttribute("currentUser", us.getName());

                session.setAttribute("currentRole", us.getRole().toString());

                session.setMaxInactiveInterval(15 * 60); // max 15 min of inactivity

                response.sendRedirect("dashboard.jsp"); 
            } 
           
       }
       catch (Exception e)
       {
               request.getRequestDispatcher("index.jsp").forward(request, response);
       }
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }
 
    @Override
    public String getServletInfo()
    {
        return "Short description";
    } 

}
