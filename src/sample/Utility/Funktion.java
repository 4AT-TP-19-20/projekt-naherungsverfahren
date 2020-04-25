package sample.Utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Funktion {

    /**
     * Derives a function with an API-Call to the webpage WolframAlpha
     *
     * @param funktion - The function to be derived
     * @return String - Returns on success the derived function
     * @throws IOException - If the webpage is not available
     */
    public static String derive(String funktion) throws IOException {
        String url = "https://api.wolframalpha.com/v1/result?i=derivative+";
        try {
            url += URLEncoder.encode(funktion, "UTF-8") + "&appid=8PAWXG-AUU3VAPX3H";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        Document doc = Jsoup.connect(url).get();

        return doc.body().getAllElements().get(0).html();
    }
}
