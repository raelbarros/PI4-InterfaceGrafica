package br.com.fabio.ui.clientes;

import br.com.fabio.model.clientes.Cliente;
import br.com.fabio.service.cliente.ServicoCliente;
import br.com.fabio.ui.principal.TelaPrincipal;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Tela de edição de detalhes de cliente
 */
public class TelaEditarCliente extends javax.swing.JInternalFrame {
    //Armazena o cliente em edição
    Cliente cliente = new Cliente();

    //Construtor e inicialização de componentes
    public TelaEditarCliente() {
        initComponents();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método de construção da tela gerado pelo GUI Builder
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        fieldNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fieldSobrenome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboGenero = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        fFieldDataNasc = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Editar Cliente");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel1.setText("Nome: ");

        jLabel2.setText("Sobrenome: ");

        jLabel3.setText("Data Nasc: ");

        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "Feminino", "Masculino" }));

        jLabel4.setText("Sexo: ");

        cancelar.setText("Fechar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        fFieldDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldNome)
                            .addComponent(fieldSobrenome)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fFieldDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboGenero, 0, 151, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoSalvar)
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fieldSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fFieldDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar)
                    .addComponent(cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Listener do botão cancelar
    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    //Listener do botão salvar
    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        //Configura os novos valores dos campos de edição da tela
        //para o objeto de cliente, a fim de passá-lo para o serviço
        //e realizar as atualizações no DAO
        cliente.setNome(fieldNome.getText());
        cliente.setSobrenome(fieldSobrenome.getText());        
        
        Date data = null;
        try {
            data =  (Date)fFieldDataNasc.getValue();
        } catch (Exception e) {
            
        }
        cliente.setDataNascimento(data);
        
        cliente.setGenero((String)comboGenero.getSelectedItem());
        
        try {
            //Chama o serviço para realizar as alterações necessárias
            ServicoCliente.getInstance().atualizarCliente(cliente);
        }
        catch(Exception e) {
            //Exibe alguma mensagem de erro que pode ter vindo do serviço
            JOptionPane.showMessageDialog(rootPane, e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }        
        
        //Atualiza a tela de consulta de clientes, caso possível. Para isso,
        //obtém o "top level ancestor" (ou seja, o componente pai mais acima
        //do formulário, no nosso caso, o desktop) para conseguir o frame
        //de consulta e daí solicitar a atualização da lista através da
        //chamada de seu método público de atualização
        try {
            if (this.getDesktopPane().getTopLevelAncestor()
                    instanceof TelaPrincipal) {
                TelaPrincipal principal = (TelaPrincipal) this.
                        getDesktopPane().getTopLevelAncestor();
                if (principal != null) {
                    principal.getConsultarClientes().refreshList();                
                }
            }
        }
        catch(Exception e) {
            //Exibe erros de atualização da lista no
            //console, mas esconde-os do usuário
            e.printStackTrace();
        }
        
        JOptionPane.showMessageDialog(rootPane, "Cliente atualizado com sucesso",
                "Cadastro atualizado", JOptionPane.INFORMATION_MESSAGE);        
        this.dispose();
    }//GEN-LAST:event_botaoSalvarActionPerformed

    //Listener de abertura da janela. Aproveita o evento para obter os valores
    //do cliente em edição e passa-os para os campos de edição da tela
    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        fieldNome.setText(cliente.getNome());
        fieldSobrenome.setText(cliente.getSobrenome());
        fFieldDataNasc.setValue(cliente.getDataNascimento());
        for (int i = 0; i < comboGenero.getItemCount(); i++) {
            if (comboGenero.getItemAt(i).equals(cliente.getGenero())) {
                comboGenero.setSelectedIndex(i);
                break;
            }
        }
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JButton cancelar;
    private javax.swing.JComboBox<String> comboGenero;
    private javax.swing.JFormattedTextField fFieldDataNasc;
    private javax.swing.JTextField fieldNome;
    private javax.swing.JTextField fieldSobrenome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
