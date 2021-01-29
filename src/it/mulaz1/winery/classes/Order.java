package it.mulaz1.winery.classes;

/**
 * {@code Order} is service class, that contains username, wine name and quantity for single order
 * @author mulaz1
 *
 */
public class Order {

	private String _username;
	private String _wineName;
	private Integer _quantity;
	private String _date;
	
	
	public Order(String username,String wineName,Integer quantity,String date) {
		_username = username;
		_wineName = wineName;
		_quantity = quantity;
		_date = date;
	}
	
	/**
	 * 
	 * @return Username that make order
	 */
	public String getUsername() {
		return _username;	
	}
	
	/**
	 * 
	 * @return Order wine name
	 */
	public String getWineName() {
		return _wineName;
	}
	
	/**
	 * 
	 * @return Order quantity
	 */
	public Integer getQuantity() {
		return _quantity;
	}
	
	/**
	 * 
	 * @return Date on which the order was placed 
	 */
	public String getDate() {
		return _date;
	}

}
