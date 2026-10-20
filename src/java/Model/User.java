 
package Model;
 
public class User implements DataBaseable
{
    private String name;
    private String pass;
    private String email;
    private UserRole role;
    private int id;

    private User(String name, String pass, String email, UserRole role, int id)
    {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.role = role;
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public String getPass()
    {
        return pass;
    }

    public String getEmail()
    {
        return email;
    }

    public UserRole getRole()
    {
        return role;
    }

    public int getId()
    {
        return id;
    }
    
    public String toACommaValueStd()
    {
        return "User{";
    }
    
    public static class UserBuilder
    {
        private String name;
        private String pass;
        private String email;
        private UserRole role;
        private int id;
        
        public User getUser()
        {
            return new User(name, pass, email, role, id);
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public void setPass(String pass)
        {
            this.pass = pass;
        }

        public void setEmail(String email)
        {
            this.email = email;
        }

        public void setRole(UserRole role)
        {
            this.role = role;
        }

        public void setId(int id)
        {
            this.id = id;
        }
    }
}
