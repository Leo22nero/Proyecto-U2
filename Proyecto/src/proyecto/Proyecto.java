
package proyecto;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Proyecto {

 
    public static void main(String[] args) {
          FlatDarkLaf.setup();
          SwingUtilities.invokeLater(()->{
            
            Ventana ventana = new Ventana();
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(1200, 700);
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(false);
            ventana.setVisible(true);
            new Ventana().setVisible(true);
            
    });
    }
    
}
