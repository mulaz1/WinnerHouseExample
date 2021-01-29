package it.mulaz1.winery.classes;

public class Wine {
    private String _name;
    private String _wineProducer;
    private Integer _year = 1986;
    private String _technicalNotes;
    private String _vineType;

    public Wine(final String name, final Integer year, final String techNotes, final String vineType, final String wineProducer) {
        _name = name;
        _year = year;
        _wineProducer = wineProducer;
        _technicalNotes = techNotes;
        _vineType = vineType;
    }

    public Wine(final String name, final Integer year) {
        _name = name;
        _year = year;
        _technicalNotes = "";
        _vineType = "";
    }
    
    /**
     * 
     * @return Wine name
     */
    public String getName() {
        return _name;
    }

    /**
     * 
     * @return Wine year
     */
    public Integer getYear() {
        return _year;
    }

    /**
     * 
     * @return Wine technical note
     */
    public String getTechnicalNotes() {
        return _technicalNotes;
    }

    /**
     * 
     * @return Wine type
     */
    public String getVineType() {
        return _vineType;
    }
    
    /**
     * 
     * @return Wine producer
     */
    public String getWineProducer() {
    	return _wineProducer;
    }
    
}
