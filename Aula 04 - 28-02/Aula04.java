package aula04;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Aula04 {

    public static void main(String[] args) {
        Runnable thread = new Runnable() {
            public void run() {
                criarGUI();
            }
        };

        SwingUtilities.invokeLater(thread);
    }

    public static void criarGUI() {
        JFrame frame = new JFrame();

        frame.setMinimumSize(new Dimension(400, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Aula 04");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        JLabel label = new JLabel("Teste");
        panel.add(label);

        JTextField txtField = new JTextField(10);
        panel.add(txtField);

        JButton btn = new JButton("Botao");
        panel.add(btn);

        JCheckBox checkBox01 = new JCheckBox("Item 01");
        panel.add(checkBox01);

        JCheckBox checkBox02 = new JCheckBox("Item 02");
        checkBox02.isSelected();
        panel.add(checkBox02);

        JRadioButton radio01 = new JRadioButton("Opcao 01");
        panel.add(radio01);

        JRadioButton radio02 = new JRadioButton("Opcao 02");
        panel.add(radio02);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radio01);
        grupo.add(radio02);

        String[] opcoesCombo = {"Selecione", "Opcao 01", "Opcao 02", "Opcao 03", "Opcao 04"};
        JComboBox comboBox = new JComboBox(opcoesCombo);
        panel.add(comboBox);

        btn.addActionListener((ae) -> {
//            JOptionPane.showMessageDialog(frame, txtField.getText());
            JOptionPane.showMessageDialog(frame, "Opcao 01: " + radio01.isSelected() + "\nOpcao 02: " + radio02.isSelected() + "\nOpcao ComboBox: " + comboBox.getSelectedItem());

        });

//        checkBox01.addActionListener((ae) -> {
//            if (checkBox01.isSelected()) {
//                JOptionPane.showMessageDialog(frame, "OK");
//            }
//        });
        checkBox01.addItemListener((ie) -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                JOptionPane.showMessageDialog(frame, "OK");
            }
        });

        frame.pack();
        frame.setVisible(true);

    }

}
