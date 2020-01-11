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
import javax.swing.JOptionPane;
import textsimilarity.TextSimilarity;

/**
 *
 * @author Elcot
 */
public class KPrototypeClustering extends javax.swing.JFrame {

    /**
     * Creates new form KPrototypeClustering
     */
    
    public static long kprotoexetime=0;
    
    public KPrototypeClustering() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        jLabel1.setFont(new java.awt.Font("Andalus", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("K-Prototype Clustering");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jLabel1)
                .addContainerGap(191, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(30, 30, 30))
        );

        jButton1.setText("K-Prototype Clustering");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String path = "Web Documents/";  
        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 
        String content = "";
        String fnames = "";
        for (int i = 0; i < listOfFiles.length; i++) 
        { 
            if (listOfFiles[i].isFile()) 
            {
                files = listOfFiles[i].getName();
                fnames += files + "\n";
                String fname1="Web Documents/"+files;
                String convertrainingdoc=browse(fname1); 
                convertrainingdoc = convertrainingdoc.replace(",", " ");
                convertrainingdoc = convertrainingdoc.replace("\n", " ");
                content += convertrainingdoc  + "\n";
            }
        }
        content = content.substring(0,content.lastIndexOf("\n"));
        fnames = fnames.substring(0,fnames.lastIndexOf("\n"));
        System.err.println("149.........."+content);
        Kprototype kptype = new Kprototype();
//        long usedMemoryStart = Runtime.getRuntime().freeMemory();
//        long starttime=System.currentTimeMillis();
        String result = kptype.clustering(content, fnames);
//        long stoptime=System.currentTimeMillis();
//        long usedMemoryEnd = Runtime.getRuntime().freeMemory();
//        Details.kprorotypeSpace = usedMemoryEnd - usedMemoryStart;
//        KPrototypeClustering.kprotoexetime=stoptime-starttime;
        jTextArea1.setText(Kprototype.result);


    }//GEN-LAST:event_jButton1ActionPerformed

    
    public String readFile(String fileName)throws Exception{
        File file = new File("Web Documents\\"+fileName);
        FileInputStream fis = new FileInputStream(file);
        byte data[] = new byte[fis.available()];
        fis.read(data);
        fis.close();
        String str = new String(data);
        return str;
    }
    public ArrayList readFileName(){ 
        ArrayList fileNames = new ArrayList();
        String path = "Web Documents/";  
        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            String name = listOfFiles[i].getName();
            fileNames.add(name);    
        }
        System.out.println("fileNames : " + fileNames);
        return fileNames;
    }
    public String assignCluster() throws Exception{
        TextSimilarity ts = new TextSimilarity();
        ArrayList fileNames = readFileName();
        int k;
        do{
            k = Integer.parseInt(JOptionPane.showInputDialog("Enter no of cluster : "));
        }while(k <= 1 || k >= 10);
        ArrayList cluster[] = new ArrayList[k];
        int i = 0;
        for (; i < cluster.length; i++) {
            cluster[i] = new ArrayList();
            cluster[i].add(fileNames.get(i));
        }
        ArrayList percent = new ArrayList();
        
        for (int fileCtr = k; fileCtr < fileNames.size(); fileCtr++) {
            String compareFileName = fileNames.get(fileCtr).toString();
            for (int c = 0; c < cluster.length; c++) {
                String clusterFileName = cluster[c].get(0).toString();
                double similarity = 0;
                String str1 = readFile(clusterFileName);
                String str2 = readFile(compareFileName);
                similarity = ts.similarity(str1, str2);
                percent.add(similarity);
            }
            double max = Double.parseDouble(Collections.max(percent).toString());
            int index = percent.indexOf(max);
            cluster[index].add(compareFileName);
            percent = new ArrayList();
        }
        String result = "";
        for (int j = 0; j < cluster.length; j++) {
            ArrayList c = cluster[j];
            int n = j+1;
            result += "*********************Cluster " + n + "**************************\n";
            for (int l = 0; l < c.size(); l++) {
                String fname = c.get(l).toString();
                result += fname + "\n";
            }
            result += "*************************************************************\n\n";
        }
        System.out.println("Result : ");
        System.out.println(result);
        return result;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KPrototypeClustering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KPrototypeClustering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KPrototypeClustering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KPrototypeClustering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KPrototypeClustering().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
