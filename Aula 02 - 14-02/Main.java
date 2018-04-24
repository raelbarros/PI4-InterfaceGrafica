package br.com.israel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

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
        
        JLabel label01 = new JLabel("Paulo");
        JLabel label02 = new JLabel("é um");
        String html = "<html><body><font color=red><i>viadao</i></font></body></html>";
        JLabel label03 = new JLabel(html);
        
        JLabel txtInput = new JLabel();
        
        JButton btn = new JButton("Botão");
        
        JTextField txtField = new JTextField(10);
        
        String lala = txtField.getText();
        txtInput.setText(lala);
        

        // Comando para finalizar execução ao fechar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centralização da tela
        frame.setLocationRelativeTo(null);

        // Add intens na Frame
        frame.getContentPane().add(panel);
        panel.add(label01);
        panel.add(label02);
        panel.add(label03);
        panel.add(txtField);
        panel.add(txtInput);
        panel.add(btn);

        // Comandos para exibir na tela
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300, 150));

    }
    
    public void click(ActionEvent event){
    }

}
