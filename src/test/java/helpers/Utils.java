package helpers;

import com.codeborne.selenide.WebDriverRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Utils {
    public static String getTodayDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getTomorrowDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
    }

    public static ArrayList<String> getAppliedAmenitiesList(){
        return new ArrayList<>(Arrays.asList((WebDriverRunner.getWebDriver().getCurrentUrl())
                .split("amenities=")[1].split("&")[0].split("-")));
    }

}
