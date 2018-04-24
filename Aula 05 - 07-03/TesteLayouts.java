package aula05;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TesteLayouts {

    public static void main(String[] args) {

        Runnable thread = new Runnable() {
            public void run() {
                criarGUI();
            }
        ;
        };
        SwingUtilities.invokeLater(thread);

    }

    public static void criarGUI() {
        JFrame frame = new JFrame();

        frame.setMinimumSize(new Dimension(400, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Aula05");

        
        //---- Border Layouts
//        JPanel panel = new JPanel();
//        frame.getContentPane().add(panel);
//        panel.setLayout(new BorderLayout());
//        
//        JButton btn01 = new JButton("Botao 01");
//        panel.add(btn01, BorderLayout.NORTH);
//        JButton btn02 = new JButton("Botao 02");
//        panel.add(btn02, BorderLayout.SOUTH);
//        JButton btn03 = new JButton("Botao 03");
//        panel.add(btn03, BorderLayout.WEST);
//        
        //---- FlowLayout
//        JPanel panel = new JPanel();
//        frame.getContentPane().add(panel);
//        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        
//        JButton btn01 = new JButton("Botao 01");
//        panel.add(btn01);
//        JButton btn02 = new JButton("Botao 02");
//        panel.add(btn02);
//        JButton btn03 = new JButton("Botao 03");
//        panel.add(btn03);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(new GridLayout(2,0));
        
        JButton btn01 = new JButton("Botao 01");
        panel.add(btn01);
        JButton btn02 = new JButton("Botao 02");
        panel.add(btn02);
        JButton btn03 = new JButton("Botao 03");
        panel.add(btn03);


        
        frame.pack();
        frame.setVisible(true);

    }

}
