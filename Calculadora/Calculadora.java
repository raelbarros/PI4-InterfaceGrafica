package calculadora;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Calculadora {

    public static void main(String[] args) {

        Runnable thread = new Runnable() {
            public void run() {
                criarGUI();
            }
        };

        SwingUtilities.invokeLater(thread);

    }

    public static void criarGUI() {
        // Frame
        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(500, 150));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Calculadora");
        frame.pack();
        frame.setVisible(true);

        // Painel Principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        frame.getContentPane().add(panelPrincipal);

        // Painel Superior
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelPrincipal.add(panelTop, BorderLayout.NORTH);

        // Btns radios para selecionar as operacoes
        JRadioButton soma = new JRadioButton("Soma");
        JRadioButton sub = new JRadioButton("Subtração");
        JRadioButton mult = new JRadioButton("Multiplicação");
        JRadioButton div = new JRadioButton("Divisão");
       
        panelTop.add(soma);
        panelTop.add(sub);
        panelTop.add(mult);
        panelTop.add(div);

        // Grupos dos btn radio
        ButtonGroup btnsRadio = new ButtonGroup();
        
        btnsRadio.add(soma);
        btnsRadio.add(sub);
        btnsRadio.add(mult);
        btnsRadio.add(div);

        // Painel Centro
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelPrincipal.add(panelCenter, BorderLayout.CENTER);

        // Labels
        JLabel valor01 = new JLabel("Valor 01: ");
        JLabel valor02 = new JLabel("Valor 02: ");

        // TextField para receber os valores digitados
        JTextField txtField01 = new JTextField(10);
        JTextField txtField02 = new JTextField(10);

        // Botao para calcular o resultado
        JButton btnResult = new JButton("Resultado");

        panelCenter.add(valor01);
        panelCenter.add(txtField01);
        panelCenter.add(valor02);
        panelCenter.add(txtField02);
        panelCenter.add(btnResult);

        // Painel Inferior
        JPanel panelBotton = new JPanel();
        panelBotton.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelPrincipal.add(panelBotton, BorderLayout.SOUTH);

        // Botao para Limpar e Sair
        JButton btnLimpar = new JButton("Limpar");
        JButton btnSair = new JButton("Sair");

        panelBotton.add(btnLimpar);
        panelBotton.add(btnSair);

        // Action botao Resultado
        btnResult.addActionListener((al) -> {
            boolean isSelected = isSelect(soma.isSelected(), sub.isSelected(), mult.isSelected(), div.isSelected());
            boolean isNumber = isNumeric(txtField01.getText(), txtField02.getText());

            if (isSelected && isNumber) {
                float val01 = convertStringToNumber(txtField01.getText());
                float val02 = convertStringToNumber(txtField02.getText());

                if (soma.isSelected()) {
                    showMsg("O resultado da sua soma é: " + (val01 + val02));
                } else if (sub.isSelected()) {
                    showMsg("O resultado da sua subtração é: " + (val01 - val02));
                } else if (mult.isSelected()) {
                    showMsg("O resultado da sua subtração é: " + (val01 * val02));
                } else if (div.isSelected()) {
                    if (val02 == 0) {
                        showMsg("Não é possivel fazer divisão por 0");
                    } else {
                        showMsg("O resultado da sua divisão é: " + (val01 / val02));
                    }
                }
            }

        });

        // Action botao Limpar
        btnLimpar.addActionListener((al) -> {
            txtField01.setText("");
            txtField02.setText("");
            btnsRadio.clearSelection();
        });
        
        // Action botao Sair
        btnSair.addActionListener((al) -> {
            frame.dispose();
        });

    }

    public static boolean IsNotEmpty(String val01, String val02) {
        if (val01.isEmpty() && val02.isEmpty()) {
            showMsg("Insira algum valor nos campos");
            return false;
        } else if (!val01.isEmpty() && val02.isEmpty()) {
            showMsg("Insira algum valor no campo 2");
            return false;
        } else if (val01.isEmpty() && !val02.isEmpty()) {
            showMsg("Insira algum valor no campo 1");
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String x, String y) {
        try {
            if (IsNotEmpty(x, y)) {
                Float.parseFloat(x);
                Float.parseFloat(y);
                return true;
            }
        } catch (NumberFormatException ex) {
            showMsg("Insira apenas numeros");
        }
        return false;
    }

    public static boolean isSelect(boolean soma, boolean sub, boolean mult, boolean div) {
        if (soma || sub || mult || div) {
            return true;
        } else {
            showMsg("Selecione alguma operação");
            return false;
        }
    }

    public static float convertStringToNumber(String val) {
        return Float.parseFloat(val);

    }

    public static void showMsg(String mgs) {
        JOptionPane.showMessageDialog(null, mgs);
    }

}
