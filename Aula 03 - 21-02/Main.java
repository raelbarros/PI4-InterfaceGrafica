package br.com.israel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        // Classe anonima
        Runnable thread = new Runnable() {
            public void run() {
                criarGUI();
            }
        };

        SwingUtilities.invokeLater(thread);
    }

    public static void criarGUI() {
        // Instanciando os objetos
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Teste");
        JButton btn = new JButton("Botão");
        final JTextField txtField = new JTextField(10);
        JCheckBox checkbox = new JCheckBox("Item 01");
        
        checkbox.setSelected(true);
        
        frame.getContentPane().add(panel);
        panel.add(label);
        panel.add(txtField);
        panel.add(btn);
        panel.add(checkbox);

        // Classe anonima
        /*ActionListener l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("lala");
            }
        };
                
        btn.addActionListener(l);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(frame, txtField.getText());
            }
        });*/

        btn.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog(frame, txtField.getText());
        });

        // Comandos para exibir na tela
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300, 150));
        // Comando para finalizar execução ao fechar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Centralização da tela
        frame.setLocationRelativeTo(null);

        frame.setTitle("lalala");
    }

}
