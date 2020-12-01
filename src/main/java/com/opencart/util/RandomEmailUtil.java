package com.opencart.util;

import com.opencart.enums.URLs;
import com.opencart.helpers.JsonHelper;


import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.opencart.helpers.JsonHelper.getJsonByURL;

public class RandomEmailUtil {

    public static String email;

    private static String getSubstringWithRegularExpression(String string, String regularExpression) {
        Pattern r = Pattern.compile(regularExpression);
        Matcher m = r.matcher(string);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    public static String generateRandomEmail() {
        try {
            HttpResponse<String> response = getJsonByURL(URLs.TEMP_MAILS_URL.getValue() + "genRandomMailbox&count=1");
            email = response.body().substring(2, response.body().length() - 2);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return email;
    }

    public static String getChangePasswordURL() {
        String[] emailDomains = email.split("@");
        String URL = URLs.TEMP_MAILS_URL.getValue() + "getMessages&login=" + emailDomains[0] + "&domain=" + emailDomains[1];
        try {
            String emailId = "";
            while (emailId.equals("")) {
                HttpResponse<String> response = getJsonByURL(URL);
                emailId = RandomEmailUtil.getSubstringWithRegularExpression(response.body(), "\"id\":(\\d+),\"from\":\".+\",\"subject\":\"Your Store - Password reset request\"");
            }
            URL = URLs.TEMP_MAILS_URL.getValue() + "readMessage&login=" + emailDomains[0] + "&domain=" + emailDomains[1] + "&id=" + emailId;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return RandomEmailUtil.getSubstringWithRegularExpression(JsonHelper.getJsonValueByURLAndKey(URL, "textBody"), "(http.*?)\\n\\n");
    }
}
