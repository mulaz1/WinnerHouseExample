package it.mulaz1.winery.classes.Users;

import it.mulaz1.winery.classes.Wine;

/** 
 * {@code Employee} is Employee class
 * @author simo
 *
 */
public class Employee extends AbstractUser {
    public Employee(String name, String username,String email, String password) {
        super(name, username,email, password);
    }

    /** 
     * 
     * @param wine Specifies which wine is finishing or is finished
     */
    public void goBuyNewBottles(Wine wine) {
        System.out.println("Employee " + getName() + " received a new message: " + wine.getName() + " is finished! I have to go buy new bottles!");
    }
}
