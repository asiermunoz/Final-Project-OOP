package ucab.edu.objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WordChecker {

    public boolean checkWord(String word) {
        try {
            Document document = Jsoup.connect("https://es.wiktionary.org/wiki/" + word).get();
            Elements head = document.select("#firstHeading");

            for (Element elem : head) {
                if(elem.text().equals(word)) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
