package it.mulaz1.winery.classes;

/**
 * {@code Stock} is service class, contains wine istance and quantity in stock of that wine
 *
 */
public class Stock {
	
	private Wine _wine;
	private int _quantity;
	
	public Stock(final Wine wine,final int quantity) {
		_wine = wine;
		_quantity = quantity;
	}
	
	/**
	 * 
	 * @return Wine Instance
	 */
	public Wine getWine() {
		
		return _wine;
	}
	
	/**
	 * return Wine quantity in stock
	 * @return
	 */
	public Integer getQuantity() {
		
		return _quantity;
	}
	
	/**
	 * 
	 * @return Wine name
	 */
	public String getName() {
        return _wine.getName();
    }
	/**
	 * 
	 * @return Wine year
	 */
    public Integer getYear() {
        return _wine.getYear();
    }

    /**
     * 
     * @return Wine techical note
     */
    public String getTechnicalNotes() {
        return _wine.getTechnicalNotes();
    }

    /**
     * 
     * @return Wine type 
     */
    public String getVineType() {
        return _wine.getVineType();
    }
    
    /**
     * 
     * @return Wine producer
     */
    public String getWineProducer() {
    	return _wine.getWineProducer();
    }	
}
