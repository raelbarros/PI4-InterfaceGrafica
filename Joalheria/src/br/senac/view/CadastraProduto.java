package br.senac.view;

import br.senac.controller.ServicoDaoCategoria;
import br.senac.controller.ServicoDaoProduto;
import br.senac.db.utils.ManipularImagem;
import br.senac.model.CategoriaProduto;
import br.senac.model.Produto;
import br.senac.model.TipoSegmento;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CadastraProduto extends javax.swing.JFrame {

    private TelaAdmin telaAdmin = null;

    public TelaAdmin getTelaAdmin() {
        return telaAdmin;
    }

    public void setTelaAdmin(TelaAdmin telaAdmin) {
        this.telaAdmin = telaAdmin;
    }

    public CadastraProduto() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Cadastro de Produto");

        getCategoria();

        txtCodigo.setValue(numRandom());

        File imgPadrao = new File("imgPadrao.png");
        imagem = ManipularImagem.setImagemDimensao(imgPadrao.getAbsolutePath(), 146, 170);
        byte[] imgAux = ManipularImagem.getImgBytes(imagem);
        ManipularImagem.exibiImagemLabel(imgAux, lblImg);
    }

    public void getCategoria() {
        List<CategoriaProduto> listaCategoria = null;
        try {
            listaCategoria = ServicoDaoCategoria.getIstance().listarCategoria();

            for (CategoriaProduto c : listaCategoria) {
                jcCategoria.addItem(c.getNome());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Nenhuma Categoria Cadastrada");
            btnSalvar.setEnabled(false);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();
        jpNovoProduto = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblQuantidade = new javax.swing.JLabel();
        jsQuantidade = new javax.swing.JSpinner();
        lblPreco = new javax.swing.JLabel();
        txtPreco = new javax.swing.JFormattedTextField();
        lblCifrao = new javax.swing.JLabel();
        lblEx = new javax.swing.JLabel();
        btnImg = new javax.swing.JButton();
        jpImagem = new javax.swing.JPanel();
        lblImg = new javax.swing.JLabel();
        infoProduto = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JFormattedTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblMarca = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        lblMaterial = new javax.swing.JLabel();
        txtMaterial = new javax.swing.JTextField();
        lblTamanho = new javax.swing.JLabel();
        txtTamanho = new javax.swing.JTextField();
        lblSegmento = new javax.swing.JLabel();
        jcSegmento = new javax.swing.JComboBox<>();
        lblCategoria = new javax.swing.JLabel();
        jcCategoria = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jpNovoProduto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Novo Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 11))); // NOI18N

        btnSalvar.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblQuantidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuantidade.setText("Quantidade Disponivel*");

        jsQuantidade.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jsQuantidade.setModel(new javax.swing.SpinnerNumberModel(0, 0, 500, 1));

        lblPreco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPreco.setText("Preço Unitario*");

        txtPreco.setColumns(10);
        txtPreco.setForeground(new java.awt.Color(255, 0, 0));
        txtPreco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtPreco.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPreco.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblCifrao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCifrao.setText("R$");

        lblEx.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblEx.setText("(Ex: 2.000,00)");

        btnImg.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnImg.setText("Selecionar Imagem");
        btnImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImgActionPerformed(evt);
            }
        });

        jpImagem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imagem Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 11))); // NOI18N

        lblImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jpImagemLayout = new javax.swing.GroupLayout(jpImagem);
        jpImagem.setLayout(jpImagemLayout);
        jpImagemLayout.setHorizontalGroup(
            jpImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
        );
        jpImagemLayout.setVerticalGroup(
            jpImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImg, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
        );

        infoProduto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações do Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 11))); // NOI18N

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblCodigo.setText("SKU");

        txtCodigo.setEditable(false);
        txtCodigo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        lblNome.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblNome.setText("Nome*");

        lblMarca.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblMarca.setText("Marca*");

        lblMaterial.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblMaterial.setText("Material*");

        lblTamanho.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblTamanho.setText("Tamanho*");

        lblSegmento.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSegmento.setText("Segmento*");

        jcSegmento.setModel(new DefaultComboBoxModel(TipoSegmento.values()));

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblCategoria.setText("Categoria*");

        javax.swing.GroupLayout infoProdutoLayout = new javax.swing.GroupLayout(infoProduto);
        infoProduto.setLayout(infoProdutoLayout);
        infoProdutoLayout.setHorizontalGroup(
            infoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoProdutoLayout.createSequentialGroup()
                .addGroup(infoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(infoProdutoLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(infoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMarca)
                            .addComponent(lblNome)
                            .addComponent(lblMaterial)
                            .addComponent(lblTamanho))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, infoProdutoLayout.createSequentialGroup()
                                .addComponent(txtTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcCategoria, 0, 103, Short.MAX_VALUE))
                            .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMarca, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaterial, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(infoProdutoLayout.createSequentialGroup()
                        .addComponent(lblSegmento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcSegmento, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(lblCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo)))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        infoProdutoLayout.setVerticalGroup(
            infoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoProdutoLayout.createSequentialGroup()
                .addGroup(infoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaterial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTamanho)
                    .addComponent(txtTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoria)
                    .addComponent(jcCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSegmento)
                    .addComponent(jcSegmento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodigo))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpNovoProdutoLayout = new javax.swing.GroupLayout(jpNovoProduto);
        jpNovoProduto.setLayout(jpNovoProdutoLayout);
        jpNovoProdutoLayout.setHorizontalGroup(
            jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNovoProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNovoProdutoLayout.createSequentialGroup()
                        .addGroup(jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpNovoProdutoLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(btnImg)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpNovoProdutoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpNovoProdutoLayout.createSequentialGroup()
                                .addGroup(jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jsQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblQuantidade))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpNovoProdutoLayout.createSequentialGroup()
                                        .addComponent(lblPreco)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblEx))
                                    .addGroup(jpNovoProdutoLayout.createSequentialGroup()
                                        .addComponent(lblCifrao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)))
                                .addGap(57, 57, 57))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpNovoProdutoLayout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(152, 152, 152))))))
        );
        jpNovoProdutoLayout.setVerticalGroup(
            jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNovoProdutoLayout.createSequentialGroup()
                .addGroup(jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(infoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuantidade)
                    .addGroup(jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPreco)
                        .addComponent(lblEx)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCifrao)
                        .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jsQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpNovoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpNovoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Produto newProduto = new Produto();

        newProduto.setNome(txtNome.getText());
        newProduto.setMarca(txtMarca.getText());
        newProduto.setQuant((int) jsQuantidade.getValue());
        newProduto.setMaterial(txtMaterial.getText());
        newProduto.setTamanho(txtTamanho.getText());
        newProduto.setCodigo((int) txtCodigo.getValue());

        newProduto.setImg(ManipularImagem.getImgBytes(imagem));

        TipoSegmento tipo = (TipoSegmento) jcSegmento.getSelectedItem();
        newProduto.setSegmento(tipo.getTipoSegmento());

        float preco = convertSringToFloat(txtPreco.getValue().toString());

        if (preco <= 0) {
            JOptionPane.showMessageDialog(this, "Preço Invalido");
            return;
        }

        newProduto.setPrecoUni(preco);

        try {
            CategoriaProduto categoria = ServicoDaoCategoria.getIstance().pegaCategoriaByName(jcCategoria.getSelectedItem().toString());
            int id = categoria.getIdCategoria();
            newProduto.setIdCategoria(id);
            ServicoDaoProduto.getIstance().cadastraProduto(newProduto);

            JOptionPane.showMessageDialog(this, "Cadastro efetuado com sucesso!");

            txtNome.setText("");
            txtMarca.setText("");
            txtMaterial.setText("");
            txtTamanho.setText("");
            jcSegmento.setSelectedIndex(0);//Corrigir
            jcCategoria.setSelectedIndex(0);
            jsQuantidade.setValue(0);
            txtPreco.setValue(0);
            telaAdmin.listaroduto();

            byte[] imgAux = ManipularImagem.getImgBytes(imagem);
            ManipularImagem.exibiImagemLabel(imgAux, lblImg);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    BufferedImage imagem = null;
    private void btnImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImgActionPerformed
        JFileChooser fc = new JFileChooser();
        int resp = fc.showOpenDialog(null);

        if (resp == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();

            try {
                imagem = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 146, 170);
                byte[] imgAux = ManipularImagem.getImgBytes(imagem);
                ManipularImagem.exibiImagemLabel(imgAux, lblImg);
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum arquivo selecionado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnImgActionPerformed

    private float convertSringToFloat(String x) {
        try {
            return Float.parseFloat(x);
        } catch (Exception e) {
            return -1;
        }
    }

    private int numRandom() {
        Random rdn = new Random();
        return rdn.nextInt(19700621);
    }

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
            java.util.logging.Logger.getLogger(CadastraProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastraProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastraProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastraProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastraProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImg;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JPanel infoProduto;
    private javax.swing.JComboBox<String> jcCategoria;
    private javax.swing.JComboBox<String> jcSegmento;
    private javax.swing.JPanel jpImagem;
    private javax.swing.JPanel jpNovoProduto;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JSpinner jsQuantidade;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCifrao;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblEx;
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMaterial;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblSegmento;
    private javax.swing.JLabel lblTamanho;
    private javax.swing.JFormattedTextField txtCodigo;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtMaterial;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtPreco;
    private javax.swing.JTextField txtTamanho;
    // End of variables declaration//GEN-END:variables
}
