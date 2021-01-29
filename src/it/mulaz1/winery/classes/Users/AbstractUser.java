package it.mulaz1.winery.classes.Users;

public class AbstractUser {

    private String _name;
    private String _username;
    private String _password;
    private String _email;
    
    
    public AbstractUser(String name, String username,String email, String password) {
        _name = name;
        _username = username;
        _email = email;
        _password = password;
    }
    
    /**
     *
     * @return User name
     */
    public String getName() {
        return _name;
    }

    /**
     * 
     * @return User username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * 
     * @return User password
     */
    public String getPassword() {
        return _password;
    }
    
    /**
     * 
     * @return User Email
     */
    public String getEmail() {
    	return _email;
    }
}
