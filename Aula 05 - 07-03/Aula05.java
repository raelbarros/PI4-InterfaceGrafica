package aula05;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Aula05 {

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

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JButton btn = new JButton("Botao");
        btn.setBounds(50, 25, 100, 25);
        panel.add(btn);

        JTextField txtField = new JTextField();
        txtField.setBounds(220, 150, 70, 20);
        panel.add(txtField);
        
        String[] opcoesCombo = {"Selecione"};
        JComboBox comboBox = new JComboBox(opcoesCombo);
        comboBox.setBounds(50, 300, 150, 100);
        panel.add(comboBox);
        
        JLabel label = new JLabel("Exemplo de Label");
        label.setBounds(250, 480, 100, 100);
        panel.add(label);
        
        
        
        frame.pack();
        frame.setVisible(true);

    }

}
