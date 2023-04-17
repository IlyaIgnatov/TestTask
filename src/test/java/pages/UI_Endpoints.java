package pages;

import helpers.Utils;


public class UI_Endpoints {

    public static final String SEARCH_RESULT_PAGE = "/search?start=" + Utils.getTodayDate() +
            "&end=" + Utils.getTomorrowDate() + "&numberOfGuests=1";

    public static final String ALL_LISTING = "/all-listings";
}
