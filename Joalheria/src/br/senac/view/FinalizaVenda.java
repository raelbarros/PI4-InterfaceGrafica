package br.senac.view;

import br.senac.controller.ServicoAutenticacao;
import br.senac.controller.ServicoDaoCliente;
import br.senac.controller.ServicoDaoVenda;
import br.senac.exception.DaoException;
import br.senac.model.Cliente;
import br.senac.model.ItemVenda;
import br.senac.model.Venda;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

public class FinalizaVenda extends javax.swing.JFrame {

    private Venda venda;
    private TelaFuncionario telaUser;
    
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public TelaFuncionario getTelaUser() {
        return telaUser;
    }

    public void setTelaUser(TelaFuncionario telaUser) {
        this.telaUser = telaUser;
    }

    
    
    public FinalizaVenda() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Finalizar Venda");

        txtDesc.setValue(0.00);

        txtCliente.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                String val = txtCliente.getEditor().getItem().toString();

                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    try {
                        DefaultComboBoxModel autoComplete = ServicoDaoCliente.getIstance().autoComplete(val);

                        if (autoComplete == null) {
                            return;
                        }

                        txtCliente.setModel(autoComplete);

                        if (txtCliente.getItemCount() > 0) {
                            txtCliente.showPopup();
                            if (evt.getKeyCode() != 8) {
                                ((JTextComponent) txtCliente.getEditor().getEditorComponent()).select(val.length(), txtCliente.getEditor().getItem().toString().length());
                            } else {
                                txtCliente.getEditor().setItem(val);
                            }
                        } else {
                            txtCliente.addItem(val);
                        }
                    } catch (DaoException ex) {
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pagamento = new javax.swing.ButtonGroup();
        jpPrincipal = new javax.swing.JPanel();
        jpFormaPag = new javax.swing.JPanel();
        jcDinheiro = new javax.swing.JCheckBox();
        jcCredito = new javax.swing.JCheckBox();
        jcDebito = new javax.swing.JCheckBox();
        txtValParc = new javax.swing.JTextField();
        lblRSP = new javax.swing.JLabel();
        lblDesconto = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblParcelas = new javax.swing.JLabel();
        jsParcelas = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JSeparator();
        txtDesc = new javax.swing.JFormattedTextField();
        txtValDesc = new javax.swing.JTextField();
        txtRSD = new javax.swing.JLabel();
        btnFimPedido = new javax.swing.JButton();
        jpTotal = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lblDesc = new javax.swing.JLabel();
        txtValTotalDesc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTotalDesc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtValTotalParc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTotalParc = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtValTotal = new javax.swing.JTextField();
        btnCompra = new javax.swing.JButton();
        jpCliente = new javax.swing.JPanel();
        txtCliente = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jpFormaPag.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forma de Pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 11))); // NOI18N

        Pagamento.add(jcDinheiro);
        jcDinheiro.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jcDinheiro.setText("Dinheiro");
        jcDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcDinheiroActionPerformed(evt);
            }
        });

        Pagamento.add(jcCredito);
        jcCredito.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jcCredito.setText("Credito");
        jcCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcCreditoActionPerformed(evt);
            }
        });

        Pagamento.add(jcDebito);
        jcDebito.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jcDebito.setText("Debito");
        jcDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcDebitoActionPerformed(evt);
            }
        });

        txtValParc.setEditable(false);

        lblRSP.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRSP.setText("R$");

        lblDesconto.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblDesconto.setText("Desconto (%)");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        lblParcelas.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblParcelas.setText("Parcelas:");

        jsParcelas.setModel(new javax.swing.SpinnerNumberModel(0, 0, 12, 1));
        jsParcelas.setEnabled(false);
        jsParcelas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsParcelasStateChanged(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        txtDesc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0%"))));

        txtValDesc.setEditable(false);

        txtRSD.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        txtRSD.setText("R$");

        btnFimPedido.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnFimPedido.setText("Finalizar Pedido");
        btnFimPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFimPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpFormaPagLayout = new javax.swing.GroupLayout(jpFormaPag);
        jpFormaPag.setLayout(jpFormaPagLayout);
        jpFormaPagLayout.setHorizontalGroup(
            jpFormaPagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFormaPagLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFormaPagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFormaPagLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jcDinheiro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcCredito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcDebito)
                        .addGap(30, 30, 30))
                    .addGroup(jpFormaPagLayout.createSequentialGroup()
                        .addGroup(jpFormaPagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpFormaPagLayout.createSequentialGroup()
                                .addComponent(lblDesconto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRSD, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpFormaPagLayout.createSequentialGroup()
                                .addComponent(lblParcelas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblRSP, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValParc, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jpFormaPagLayout.createSequentialGroup()
                .addGroup(jpFormaPagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jpFormaPagLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btnFimPedido)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpFormaPagLayout.setVerticalGroup(
            jpFormaPagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFormaPagLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFormaPagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcDinheiro)
                    .addComponent(jcCredito)
                    .addComponent(jcDebito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFormaPagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblParcelas)
                    .addComponent(jsParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValParc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFormaPagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFormaPagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtValDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRSD))
                    .addComponent(lblDesconto))
                .addGap(18, 18, 18)
                .addComponent(btnFimPedido)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpTotal.setBorder(javax.swing.BorderFactory.createTitledBorder("Total"));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTotal.setText("Total R$");

        txtSubTotal.setEditable(false);

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));

        lblDesc.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDesc.setText("Desc.");

        txtValTotalDesc.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel3.setText("R$");

        txtTotalDesc.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel4.setText("Parcelas");

        txtValTotalParc.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel6.setText("R$");

        txtTotalParc.setEditable(false);

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("SubTotal:");

        txtValTotal.setBackground(new java.awt.Color(0, 0, 0));
        txtValTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtValTotal.setForeground(new java.awt.Color(255, 0, 0));
        txtValTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnCompra.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/money.png"))); // NOI18N
        btnCompra.setText("   Finalizar Compra");
        btnCompra.setEnabled(false);
        btnCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpTotalLayout = new javax.swing.GroupLayout(jpTotal);
        jpTotal.setLayout(jpTotalLayout);
        jpTotalLayout.setHorizontalGroup(
            jpTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtValTotal)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpTotalLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubTotal))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpTotalLayout.createSequentialGroup()
                        .addGroup(jpTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpTotalLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValTotalParc))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpTotalLayout.createSequentialGroup()
                                .addComponent(lblDesc)
                                .addGap(12, 12, 12)
                                .addComponent(txtValTotalDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpTotalLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalDesc))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTotalLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalParc, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpTotalLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(lblTotal)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTotalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompra)
                .addGap(34, 34, 34))
        );
        jpTotalLayout.setVerticalGroup(
            jpTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSubTotal)
                    .addGroup(jpTotalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDesc)
                    .addComponent(txtValTotalDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTotalDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtValTotalParc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotalParc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCompra)
                .addGap(31, 31, 31))
        );

        jpCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 11))); // NOI18N

        txtCliente.setEditable(true);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel5.setText("Cliente:");

        javax.swing.GroupLayout jpClienteLayout = new javax.swing.GroupLayout(jpCliente);
        jpCliente.setLayout(jpClienteLayout);
        jpClienteLayout.setHorizontalGroup(
            jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpClienteLayout.setVerticalGroup(
            jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpClienteLayout.createSequentialGroup()
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpPrincipalLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jpFormaPag, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpPrincipalLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jpFormaPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcCreditoActionPerformed
        enableParcelas();
    }//GEN-LAST:event_jcCreditoActionPerformed

    private void jcDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcDinheiroActionPerformed
        disableParcelas();
    }//GEN-LAST:event_jcDinheiroActionPerformed

    private void jcDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcDebitoActionPerformed
        disableParcelas();
    }//GEN-LAST:event_jcDebitoActionPerformed

    private void btnFimPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFimPedidoActionPerformed
        try {
            double desc = 0;
            float d = 0;
            if (txtDesc.getValue() != null) {
                desc = (double) txtDesc.getValue();
                d = (float) desc;
            }
            if (jcDinheiro.isSelected()) {
                setInfoVendas(txtCliente.getSelectedItem().toString(), "Dinheiro", 0, d);
                setInfoInputs();
                btnCompra.setEnabled(true);
            } else if (jcDebito.isSelected()) {
                setInfoVendas(txtCliente.getSelectedItem().toString(), "Debito", 0, d);
                setInfoInputs();
                btnCompra.setEnabled(true);
            } else if (jcCredito.isSelected()) {
                int p = (int) jsParcelas.getValue();
                setInfoVendas(txtCliente.getSelectedItem().toString(), "Credito", p, d);
                setInfoInputs();
                btnCompra.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Informe o Cliente e o tipo de pagamento", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Informe o Cliente e o tipo de pagamento", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFimPedidoActionPerformed

    private void jsParcelasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsParcelasStateChanged
        int p = (int) jsParcelas.getValue();

        double totP = valorTotal() / p;
        txtValParc.setText(formatVal(totP));

    }//GEN-LAST:event_jsParcelasStateChanged

    private void btnCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompraActionPerformed
        try {

            int resp = JOptionPane.showConfirmDialog(this, "Finalizar Venda", "Venda", JOptionPane.YES_NO_OPTION);

            if (resp == JOptionPane.YES_OPTION) {
                ServicoDaoVenda.getIstance().efetuarVenda(venda);
                JOptionPane.showMessageDialog(this, "Venda Efetuada com Sucesso!", "Venda", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                
                telaUser.clear();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCompraActionPerformed

    private void setInfoVendas(String cliente, String tipo, int parcelas, float desconto) {
        String auxData;

        Date systemData = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        auxData = format.format(systemData);

        Date data = null;
        try {
            data = (Date) format.parse(auxData);

            String[] aux = cliente.split(":");
            String[] temp = aux[1].split(" ");

            Cliente c = ServicoDaoCliente.getIstance().pegaClienteByRg(temp[0]);

            float totalVenda = valorTotal();

            float val = totalVenda * desconto;
            totalVenda -= val;

            venda.setDtVenda(data);
            venda.setTipoPagamento(tipo);
            venda.setParcelas(parcelas);
            venda.setTotalVenda(totalVenda);
            venda.setDesconto(desconto);
            venda.setCliente(c);

        } catch (Exception e) {
        }
    }

    private void setInfoInputs() {

        double totalVenda = valorTotal();
        double desc = (double) txtDesc.getValue();

        double valDesc = totalVenda * desc;

        txtValDesc.setText(formatVal(valDesc));

        txtSubTotal.setText(formatVal(totalVenda));
        txtValTotalDesc.setText(txtDesc.getText());
        txtTotalDesc.setText(formatVal(valDesc));

        txtValTotalParc.setText(venda.getParcelas() + "");
        txtTotalParc.setText(txtValParc.getText());

        txtValTotal.setText(formatVal(venda.getTotalVenda()));
    }

    private void enableParcelas() {
        jsParcelas.setEnabled(true);
    }

    private void disableParcelas() {
        jsParcelas.setEnabled(false);
        jsParcelas.setValue(0);
        txtValParc.setText("");
    }

    private float valorTotal() {
        float totalVenda = 0;

        List<ItemVenda> listaVenda = venda.getListaItensVendidos();
        for (ItemVenda item : listaVenda) {
            totalVenda += item.getPrecoUni() * item.getQuant();
        }
        return totalVenda;
    }

    private String formatVal(double val) {
        DecimalFormat format = new DecimalFormat("#,###,###.00");
        return format.format(val);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FinalizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinalizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinalizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinalizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinalizaVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Pagamento;
    private javax.swing.JButton btnCompra;
    private javax.swing.JButton btnFimPedido;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JCheckBox jcCredito;
    private javax.swing.JCheckBox jcDebito;
    private javax.swing.JCheckBox jcDinheiro;
    private javax.swing.JPanel jpCliente;
    private javax.swing.JPanel jpFormaPag;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JPanel jpTotal;
    private javax.swing.JSpinner jsParcelas;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblDesconto;
    private javax.swing.JLabel lblParcelas;
    private javax.swing.JLabel lblRSP;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JComboBox<String> txtCliente;
    private javax.swing.JFormattedTextField txtDesc;
    private javax.swing.JLabel txtRSD;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotalDesc;
    private javax.swing.JTextField txtTotalParc;
    private javax.swing.JTextField txtValDesc;
    private javax.swing.JTextField txtValParc;
    private javax.swing.JTextField txtValTotal;
    private javax.swing.JTextField txtValTotalDesc;
    private javax.swing.JTextField txtValTotalParc;
    // End of variables declaration//GEN-END:variables
}
