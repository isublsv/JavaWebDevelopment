package by.epam.string.locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HamletInternational {

    private static final Logger LOGGER =
            LogManager.getLogger(HamletInternational.class);

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            LOGGER.debug("1 — английский /n 2 — белорусский \n любой " + "—" + " русский ");
            char c = 0;

            Scanner sc = new Scanner(System.in);
            c = sc.nextLine().charAt(0);

            String country = "";
            String language = "";
            if (c == '1') {
                country = "US";
                language = "EN";
            } else if (c == '2') {
                country = "BY";
                language = "BE";
            }

            Locale current = new Locale(language, country);
            ResourceBundle rb = ResourceBundle.getBundle("property.text",
                    current);

            String s1 = rb.getString("str1");
            LOGGER.debug(s1);
            String s2 = rb.getString("str2");
            LOGGER.debug(s2);
        }
    }
}

