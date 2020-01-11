/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package languagedetection;

import org.apache.tika.language.LanguageIdentifier;

/**
 *
 * @author nuts001
 */
public class LanguageDetection {

    /**
     * @param args the command line arguments
     */
    public static String detect(String text) {
       LanguageIdentifier identifier = new LanguageIdentifier(text);
       String language = identifier.getLanguage();
       return language;
    }
    
}
