/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package discoveringlatentsemantic;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author nuts001
 */
public class Kprototype {
    public static ArrayList numericaldatas=new ArrayList();
    public static ArrayList categoricaldatas=new ArrayList();
    public static ArrayList combineddatas=new ArrayList();
    
    static String result = "";
    public static double DistanceofPandQ(String[] p, String q) 
    {
        int mismatch=0;
	for(int i=0;i<p.length;i++)                         //Simple Macthing Coefficient = number of mismatches / Total number of attributes
	{
            if(!(q.contains(p[i].trim())))
            {
		mismatch++;
            }
	}		
	double distance=(double)mismatch/(double)p.length;			
        return distance;
    }
    public static double euclideanDistanceofPandQ(String[] p, String[] q) {       //Euclideand distance=Sqrt of[((q1-p1)^2)*((q2-p2)^2)*.....*((qn-pn)^2)]
        double p1[]=new double[p.length];   
	double q1[]=new double[q.length];
	for(int i=0;i<p.length;i++)
	{
            p1[i]=Double.parseDouble(p[i].trim());
            q1[i]=Double.parseDouble(q[i].trim());
	}
	double outweight=0;
	for(int i=0;i<p1.length;i++)
	{
            outweight=outweight+((q1[i]-p1[i])*(q1[i]-p1[i]));            
	}
	double euclideandistance=Math.sqrt(outweight);
        return euclideandistance;
    }
    public static ArrayList[] kPrototypeforNumerical(String numerical, int k) 
    {
        
        ArrayList norepeat=new ArrayList();
            //Select initial k-means for k cluster         
        String sp[]=numerical.split("\n");
        System.out.println("sp.length1: "+sp.length);
        int len=sp.length/k;
        String initialkmeans[]=new String[k];
        ArrayList cluster[]=new ArrayList[k];
        int cou=0;
        for(int i=0;i<k;i++)
        {            
            if(cou<sp.length)
            {
                initialkmeans[i]=sp[cou];
                cou=cou+len;
                cluster[i]=new ArrayList();
                if(cou<sp.length)
                {
                    if(!(norepeat.contains(sp[cou].trim())))
                    {
                        if(!(cluster[i].contains(sp[cou].trim())))
                        {
                            cluster[i].add(sp[cou].trim());
                            norepeat.add(sp[cou].trim());
                        }
                    }
                }
            }            
        }
                
        for(int i=0;i<sp.length;i++)
        {
            //System.out.println(sp[i].trim());
            String p[]=sp[i].trim().split(",");
            ArrayList distanceonly=new ArrayList();
            ArrayList distwithclustid=new ArrayList();
            for(int j=0;j<initialkmeans.length;j++)
            {
                String q[]=initialkmeans[j].trim().split(",");
                double distance=euclideanDistanceofPandQ(p,q);
                distanceonly.add(distance);
                distwithclustid.add(distance+"#"+j);
            }
            Collections.sort(distanceonly,Collections.reverseOrder());
            String max=distanceonly.get(0).toString().trim();
            int clusterid=0;
            for(int j=0;j<distwithclustid.size();j++)
            {
                String diswithclusid[]=distwithclustid.get(j).toString().trim().split("#");
                if(max.trim().equals(diswithclusid[0].trim()))
                {
                    clusterid=Integer.parseInt(diswithclusid[1].trim());
                    //if(!(norepeat.contains(sp[i].trim())))
                    //{
                        
                    //}
                }
            }
            if(!(norepeat.contains(sp[i].trim())))
            {                
                if(!(cluster[clusterid].contains(sp[i].trim())))
                {
                    cluster[clusterid].add(sp[i].trim());            
                }
            }
            //System.out.println("clusterid: "+clusterid);
        }
        //System.out.println("norepeat.size1: "+norepeat.size());
        return cluster;
    }
    public static ArrayList[] kPrototypeforCategorical(String categorical, int k, String fnames) {
            //Select initial k-means for k cluster  
        String clusterWithFname = "";
        ArrayList norepeat=new ArrayList();
        String sp[]=categorical.split("\n");
        String fn[]=fnames.split("\n");
        System.out.println("sp.length2: "+sp.length);
        int len=sp.length/k;
        String initialkmeans[]=new String[k];
        ArrayList cluster[]=new ArrayList[k];
        int cou=0;
        for(int i=0;i<k;i++)
        {            
            if(cou<sp.length)
            {
                initialkmeans[i]=sp[cou];
                cou=cou+len;
                cluster[i]=new ArrayList();
                System.out.println("cou"+cou);
                if(cou<sp.length)
                {
                    if(!(norepeat.contains(sp[cou].trim())))
                    {
                        if(!(cluster[i].contains(sp[cou].trim())))
                        {
                            cluster[i].add(sp[cou].trim());
                            norepeat.add(sp[cou].trim());
                            
                        }
                    }
                }
            }            
        }
        //System.out.println(clusterWithFname); 
       
        for(int i=0;i<sp.length;i++)
        {  
        //    clusterWithFname += "****************cluster " + (i+1) + "\n";
            
            //System.out.println(sp[i].trim());
            String p[]=sp[i].trim().split(" ");
            ArrayList distanceonly=new ArrayList();
            ArrayList distwithclustid=new ArrayList();
            for(int j=0;j<initialkmeans.length;j++)
            {
                String q[]=initialkmeans[j].trim().split(" ");
                double distance=DistanceofPandQ(p,initialkmeans[j].trim());
                distanceonly.add(distance);
                distwithclustid.add(distance+"#"+j);
            }
            Collections.sort(distanceonly,Collections.reverseOrder());
            String max=distanceonly.get(0).toString().trim();
            int clusterid=0;
            for(int j=0;j<distwithclustid.size();j++)
            {
                String diswithclusid[]=distwithclustid.get(j).toString().trim().split("#");
                if(max.trim().equals(diswithclusid[0].trim()))
                {
                    clusterid=Integer.parseInt(diswithclusid[1].trim());                    
                }
            }
          
            if(!(norepeat.contains(sp[i].trim())))
            {
                if(!(cluster[clusterid].contains(sp[i].trim())))
                {
                    cluster[clusterid].add(sp[i].trim());     
                    //clusterWithFname += clusterid + "#"+i+ "\n";
                }
            }
          
            
           // clusterWithFname += "\n";
            
           // System.out.println("clusterid: "+clusterid);
        }
        //System.out.println("norepeat.size2: "+norepeat.size());
      //  System.out.println(clusterWithFname);
       clusteringWithFname(cluster);
        return cluster;
    }
    
    public static void clusteringWithFname(ArrayList cls[]){
        ArrayList fnames = new ArrayList();
        ArrayList contents = new ArrayList();
        String path = "Web Documents/";  
        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 
        for (int i = 0; i < listOfFiles.length; i++) 
        { 
            if (listOfFiles[i].isFile()) 
            {
                files = listOfFiles[i].getName();
                String fname1="Web Documents/"+files;
                String convertrainingdoc=browse(fname1); 
                convertrainingdoc = convertrainingdoc.replace(",", " ");
                convertrainingdoc = convertrainingdoc.replace("\n", " ");
                fnames.add(files);
                contents.add(convertrainingdoc);
            }
        }
        result = "";
        for (int i = 0; i < cls.length; i++) {
            result += "*********************Cluster"+ (i+1) + "**************************\n";
            for (int j = 0; j < cls[i].size(); j++) {
                String str = cls[i].get(j).toString();
            //    if(contents.contains(str)){
                    int index = contents.indexOf(str);
                    System.err.println("236.................."+index);
                    String filename = fnames.get(index).toString();
                     result += filename + "\n";
          //      }
            }
            
        }
        System.out.println(result);
    }
    private static String browse(String fname) {
        String ds="";
        try
        {
            File fe=new File(fname);		
            FileInputStream fis=new FileInputStream(fe);
            byte data[]=new byte[fis.available()];
            fis.read(data);
            fis.close();
            String str=new String(data);
            ds=str.trim();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ds;
    }
    
    public String clustering(String documents, String fnames){
        
        String numericatag = documents + "#0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n";
        String sk[]=numericatag.split("#");
        String numerical=sk[1].trim();
        String categorical=sk[0].trim();
        System.out.println("Numerical : " + numerical);
        System.out.println("categorical : " + categorical);
        String kp[]=numerical.split("\n");
        for(int i=0;i<kp.length;i++)
        {
            numericaldatas.add(kp[i].trim());
        }
        
        String kp1[]=categorical.split("\n");
        for(int i=0;i<kp1.length;i++)
        {
            categoricaldatas.add(kp1[i].trim());
        }
        

        String fork=JOptionPane.showInputDialog(new JFrame(), "Enter the Number of Clusters (K) to be Generated: ");
        int k=Integer.parseInt(fork.trim());   
        int kforc=k;
        double start = System.currentTimeMillis();
        
        
        String header9="================================================================================\n";
        String header10="                          Categorical Dataset\n";
        String header11="================================================================================\n";
            
                //k-Prototype Cluster for categorical dataset
                      
        ArrayList categoricalcluster[]=kPrototypeforCategorical(documents,k,fnames); 
        String catagclu="",header1213141516="";
        for(int i=0;i<categoricalcluster.length;i++)
        {
            String header12="--------------------------------------------------------------------------------\n";
            String header13="                          Cluster - "+(i+1)+"\n";
            String header14="--------------------------------------------------------------------------------\n";
            String ro="";
            System.out.println("categoricalcluster["+i+"].size(): "+categoricalcluster[i].size());
            for(int j=0;j<categoricalcluster[i].size();j++)
            {
                String s=categoricalcluster[i].get(j).toString().trim();
                ro=ro+s.trim()+"\n";
            }
            catagclu=catagclu+i+"@"+ro.substring(0,ro.lastIndexOf('\n'))+"#";
            String header15=ro.trim();
            String header16="\n--------------------------------------------------------------------------------\n";
            header1213141516=header1213141516+header12+header13+header14+header15+header16+"\n";
        }
        String result = "";
        String catagcluclustC=catagclu.substring(0,catagclu.lastIndexOf('#'));
       String cluster[] = catagcluclustC.split("#");
        for (int i = 0; i < cluster.length; i++) {
            String string = cluster[i];
            int cnum = Integer.parseInt(string.split("@")[0]);
            String content = string.split("@")[1];
            content = content.replaceAll("\t", " ");
            result += "*********************Cluster"+ (cnum+1) +"**************************\n" + content + "\n*************************************************************\n";
        }
        

        return result;
    }
    
}
