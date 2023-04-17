package data;

public enum QueryParamsName {
    PRICE_FROM("minAverageNightlyPrice="),
    PRICE_TO("maxAverageNightlyPrice="),
    NUMBER_OF_BEDS("numberOfBeds="),
    NUMBER_OF_BEDROOMS("numberOfBedrooms="),
    NUMBER_OF_BATHROOMS("numberOfBathrooms="),
    AMENITIES("amenities=");

    private final String paramName;

    QueryParamsName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }



    @Override
    public String toString() {
        return paramName;
    }
    }