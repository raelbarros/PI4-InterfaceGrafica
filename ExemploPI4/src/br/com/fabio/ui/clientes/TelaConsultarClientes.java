package br.com.fabio.ui.clientes;

import br.com.fabio.model.clientes.Cliente;
import br.com.fabio.exceptions.ClienteException;
import br.com.fabio.service.cliente.ServicoCliente;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Tela de consulta de clientes
 */
public class TelaConsultarClientes extends javax.swing.JInternalFrame {

    //Instância do form de edição de clientes
    TelaEditarCliente formEditarCliente = new TelaEditarCliente();
    //Armazena a última pesquisa realizada
    String ultimaPesquisa = null;

    //Construtor e inicialização de componentes
    public TelaConsultarClientes() {
        initComponents();
        //Faz com que a coluna do ID não seja mostrada ao usuário.
        tabelaResultados.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaResultados.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaResultados.getColumnModel().getColumn(0).setWidth(0);
    }

    //Código gerado do GUI Builder
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPesquisa = new javax.swing.JLabel();
        fieldPesquisa = new javax.swing.JTextField();
        tabelaResultadosScroll = new javax.swing.JScrollPane();
        tabelaResultados = new javax.swing.JTable();
        buttonCancelar = new javax.swing.JButton();
        buttonPesquisar = new javax.swing.JButton();
        buttonAlterar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consultar Clientes");

        labelPesquisa.setText("Pesquisar:");

        tabelaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Sobrenome", "Idade", "Sexo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaResultados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelaResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaResultadosMouseClicked(evt);
            }
        });
        tabelaResultadosScroll.setViewportView(tabelaResultados);

        buttonCancelar.setText("Fechar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        buttonPesquisar.setText("Pesquisar");
        buttonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPesquisarActionPerformed(evt);
            }
        });

        buttonAlterar.setText("Alterar");
        buttonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAlterarActionPerformed(evt);
            }
        });

        buttonExcluir.setText("Excluir");
        buttonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabelaResultadosScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAlterar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPesquisa)
                    .addComponent(fieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaResultadosScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelar)
                    .addComponent(buttonAlterar)
                    .addComponent(buttonExcluir))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Listener do botão de pesquisa
    private void buttonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPesquisarActionPerformed
        //Inicializa o sucesso da pesquisa com valor negativo, indicando que
        //a pesquisa de clientes não obteve resultados (situação padrão)
        boolean resultSearch = false;
        
        //Grava o campo de pesquisa como a última pesquisa válida. O valor
        //de última pesquisa válida é utilizado na atualização da lista
        ultimaPesquisa = fieldPesquisa.getText();

        try {
            //Solicita a atualização da lista com o novo critério
            //de pesquisa (ultimaPesquisa)
            resultSearch = refreshList();
        } catch (Exception e) {
            //Exibe mensagens de erro na fonte de dados e para o listener
            JOptionPane.showMessageDialog(rootPane, e.getMessage(),
                    "Falha ao obter lista", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Exibe mensagem de erro caso a pesquisa não tenha resultados
        if (!resultSearch) {
            JOptionPane.showMessageDialog(rootPane, "A pesquisa não retornou resultados ",
                    "Sem resultados", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonPesquisarActionPerformed

    //Atualiza a lista de clientes. Pode ser chamado por outras telas
    public boolean refreshList() throws ClienteException, Exception {
        //Realiza a pesquisa de clientes com o último valor de pesquisa
        //para atualizar a lista
        List<Cliente> resultado = ServicoCliente.getInstance().
                procurarCliente(ultimaPesquisa);

        //Obtém o elemento representante do conteúdo da tabela na tela
        DefaultTableModel model = (DefaultTableModel) tabelaResultados.getModel();
        //Indica que a tabela deve excluir todos seus elementos
        //Isto limpará a lista, mesmo que a pesquisa não tenha sucesso
        model.setRowCount(0);

        //Verifica se não existiram resultados. Caso afirmativo, encerra a
        //atualização e indica ao elemento acionador o não sucesso da pesquisa
        if (resultado == null || resultado.size() <= 0) {
            return false;
        }

        //Percorre a lista de resultados e os adiciona na tabela
        for (int i = 0; i < resultado.size(); i++) {
            Cliente cli = resultado.get(i);
            if (cli != null) {
                Object[] row = new Object[5];
                row[0] = cli.getId();
                row[1] = cli.getNome();
                row[2] = cli.getSobrenome();
                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");          
                row[3] = formatador.format(cli.getDataNascimento());
                row[4] = cli.getGenero();
                model.addRow(row);
            }
        }

        //Se chegamos até aqui, a pesquisa teve sucesso, então
        //retornamos "true" para o elemento acionante, indicando
        //que não devem ser exibidas mensagens de erro
        return true;
    }

    //Trata os cliques na tabela de resultados de pesquisa de clientes
    private void tabelaResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaResultadosMouseClicked
        //Verifica se o clique é um clique duplo       
        if (evt.getClickCount() == 2) {
            try {                
                //Obtém a linha selecionada da tabela de resultados
                final int row = tabelaResultados.getSelectedRow();
                //Obtém o valor do ID da coluna "ID" da tabela de resultados
                Integer id = (Integer) tabelaResultados.getValueAt(row, 0);
                
                //Com o ID da coluna, chama o serviço de cliente para
                //obter o cliente com dados atualizados do DAO
                Cliente cliente = ServicoCliente.getInstance().obterCliente(id);

                //Cria uma nova instância da tela de edição,
                //configura o cliente selecionado como elemento a
                //ser editado e mostra a tela de edição.
                //Para exibir a tela, é necessário adicioná-la ao
                //componente de desktop, o "pai" da janela corrente
                formEditarCliente.dispose();
                formEditarCliente = new TelaEditarCliente();
                formEditarCliente.setCliente(cliente);
                formEditarCliente.setTitle(cliente.getNome() + " " + cliente.getSobrenome());
                this.getParent().add(formEditarCliente);
                this.openFrameInCenter(formEditarCliente);                
                formEditarCliente.toFront();
            } catch (Exception e) {
                //Se ocorrer algum erro técnico, mostra-o no console,
                //mas esconde-o do usuário
                e.printStackTrace();
                //Exibe uma mensagem de erro genérica ao usuário
                JOptionPane.showMessageDialog(rootPane, "Não é possível "
                    + "exibir os detalhes deste cliente.",
                    "Erro ao abrir detalhe", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_tabelaResultadosMouseClicked

    //Trata o comportamento de exclusão do botão respectivo
    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        //Verifica se há itens selecionados para exclusão.
        //Caso negativo, ignora o comando
        if (tabelaResultados.getSelectedRow() >= 0) {
            
            //Obtém a linha do item selecionado
            final int row = tabelaResultados.getSelectedRow();
            //Obtém o nome do cliente da linha indicada para exibição
            //de mensagem de confirmação de exclusão utilizando seu nome
            String nome = (String) tabelaResultados.getValueAt(row, 1);
            //Mostra o diálogo de confirmação de exclusão
            int resposta = JOptionPane.showConfirmDialog(rootPane,
                "Excluir o cliente \"" + nome + "\"?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
            //Se o valor de resposta for "Sim" para a exclusão
            if (resposta == JOptionPane.YES_OPTION) {
                try {
                    //Obtém o ID do cliente
                    Integer id = (Integer) tabelaResultados.getValueAt(row, 0);
                    //Solicita ao serviço a inativação do cliente com o ID
                    ServicoCliente.getInstance().excluirCliente(id);
                    //Atualiza a lista após a "exclusão"
                    this.refreshList();
                } catch (Exception e) {
                    //Se ocorrer algum erro técnico, mostra-o no console,
                    //mas esconde-o do usuário
                    e.printStackTrace();
                    //Exibe uma mensagem de erro genérica ao usuário
                    JOptionPane.showMessageDialog(rootPane, e.getMessage(),
                            "Falha na Exclusão", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_buttonExcluirActionPerformed

    //Listener do botão cancelar
    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    //Trata o clique no botão de alteração
    private void buttonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlterarActionPerformed
        try {
            //Obtém a linha selecionada na tabela de resultados
            final int row = tabelaResultados.getSelectedRow();
            //Verifica se há linha selecionada na tabela
            if (row >= 0) {
                //Obtém a linha selecionada na tabela
                Integer id = (Integer) tabelaResultados.getValueAt(row, 0);
                
                //Solicita ao serviço a obtenção do cliente a partir do
                //ID selecionado na tabela
                Cliente cliente = ServicoCliente.getInstance().obterCliente(id);

                //Cria uma nova instância da tela de edição,
                //configura o cliente selecionado como elemento a
                //ser editado e mostra a tela de edição.
                //Para exibir a tela, é necessário adicioná-la ao
                //componente de desktop, o "pai" da janela corrente
                formEditarCliente.dispose();
                formEditarCliente = new TelaEditarCliente();
                formEditarCliente.setCliente(cliente);
                formEditarCliente.setTitle(cliente.getNome() + " " + cliente.getSobrenome());
                this.getParent().add(formEditarCliente);
                this.openFrameInCenter(formEditarCliente);                
                formEditarCliente.toFront();
            }
        } catch (Exception e) {
            //Se ocorrer algum erro técnico, mostra-o no console,
            //mas esconde-o do usuário
            e.printStackTrace();
            //Exibe uma mensagem de erro genérica ao usuário
            JOptionPane.showMessageDialog(rootPane, "Não é possível "
                + "exibir os detalhes deste cliente.",
                "Erro ao abrir detalhe", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonAlterarActionPerformed

    //Abre um internal frame centralizado na tela
    public void openFrameInCenter(JInternalFrame jif) {
        Dimension desktopSize = this.getParent().getSize();
        Dimension jInternalFrameSize = jif.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAlterar;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonPesquisar;
    private javax.swing.JTextField fieldPesquisa;
    private javax.swing.JLabel labelPesquisa;
    private javax.swing.JTable tabelaResultados;
    private javax.swing.JScrollPane tabelaResultadosScroll;
    // End of variables declaration//GEN-END:variables
}
