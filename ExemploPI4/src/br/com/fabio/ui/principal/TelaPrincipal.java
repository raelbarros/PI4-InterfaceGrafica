package br.com.fabio.ui.principal;

import br.com.fabio.ui.clientes.TelaCadastrarCliente;
import br.com.fabio.ui.clientes.TelaConsultarClientes;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

/**
 * Tela principal do sistema
 */
public class TelaPrincipal extends javax.swing.JFrame {

    private TelaCadastrarCliente cadastrarCliente = null;
    private TelaConsultarClientes consultarClientes = null;

    /**
     * Construtor e inicialização de componentes
     */
    public TelaPrincipal() {
        
        initComponents();
        //Faz com que a janela seja aberta no meio da tela
        this.setLocationRelativeTo(null);
    }

    //Métodos de acesso para os formulários abertos pela tela principal
    public TelaCadastrarCliente getCadastrarCliente() {
        return cadastrarCliente;
    }

    public void setCadastrarCliente(TelaCadastrarCliente cadastrarCliente) {
        this.cadastrarCliente = cadastrarCliente;
    }

    public TelaConsultarClientes getConsultarClientes() {
        return consultarClientes;
    }

    public void setConsultarClientes(TelaConsultarClientes consultarClientes) {
        this.consultarClientes = consultarClientes;
    }    

    /**
     * Código de construção dos componentes do GUI Builder
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        barraMenus = new javax.swing.JMenuBar();
        menuCliente = new javax.swing.JMenu();
        menuCadastrarCliente = new javax.swing.JMenuItem();
        menuConsultarCliente = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Clientes");

        desktop.setDoubleBuffered(true);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1036, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );

        menuCliente.setText("Cliente");

        menuCadastrarCliente.setText("Cadastrar Cliente");
        menuCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastrarClienteActionPerformed(evt);
            }
        });
        menuCliente.add(menuCadastrarCliente);

        menuConsultarCliente.setText("Consultar Clientes");
        menuConsultarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultarClienteActionPerformed(evt);
            }
        });
        menuCliente.add(menuConsultarCliente);

        barraMenus.add(menuCliente);

        setJMenuBar(barraMenus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Listener do item de menu
    private void menuCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastrarClienteActionPerformed
        //Verifica se não há uma janela de cadastro de cliente visível.
        //Caso afirmativo, cria uma janela de cadastro de cliente e a exibe
        if (cadastrarCliente == null || !cadastrarCliente.isDisplayable()) {
            cadastrarCliente = new TelaCadastrarCliente();
            desktop.add(cadastrarCliente);
            this.openFrameInCenter(cadastrarCliente);
        }
        cadastrarCliente.toFront();
    }//GEN-LAST:event_menuCadastrarClienteActionPerformed

    //Listener do item de menu
    private void menuConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultarClienteActionPerformed
        //Verifica se não há uma janela de consulta de cliente visível.
        //Caso afirmativo, cria uma janela de consulta de cliente e a exibe
        if (consultarClientes == null || !consultarClientes.isDisplayable()) {
            consultarClientes = new TelaConsultarClientes();
            desktop.add(consultarClientes);
            this.openFrameInCenter(consultarClientes);
        }
        consultarClientes.toFront();
    }//GEN-LAST:event_menuConsultarClienteActionPerformed

    //Abre um internal frame centralizado na tela
    public void openFrameInCenter(JInternalFrame jif) {
        Dimension desktopSize = desktop.getSize();
        Dimension jInternalFrameSize = jif.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }

    /**
     * Método de inicialização da classe e do sistema
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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenus;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuItem menuCadastrarCliente;
    private javax.swing.JMenu menuCliente;
    private javax.swing.JMenuItem menuConsultarCliente;
    // End of variables declaration//GEN-END:variables
}
