/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 *
 * @author nuts001
 */
public class Translator {

    /**
     * @param args the command line arguments
     */
    public static String translate(String text){
        String translatedText = null;
        try{
            Translate.setClientId(com.auth.Authenticator.getClientId());
            Translate.setClientSecret(com.auth.Authenticator.getClientSecret());
            translatedText = Translate.execute(text, Language.HINDI, Language.ENGLISH);
        }catch(Exception e){
            e.printStackTrace();
        }
        return translatedText;
    }
}
