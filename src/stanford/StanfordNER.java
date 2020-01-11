package stanford;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Standford Named Entity Demo
 *
 * @author Ganesh
 */
public class StanfordNER {

    /**
     * identify Name,organization location etc entities and return Map<List>
     *
     * @param text -- data
     * @param model - Stanford model names out of the three models
     * @return
     */
    static String output = "";

    public static LinkedHashMap<String, LinkedHashSet<String>> identifyNER(String text, String model) {
        LinkedHashMap<String, LinkedHashSet<String>> map = new <String, LinkedHashSet<String>>LinkedHashMap();
        String serializedClassifier = model;
        System.out.println("29......."+serializedClassifier);
        CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
        List<List<CoreLabel>> classify = classifier.classify(text);
        for (List<CoreLabel> coreLabels : classify) {
            for (CoreLabel coreLabel : coreLabels) {

                String word = coreLabel.word();
                String category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
                if (!"O".equals(category)) {
                    if (map.containsKey(category)) {
                        // key is already their just insert in arraylist
                        map.get(category).add(word);
                    } else {
                        LinkedHashSet<String> temp = new LinkedHashSet<String>();
                        temp.add(word);
                        map.put(category, temp);
                    }
                    System.out.println(word + ":" + category);
                    output += word + "\t" + category + "\n";
                }

            }

        }
        return map;
    }

    public static String main(String content) {
        String result = identifyNER(content, "D:\\model\\english.conll.4class.distsim.crf.ser.gz").toString();

        return result = "***********NER Result**************\n\n" + result + "\n\n" + "**********Recoganised Entity Names**********" + "\n\n" + "WORD\tCATEGORY\n\n" + output;
    }

}
