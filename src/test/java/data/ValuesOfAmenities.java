package data;

public enum ValuesOfAmenities {
    BEACH_FRONT("169"),
    FREE_WIFI("280"),
    AIR_CONDITIONING("4"),
    PETS_ALLOWED("37"),
    STREET_PARKING("38"),
    SWIMMING_POOL("6"),
    KITCHEN("7"),
    WASHING_MACHINE("13"),
    HOT_TUB("11"),
    SUITABLE_FOR_CHILDREN("38");

    private final String paramValue;

    ValuesOfAmenities(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamValue() {
        return paramValue;
    }

    @Override
    public String toString() {
        return paramValue;
    }
}

