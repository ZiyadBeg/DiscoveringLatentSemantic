/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package discoveringlatentsemantic;

import java.util.ArrayList;

/**
 *
 * @author nuts001
 */
public class Preprocess {
    public String removeStemmer(ArrayList afterstopwords){
        String p = "appl";
        String m = "mous";
        Stemmer stem=new Stemmer();
        ArrayList afterstemmer = new ArrayList();
        String steRes = "";
        String g2 = "";
        for (int i = 0; i < afterstopwords.size(); i++) {
            g2 = afterstopwords.get(i).toString();
            g2 = g2.replaceAll("[^a-zA-Z 0-9]+","");
                g2=g2.replaceAll("[0-9]+","");
                String f[]=g2.split(" ");
                String rr="";
                for(int j=0;j<f.length;j++)
                {
                    char wordArray[] = f[j].toCharArray();
                    stem.add( wordArray, wordArray.length );
                    stem.stem();
                    if(stem.toString().equalsIgnoreCase(p) || stem.toString().equalsIgnoreCase(m)){
                        rr=rr+stem.toString() + "e" +" ";
                    }else{
                        rr=rr+stem.toString()+" ";  
                    }
                     	
                }
                rr=rr.trim();
                System.out.println("stem == "+i+" : "+rr);
                afterstemmer.add(rr);
                steRes=steRes+rr+" ";
            
        }
         return steRes;   
    }
}
