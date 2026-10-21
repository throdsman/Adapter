 
package Control;

import Model.DBConnection;
import Model.NetworkResponseEnum;
import Model.PasswordModifier;
import Model.User.UserBuilder;
import Model.UserDB;
import Model.UserRole;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
// crud
public class signup_servlet extends HttpServlet
{
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        NetworkResponseEnum state = NetworkResponseEnum.none;
        
        try
        { 
            Connection conn = DBConnection.getInstance().createConnection();
 
            Statement statement = conn.createStatement();
             
            String email = request.getParameter("email").replaceAll("\\s+", "");
             
            if (UserDB.DoesUserExist(email))
            {
                state = NetworkResponseEnum.AlreadySignin;
            }
            else
            {
                state = NetworkResponseEnum.RegisterValid;
            }
              
            switch (state)
            {
                case NetworkResponseEnum.AlreadySignin:
                {
                    request.setAttribute("errorMessage", "Email already registered");
                    request.getRequestDispatcher("createAccount.jsp").forward(request, response);
                    break;
                }
                case NetworkResponseEnum.RegisterValid:
                {
                    UserBuilder ub = new UserBuilder();
                    
                    ub.setName(request.getParameter("name"));
                    ub.setEmail(email);
                    ub.setId(0);
                    ub.setPass(PasswordModifier.SecurePassword(request.getParameter("password")));
                    ub.setRole(UserRole.Basic); // every new user is a basic
                    ub.setAge(Integer.valueOf(request.getParameter("age")));
                    
                    System.out.println( UserDB.add(ub.getUser()) + " row inserted!!!!!!!!!!!!!!!!!!!!!!");
                    
                    response.sendRedirect("createAccount.jsp?success=true");
                    break;
                }
            }
            return;
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("Connection NO!!!!!!!!!!!!!!!!");
            request.getRequestDispatcher("createAccount.jsp").forward(request, response);
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
        return "Create account helper";
    } 

}
