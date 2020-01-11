/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package discoveringlatentsemantic;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Elcot
 */
public class Main {
    public static void main(String[] args) 
    {        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try
        {                    			
            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
                                                
            MainFrame af=new MainFrame();
            af.setTitle("HomePage");
            af.setVisible(true);
            af.setResizable(false);                         
	}
	catch (Exception ex)
	{            
            //System.out.println(ex);
	}   
    }
}
