/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.it;

import static com.sun.javafx.css.SizeUnits.PC;
import java.sql.SQLException;
import javax.swing.UIManager;
import sun.rmi.runtime.Log;

/**
 *
 * @author Young
 */
public class ToolsIT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ToolsIT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ToolsIT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ToolsIT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ToolsIT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        MainFrame mainframe = new MainFrame();
        mainframe.setVisible(true);
    }
}
