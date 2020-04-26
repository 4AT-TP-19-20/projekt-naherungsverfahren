package sample.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Funktion {

    /**
     * Derives a function with an API-Call to the webpage WolframAlpha
     *
     * @param funktion The function to be derived
     * @return String - Returns on success the derived function
     */
    public static String derive(String funktion) {
        String url = "https://api.wolframalpha.com/v1/result?i=derivative+";
        String res;
        try {
            url += URLEncoder.encode(funktion, "UTF-8") + "&appid=8PAWXG-AUU3VAPX3H";
            res = getHTML(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return res;
    }

    /**
     * Gets the HTML Code of an WebPage
     *
     * @param url - The WebPage
     * @return String - The HTML Page
     * @throws IOException - If website is not available
     */
    private static String getHTML(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        rd.close();

        return result.toString();
    }
}
