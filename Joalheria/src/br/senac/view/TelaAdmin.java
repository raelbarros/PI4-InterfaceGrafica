package br.senac.view;

import br.senac.controller.ServicoAutenticacao;
import br.senac.controller.ServicoDaoCategoria;
import br.senac.controller.ServicoDaoCliente;
import br.senac.controller.ServicoDaoFuncionario;
import br.senac.controller.ServicoDaoProduto;
import br.senac.controller.ServicoDaoVenda;
import br.senac.exception.DaoException;
import br.senac.model.CategoriaProduto;
import br.senac.model.Cliente;
import br.senac.model.Funcionario;
import br.senac.model.ItemVenda;
import br.senac.model.Produto;
import br.senac.model.TipoSegmento;
import br.senac.model.Venda;
import java.awt.CardLayout;
import java.awt.Color;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaAdmin extends javax.swing.JFrame {

    Funcionario userLogged = null;
    CadastroCliente telaNewCliente = null;
    CadastraProduto telaNewProduto = null;
    CadastraFuncionario telaNewF = null;
    EditarCliente telaEditCliente = null;
    EditarProduto telaEditProduto = null;
    EditarFuncionario telaEditFuncionario = null;
    InfoVenda infoVenda = null;
    Login login = null;

    public TelaAdmin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Administrador");

        userLogged = ServicoAutenticacao.getInstance().getUserLoged();

        if (userLogged == null) {
            this.dispose();
            login.setVisible(true);
            login.toFront();
        }

        lblUsuario.setText(userLogged.getNome());

        chFiltroFuncionario.addItem("Todos");
        chFiltroFuncionario.addItem("admin");
        chFiltroFuncionario.addItem("user");

        getCategoria();

        listarCliente();
        listaroduto();
        listarFuncionario();
        listarVendas();

    }

    public void getCategoria() {
        List<CategoriaProduto> listaCategoria = null;

        chFiltroProduto.removeAll();
        chFiltroProduto.addItem("Todos");

        try {
            listaCategoria = ServicoDaoCategoria.getIstance().listarCategoria();

            for (CategoriaProduto c : listaCategoria) {
                chFiltroProduto.addItem(c.getNome());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nenhuma Categoria Cadastrada");
        }
    }

    public void listarCliente() {
        List<Cliente> listaCliente = null;

        chFiltroVendas.removeAll();
        chFiltroVendas.addItem("Todos");

        try {
            listaCliente = ServicoDaoCliente.getIstance().listarCliente();

            DefaultTableModel modelCliente = (DefaultTableModel) jtCliente.getModel();
            modelCliente.setNumRows(0);
            if (!listaCliente.isEmpty()) {
                for (Cliente c : listaCliente) {
                    modelCliente.addRow(new Object[]{
                        c.getIdCliente(),
                        c.getNome(),
                        c.getSobrenome(),
                        c.getEmail(),
                        c.getRg(),
                        c.getCpf()
                    });

                    chFiltroVendas.addItem(c.getNome());
                }
            }

        } catch (Exception e) {
        }
    }

    public void listaroduto() {
        List<Produto> listaProduto = null;
        try {
            listaProduto = ServicoDaoProduto.getIstance().listarProduto();

            NumberFormat format = new DecimalFormat("#0.00");

            DefaultTableModel modelProduto = (DefaultTableModel) jtProdutos.getModel();
            modelProduto.setNumRows(0);

            if (!listaProduto.isEmpty()) {
                for (Produto p : listaProduto) {
                    String preco = format.format(p.getPrecoUni());
                    TipoSegmento segmento = TipoSegmento.getEnum(p.getSegmento());
                    modelProduto.addRow(new Object[]{
                        p.getIdProduto(),
                        p.getCodigo(),
                        p.getNome(),
                        p.getMarca(),
                        segmento.toString(),
                        p.getQuant(),
                        preco});
                }
            }
        } catch (Exception e) {
        }
    }

    public void listarFuncionario() {
        List<Funcionario> listaFuncionario = null;
        try {
            listaFuncionario = ServicoDaoFuncionario.getIstance().listarFuncionario();

            DefaultTableModel modelFuncionario = (DefaultTableModel) jtFuncionarios.getModel();
            modelFuncionario.setNumRows(0);
            if (!listaFuncionario.isEmpty()) {
                for (Funcionario f : listaFuncionario) {
                    modelFuncionario.addRow(new Object[]{
                        f.getIdFuncionario(),
                        f.getNome(),
                        f.getSobrenome(),
                        f.getTipo()
                    });
                }
            }
        } catch (Exception e) {
        }
    }

    public void listarVendas() {
        List<Venda> listaVendas = null;
        try {
            listaVendas = ServicoDaoVenda.getIstance().listarVenda();

            DefaultTableModel model = (DefaultTableModel) jtVendas.getModel();
            model.setRowCount(0);

            NumberFormat format = new DecimalFormat("#0.00");

            if (!listaVendas.isEmpty()) {
                for (Venda v : listaVendas) {
                    String preco = format.format(v.getTotalVenda());
                    model.addRow(new Object[]{
                        v.getIdVenda(),
                        v.getFuncionario().getNome(),
                        v.getCliente().getNome() + " " + v.getCliente().getSobrenome(),
                        v.getTipoPagamento(),
                        preco,});
                }
            }
        } catch (Exception e) {
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpBg = new javax.swing.JPanel();
        jpSideBar = new javax.swing.JPanel();
        lblBemVindo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        btnFuncionarios = new javax.swing.JPanel();
        lblIconFuncionarios = new javax.swing.JLabel();
        lblFuncionarios = new javax.swing.JLabel();
        btnClientes = new javax.swing.JPanel();
        lblIconClientes = new javax.swing.JLabel();
        lblClientes = new javax.swing.JLabel();
        btnVendas = new javax.swing.JPanel();
        lblIconVendas = new javax.swing.JLabel();
        lblVendas = new javax.swing.JLabel();
        btnProdutos = new javax.swing.JPanel();
        lblIconProdutos = new javax.swing.JLabel();
        lblProdutos = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JPanel();
        imgLogout = new javax.swing.JLabel();
        jpPrincipal = new javax.swing.JPanel();
        jpFuncionarios = new javax.swing.JPanel();
        jpTopFuncionario = new javax.swing.JPanel();
        lblTopFuncionario = new javax.swing.JLabel();
        lblSubTitleFuncionario = new javax.swing.JLabel();
        btnAddFuncionario = new javax.swing.JButton();
        btnEditarFuncionario = new javax.swing.JButton();
        btnExcluirFuncionario = new javax.swing.JButton();
        jpBuscaFuncionario = new javax.swing.JPanel();
        txtBuscaFuncionario = new javax.swing.JTextField();
        jsBuscaFuncionario = new javax.swing.JSeparator();
        btnBuscaFuncionario = new javax.swing.JPanel();
        iconBusca = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtFuncionarios = new javax.swing.JTable();
        chFiltroFuncionario = new java.awt.Choice();
        lblFiltroFuncionario = new javax.swing.JLabel();
        jpClientes = new javax.swing.JPanel();
        jpTopCliente = new javax.swing.JPanel();
        lblTitleCliente = new javax.swing.JLabel();
        btnAddCliente = new javax.swing.JButton();
        lblSubTitleCliente = new javax.swing.JLabel();
        btnExcluirCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        jpBuscaCliente = new javax.swing.JPanel();
        txtBuscaCliente = new javax.swing.JTextField();
        jsBuscaCliente = new javax.swing.JSeparator();
        btnBuscaCliente = new javax.swing.JPanel();
        iconBuscaCliente = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtCliente = new javax.swing.JTable();
        jpProdutos = new javax.swing.JPanel();
        jpTopProdutos = new javax.swing.JPanel();
        lblTitleProduto = new javax.swing.JLabel();
        btnAddProdutos = new javax.swing.JButton();
        lblSubTitleProduto = new javax.swing.JLabel();
        btnEditarProduto = new javax.swing.JButton();
        btnExcluirProduto = new javax.swing.JButton();
        jpBuscaProduto = new javax.swing.JPanel();
        txtBuscaProduto = new javax.swing.JTextField();
        jsBuscaProdut = new javax.swing.JSeparator();
        btnBuscaProduto = new javax.swing.JPanel();
        iconBuscaProduto = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtProdutos = new javax.swing.JTable();
        chFiltroProduto = new java.awt.Choice();
        lblFiltroProduto = new javax.swing.JLabel();
        jpVenda = new javax.swing.JPanel();
        jpTopVendas = new javax.swing.JPanel();
        Vendas = new javax.swing.JLabel();
        lblSubTitleVendas = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtVendas = new javax.swing.JTable();
        chFiltroVendas = new java.awt.Choice();
        lblFiltroVendas = new javax.swing.JLabel();
        jdInicio = new com.toedter.calendar.JDateChooser();
        lblInicio = new javax.swing.JLabel();
        lblFim = new javax.swing.JLabel();
        jdFim = new com.toedter.calendar.JDateChooser();
        btnDownload = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jpBg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpSideBar.setBackground(new java.awt.Color(102, 102, 102));
        jpSideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBemVindo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBemVindo.setForeground(new java.awt.Color(204, 204, 204));
        lblBemVindo.setText("Bem Vindo!");
        jpSideBar.add(lblBemVindo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 30));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(204, 204, 204));
        jpSideBar.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        btnFuncionarios.setBackground(new java.awt.Color(204, 204, 204));
        btnFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnFuncionariosMousePressed(evt);
            }
        });

        lblIconFuncionarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/home.png"))); // NOI18N

        lblFuncionarios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFuncionarios.setText("Funcionarios");

        javax.swing.GroupLayout btnFuncionariosLayout = new javax.swing.GroupLayout(btnFuncionarios);
        btnFuncionarios.setLayout(btnFuncionariosLayout);
        btnFuncionariosLayout.setHorizontalGroup(
            btnFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnFuncionariosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblIconFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        btnFuncionariosLayout.setVerticalGroup(
            btnFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnFuncionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblFuncionarios)
                    .addComponent(lblIconFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jpSideBar.add(btnFuncionarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 280, 60));

        btnClientes.setBackground(new java.awt.Color(153, 153, 153));
        btnClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnClientesMousePressed(evt);
            }
        });

        lblIconClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/user.png"))); // NOI18N

        lblClientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblClientes.setText("Clientes");

        javax.swing.GroupLayout btnClientesLayout = new javax.swing.GroupLayout(btnClientes);
        btnClientes.setLayout(btnClientesLayout);
        btnClientesLayout.setHorizontalGroup(
            btnClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnClientesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblIconClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        btnClientesLayout.setVerticalGroup(
            btnClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblClientes)
                    .addComponent(lblIconClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jpSideBar.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, -1, -1));

        btnVendas.setBackground(new java.awt.Color(153, 153, 153));
        btnVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnVendasMousePressed(evt);
            }
        });

        lblIconVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/gift.png"))); // NOI18N

        lblVendas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVendas.setText("Vendas");

        javax.swing.GroupLayout btnVendasLayout = new javax.swing.GroupLayout(btnVendas);
        btnVendas.setLayout(btnVendasLayout);
        btnVendasLayout.setHorizontalGroup(
            btnVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnVendasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblIconVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        btnVendasLayout.setVerticalGroup(
            btnVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnVendasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblVendas)
                    .addComponent(lblIconVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jpSideBar.add(btnVendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, -1, -1));

        btnProdutos.setBackground(new java.awt.Color(153, 153, 153));
        btnProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnProdutosMousePressed(evt);
            }
        });

        lblIconProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/product.png"))); // NOI18N

        lblProdutos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblProdutos.setText("Produtos");

        javax.swing.GroupLayout btnProdutosLayout = new javax.swing.GroupLayout(btnProdutos);
        btnProdutos.setLayout(btnProdutosLayout);
        btnProdutosLayout.setHorizontalGroup(
            btnProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnProdutosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblIconProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        btnProdutosLayout.setVerticalGroup(
            btnProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblProdutos)
                    .addComponent(lblIconProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jpSideBar.add(btnProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 280, -1));
        jpSideBar.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 210, 10));

        btnLogout.setBackground(new java.awt.Color(102, 102, 102));

        imgLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/exit.png"))); // NOI18N
        imgLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imgLogoutMousePressed(evt);
            }
        });

        javax.swing.GroupLayout btnLogoutLayout = new javax.swing.GroupLayout(btnLogout);
        btnLogout.setLayout(btnLogoutLayout);
        btnLogoutLayout.setHorizontalGroup(
            btnLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );
        btnLogoutLayout.setVerticalGroup(
            btnLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        jpSideBar.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 20, 20));

        jpBg.add(jpSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 610));

        jpPrincipal.setLayout(new java.awt.CardLayout());

        jpFuncionarios.setBackground(new java.awt.Color(255, 255, 255));
        jpFuncionarios.setToolTipText("");

        jpTopFuncionario.setBackground(new java.awt.Color(204, 204, 204));
        jpTopFuncionario.setPreferredSize(new java.awt.Dimension(820, 165));

        lblTopFuncionario.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTopFuncionario.setText("Funcionarios");

        lblSubTitleFuncionario.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblSubTitleFuncionario.setText("Gerencie sua equipe...");

        btnAddFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnAddFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/plus.png"))); // NOI18N
        btnAddFuncionario.setText("   Adicionar");
        btnAddFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFuncionarioActionPerformed(evt);
            }
        });

        btnEditarFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/edit.png"))); // NOI18N
        btnEditarFuncionario.setText("   Editar");
        btnEditarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarFuncionarioActionPerformed(evt);
            }
        });

        btnExcluirFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/cancel_16.png"))); // NOI18N
        btnExcluirFuncionario.setText("   Excluir");
        btnExcluirFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirFuncionarioActionPerformed(evt);
            }
        });

        jpBuscaFuncionario.setBackground(new java.awt.Color(204, 204, 204));

        txtBuscaFuncionario.setBackground(new java.awt.Color(204, 204, 204));
        txtBuscaFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscaFuncionario.setBorder(null);
        txtBuscaFuncionario.setCaretColor(new java.awt.Color(255, 255, 255));

        jsBuscaFuncionario.setForeground(new java.awt.Color(255, 255, 255));

        btnBuscaFuncionario.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscaFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBuscaFuncionarioMousePressed(evt);
            }
        });

        iconBusca.setBackground(new java.awt.Color(204, 204, 204));
        iconBusca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        iconBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/search.png"))); // NOI18N

        javax.swing.GroupLayout btnBuscaFuncionarioLayout = new javax.swing.GroupLayout(btnBuscaFuncionario);
        btnBuscaFuncionario.setLayout(btnBuscaFuncionarioLayout);
        btnBuscaFuncionarioLayout.setHorizontalGroup(
            btnBuscaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconBusca, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );
        btnBuscaFuncionarioLayout.setVerticalGroup(
            btnBuscaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconBusca, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpBuscaFuncionarioLayout = new javax.swing.GroupLayout(jpBuscaFuncionario);
        jpBuscaFuncionario.setLayout(jpBuscaFuncionarioLayout);
        jpBuscaFuncionarioLayout.setHorizontalGroup(
            jpBuscaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscaFuncionarioLayout.createSequentialGroup()
                .addGroup(jpBuscaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jsBuscaFuncionario, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBuscaFuncionarioLayout.createSequentialGroup()
                        .addComponent(txtBuscaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpBuscaFuncionarioLayout.setVerticalGroup(
            jpBuscaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscaFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBuscaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpBuscaFuncionarioLayout.createSequentialGroup()
                        .addComponent(txtBuscaFuncionario)
                        .addGap(5, 5, 5)))
                .addComponent(jsBuscaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpTopFuncionarioLayout = new javax.swing.GroupLayout(jpTopFuncionario);
        jpTopFuncionario.setLayout(jpTopFuncionarioLayout);
        jpTopFuncionarioLayout.setHorizontalGroup(
            jpTopFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTopFuncionarioLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jpBuscaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(btnAddFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluirFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
            .addGroup(jpTopFuncionarioLayout.createSequentialGroup()
                .addGroup(jpTopFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTopFuncionarioLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(lblTopFuncionario))
                    .addGroup(jpTopFuncionarioLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(lblSubTitleFuncionario)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTopFuncionarioLayout.setVerticalGroup(
            jpTopFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTopFuncionarioLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTopFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubTitleFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jpTopFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTopFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcluirFuncionario)
                        .addComponent(btnAddFuncionario)
                        .addComponent(btnEditarFuncionario))
                    .addComponent(jpBuscaFuncionario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jtFuncionarios.setAutoCreateRowSorter(true);
        jtFuncionarios.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jtFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "SOBRENOME", "TIPO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtFuncionarios.setGridColor(new java.awt.Color(255, 255, 255));
        jtFuncionarios.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setViewportView(jtFuncionarios);

        chFiltroFuncionario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chFiltroFuncionarioItemStateChanged(evt);
            }
        });

        lblFiltroFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblFiltroFuncionario.setText("Filtar por Tipo de Funcionario");

        javax.swing.GroupLayout jpFuncionariosLayout = new javax.swing.GroupLayout(jpFuncionarios);
        jpFuncionarios.setLayout(jpFuncionariosLayout);
        jpFuncionariosLayout.setHorizontalGroup(
            jpFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTopFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpFuncionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addGroup(jpFuncionariosLayout.createSequentialGroup()
                        .addGroup(jpFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFiltroFuncionario)
                            .addComponent(chFiltroFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpFuncionariosLayout.setVerticalGroup(
            jpFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFuncionariosLayout.createSequentialGroup()
                .addComponent(jpTopFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFiltroFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chFiltroFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpPrincipal.add(jpFuncionarios, "cardFuncionario");
        jpFuncionarios.getAccessibleContext().setAccessibleName("");

        jpClientes.setBackground(new java.awt.Color(255, 255, 255));

        jpTopCliente.setBackground(new java.awt.Color(204, 204, 204));
        jpTopCliente.setPreferredSize(new java.awt.Dimension(820, 165));

        lblTitleCliente.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleCliente.setText("Clientes");

        btnAddCliente.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnAddCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/plus.png"))); // NOI18N
        btnAddCliente.setText("   Adicionar");
        btnAddCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddClienteActionPerformed(evt);
            }
        });

        lblSubTitleCliente.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblSubTitleCliente.setText("Informações de seus Clientes...");

        btnExcluirCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/cancel_16.png"))); // NOI18N
        btnExcluirCliente.setText("   Excluir");
        btnExcluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirClienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/edit.png"))); // NOI18N
        btnEditarCliente.setText("   Editar");
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        jpBuscaCliente.setBackground(new java.awt.Color(204, 204, 204));

        txtBuscaCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtBuscaCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscaCliente.setBorder(null);
        txtBuscaCliente.setCaretColor(new java.awt.Color(255, 255, 255));

        jsBuscaCliente.setForeground(new java.awt.Color(255, 255, 255));

        btnBuscaCliente.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBuscaClienteMousePressed(evt);
            }
        });

        iconBuscaCliente.setBackground(new java.awt.Color(204, 204, 204));
        iconBuscaCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        iconBuscaCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/search.png"))); // NOI18N

        javax.swing.GroupLayout btnBuscaClienteLayout = new javax.swing.GroupLayout(btnBuscaCliente);
        btnBuscaCliente.setLayout(btnBuscaClienteLayout);
        btnBuscaClienteLayout.setHorizontalGroup(
            btnBuscaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBuscaClienteLayout.createSequentialGroup()
                .addComponent(iconBuscaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        btnBuscaClienteLayout.setVerticalGroup(
            btnBuscaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBuscaClienteLayout.createSequentialGroup()
                .addComponent(iconBuscaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jpBuscaClienteLayout = new javax.swing.GroupLayout(jpBuscaCliente);
        jpBuscaCliente.setLayout(jpBuscaClienteLayout);
        jpBuscaClienteLayout.setHorizontalGroup(
            jpBuscaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscaClienteLayout.createSequentialGroup()
                .addGroup(jpBuscaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jsBuscaCliente, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBuscaClienteLayout.createSequentialGroup()
                        .addComponent(txtBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpBuscaClienteLayout.setVerticalGroup(
            jpBuscaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscaClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBuscaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscaCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpTopClienteLayout = new javax.swing.GroupLayout(jpTopCliente);
        jpTopCliente.setLayout(jpTopClienteLayout);
        jpTopClienteLayout.setHorizontalGroup(
            jpTopClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTopClienteLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jpBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jpTopClienteLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jpTopClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleCliente)
                    .addGroup(jpTopClienteLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(lblSubTitleCliente)))
                .addContainerGap(468, Short.MAX_VALUE))
        );
        jpTopClienteLayout.setVerticalGroup(
            jpTopClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTopClienteLayout.createSequentialGroup()
                .addGroup(jpTopClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpTopClienteLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpTopClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExcluirCliente)
                            .addComponent(btnAddCliente)
                            .addComponent(btnEditarCliente)))
                    .addGroup(jpTopClienteLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblTitleCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSubTitleCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jpBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jtCliente.setAutoCreateRowSorter(true);
        jtCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "SOBRENOME", "EMAIL", "RG", "CPF"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCliente.setGridColor(new java.awt.Color(255, 255, 255));
        jtCliente.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane3.setViewportView(jtCliente);

        javax.swing.GroupLayout jpClientesLayout = new javax.swing.GroupLayout(jpClientes);
        jpClientes.setLayout(jpClientesLayout);
        jpClientesLayout.setHorizontalGroup(
            jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTopCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpClientesLayout.setVerticalGroup(
            jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClientesLayout.createSequentialGroup()
                .addComponent(jpTopCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpPrincipal.add(jpClientes, "cardCliente");

        jpProdutos.setBackground(new java.awt.Color(255, 255, 255));

        jpTopProdutos.setBackground(new java.awt.Color(204, 204, 204));

        lblTitleProduto.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleProduto.setText("Produtos");

        btnAddProdutos.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnAddProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/plus.png"))); // NOI18N
        btnAddProdutos.setText("   Adicionar");
        btnAddProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProdutosActionPerformed(evt);
            }
        });

        lblSubTitleProduto.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblSubTitleProduto.setText("Gerencie seu estoque de produtos...");

        btnEditarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/edit.png"))); // NOI18N
        btnEditarProduto.setText("   Editar");
        btnEditarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProdutoActionPerformed(evt);
            }
        });

        btnExcluirProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/cancel_16.png"))); // NOI18N
        btnExcluirProduto.setText("   Excluir");
        btnExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirProdutoActionPerformed(evt);
            }
        });

        jpBuscaProduto.setBackground(new java.awt.Color(204, 204, 204));
        jpBuscaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpBuscaProdutoMousePressed(evt);
            }
        });

        txtBuscaProduto.setBackground(new java.awt.Color(204, 204, 204));
        txtBuscaProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscaProduto.setBorder(null);
        txtBuscaProduto.setCaretColor(new java.awt.Color(255, 255, 255));

        jsBuscaProdut.setForeground(new java.awt.Color(255, 255, 255));

        btnBuscaProduto.setBackground(new java.awt.Color(204, 204, 204));

        iconBuscaProduto.setBackground(new java.awt.Color(204, 204, 204));
        iconBuscaProduto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        iconBuscaProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/search.png"))); // NOI18N

        javax.swing.GroupLayout btnBuscaProdutoLayout = new javax.swing.GroupLayout(btnBuscaProduto);
        btnBuscaProduto.setLayout(btnBuscaProdutoLayout);
        btnBuscaProdutoLayout.setHorizontalGroup(
            btnBuscaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBuscaProdutoLayout.createSequentialGroup()
                .addComponent(iconBuscaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        btnBuscaProdutoLayout.setVerticalGroup(
            btnBuscaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBuscaProdutoLayout.createSequentialGroup()
                .addComponent(iconBuscaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jpBuscaProdutoLayout = new javax.swing.GroupLayout(jpBuscaProduto);
        jpBuscaProduto.setLayout(jpBuscaProdutoLayout);
        jpBuscaProdutoLayout.setHorizontalGroup(
            jpBuscaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscaProdutoLayout.createSequentialGroup()
                .addGroup(jpBuscaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jsBuscaProdut, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBuscaProdutoLayout.createSequentialGroup()
                        .addComponent(txtBuscaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpBuscaProdutoLayout.setVerticalGroup(
            jpBuscaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscaProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBuscaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscaProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsBuscaProdut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpTopProdutosLayout = new javax.swing.GroupLayout(jpTopProdutos);
        jpTopProdutos.setLayout(jpTopProdutosLayout);
        jpTopProdutosLayout.setHorizontalGroup(
            jpTopProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTopProdutosLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jpBuscaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddProdutos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluirProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jpTopProdutosLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jpTopProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleProduto)
                    .addGroup(jpTopProdutosLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblSubTitleProduto)))
                .addContainerGap(452, Short.MAX_VALUE))
        );
        jpTopProdutosLayout.setVerticalGroup(
            jpTopProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTopProdutosLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTitleProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubTitleProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jpTopProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTopProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcluirProduto)
                        .addComponent(btnAddProdutos)
                        .addComponent(btnEditarProduto))
                    .addComponent(jpBuscaProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jtProdutos.setAutoCreateRowSorter(true);
        jtProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "NOME", "MARCA", "SEGMENTO", "QUANTIDADE", "PRECO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtProdutos.setFocusable(false);
        jtProdutos.setGridColor(new java.awt.Color(255, 255, 255));
        jtProdutos.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane4.setViewportView(jtProdutos);

        chFiltroProduto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chFiltroProdutoItemStateChanged(evt);
            }
        });

        lblFiltroProduto.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblFiltroProduto.setText("Filtar por Categoria");

        javax.swing.GroupLayout jpProdutosLayout = new javax.swing.GroupLayout(jpProdutos);
        jpProdutos.setLayout(jpProdutosLayout);
        jpProdutosLayout.setHorizontalGroup(
            jpProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTopProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addGroup(jpProdutosLayout.createSequentialGroup()
                        .addGroup(jpProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFiltroProduto)
                            .addComponent(chFiltroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpProdutosLayout.setVerticalGroup(
            jpProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProdutosLayout.createSequentialGroup()
                .addComponent(jpTopProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFiltroProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chFiltroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpPrincipal.add(jpProdutos, "cardProduto");

        jpVenda.setBackground(new java.awt.Color(255, 255, 255));

        jpTopVendas.setBackground(new java.awt.Color(204, 204, 204));

        Vendas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Vendas.setText("Vendas");

        lblSubTitleVendas.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblSubTitleVendas.setText("Visualize todas as vendas realizadas..");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/info.png"))); // NOI18N
        jButton1.setText("   Detalhes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setText("Informações Detalhadas:");

        javax.swing.GroupLayout jpTopVendasLayout = new javax.swing.GroupLayout(jpTopVendas);
        jpTopVendas.setLayout(jpTopVendasLayout);
        jpTopVendasLayout.setHorizontalGroup(
            jpTopVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTopVendasLayout.createSequentialGroup()
                .addGroup(jpTopVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTopVendasLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(lblSubTitleVendas))
                    .addGroup(jpTopVendasLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(Vendas)))
                .addContainerGap(473, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTopVendasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jpTopVendasLayout.setVerticalGroup(
            jpTopVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTopVendasLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Vendas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubTitleVendas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jpTopVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel2))
                .addGap(25, 25, 25))
        );

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jtVendas.setAutoCreateRowSorter(true);
        jtVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FUNCIONARIO", "CLIENTE", "PAGAMENTO", "PRECO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtVendas.setFocusable(false);
        jtVendas.setGridColor(new java.awt.Color(255, 255, 255));
        jtVendas.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane5.setViewportView(jtVendas);

        chFiltroVendas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chFiltroVendasItemStateChanged(evt);
            }
        });

        lblFiltroVendas.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblFiltroVendas.setText("Filtar por Cliente");

        lblInicio.setText("Inicio");

        lblFim.setText("Termino");

        btnDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/senac/imagens/download.png"))); // NOI18N
        btnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel1.setText("Gerar Relatorio");

        javax.swing.GroupLayout jpVendaLayout = new javax.swing.GroupLayout(jpVenda);
        jpVenda.setLayout(jpVendaLayout);
        jpVendaLayout.setHorizontalGroup(
            jpVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTopVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVendaLayout.createSequentialGroup()
                        .addGroup(jpVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                            .addGroup(jpVendaLayout.createSequentialGroup()
                                .addComponent(chFiltroVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblInicio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdFim, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDownload)))
                        .addContainerGap())
                    .addGroup(jpVendaLayout.createSequentialGroup()
                        .addComponent(lblFiltroVendas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(151, 151, 151))))
        );
        jpVendaLayout.setVerticalGroup(
            jpVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendaLayout.createSequentialGroup()
                .addComponent(jpTopVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVendaLayout.createSequentialGroup()
                        .addGroup(jpVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFiltroVendas)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chFiltroVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpVendaLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jpVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDownload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jdFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblFim)
                                .addComponent(jdInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblInicio)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpPrincipal.add(jpVenda, "cardVenda");

        jpBg.add(jpPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 820, 610));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFuncionariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFuncionariosMousePressed
        setColor(btnFuncionarios);
        resetColor(btnClientes);
        resetColor(btnVendas);
        resetColor(btnProdutos);

        listarFuncionario();
        txtBuscaFuncionario.setText("");

        CardLayout card = (CardLayout) jpPrincipal.getLayout();
        card.show(jpPrincipal, "cardFuncionario");

    }//GEN-LAST:event_btnFuncionariosMousePressed

    private void btnClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientesMousePressed
        setColor(btnClientes);
        resetColor(btnFuncionarios);
        resetColor(btnVendas);
        resetColor(btnProdutos);

        listarCliente();
        txtBuscaCliente.setText("");

        CardLayout card = (CardLayout) jpPrincipal.getLayout();
        card.show(jpPrincipal, "cardCliente");

    }//GEN-LAST:event_btnClientesMousePressed

    private void btnVendasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendasMousePressed
        setColor(btnVendas);
        resetColor(btnFuncionarios);
        resetColor(btnClientes);
        resetColor(btnProdutos);

        CardLayout card = (CardLayout) jpPrincipal.getLayout();
        card.show(jpPrincipal, "cardVenda");

    }//GEN-LAST:event_btnVendasMousePressed

    private void btnProdutosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProdutosMousePressed
        setColor(btnProdutos);
        resetColor(btnFuncionarios);
        resetColor(btnClientes);
        resetColor(btnVendas);

        listaroduto();
        txtBuscaProduto.setText("");

        CardLayout card = (CardLayout) jpPrincipal.getLayout();
        card.show(jpPrincipal, "cardProduto");
    }//GEN-LAST:event_btnProdutosMousePressed

    private void btnAddClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddClienteActionPerformed
        if (telaNewCliente == null || !telaNewCliente.isDisplayable()) {
            telaNewCliente = new CadastroCliente();
            telaNewCliente.setVisible(true);
            telaNewCliente.toFront();
        }

        telaNewCliente.setTelaAdmin(this);
    }//GEN-LAST:event_btnAddClienteActionPerformed

    private void btnAddProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProdutosActionPerformed
        if (telaNewProduto == null || !telaNewProduto.isDisplayable()) {
            telaNewProduto = new CadastraProduto();
            telaNewProduto.setVisible(true);
            telaNewProduto.toFront();
        }
        telaNewProduto.setTelaAdmin(this);
    }//GEN-LAST:event_btnAddProdutosActionPerformed

    private void btnExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProdutoActionPerformed
        try {
            int selectedRowIndex = jtProdutos.getSelectedRow();
            int id = Integer.parseInt(jtProdutos.getValueAt(selectedRowIndex, 0).toString());

            Produto deleteProduto = ServicoDaoProduto.getIstance().pegaProdutoById(id);

            String msg = "Deseja excluir o Produto: \n\nCodigo: " + deleteProduto.getIdProduto() + "\nNome: " + deleteProduto.getNome()
                    + "\nMarca: " + deleteProduto.getMarca() + "\nQuantidade: " + deleteProduto.getQuant() + "\nPreço: " + deleteProduto.getPrecoUni() + "\n\n";

            int resp = JOptionPane.showConfirmDialog(this, msg, "Excluir Produto", JOptionPane.YES_NO_OPTION);

            if (resp == JOptionPane.YES_OPTION) {
                ServicoDaoProduto.getIstance().deletarProduto(id);
                listaroduto();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Selecione um Produto");
        }
    }//GEN-LAST:event_btnExcluirProdutoActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        if (telaEditCliente != null) {
            telaEditCliente.dispose();
        }

        try {
            int selectedRowIndex = jtCliente.getSelectedRow();
            int id = Integer.parseInt(jtCliente.getValueAt(selectedRowIndex, 0).toString());

            telaEditCliente = new EditarCliente();
            telaEditCliente.setVisible(true);
            telaEditCliente.toFront();

            Cliente editCliente = ServicoDaoCliente.getIstance().pegaClienteById(id);

            telaEditCliente.setTelaAdmin(this);
            telaEditCliente.setClienteEdicao(editCliente);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Selecione um Cliente");
        }

    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnExcluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirClienteActionPerformed
        try {
            int selectedRowIndex = jtCliente.getSelectedRow();
            int id = Integer.parseInt(jtCliente.getValueAt(selectedRowIndex, 0).toString());

            Cliente deleteCliente = ServicoDaoCliente.getIstance().pegaClienteById(id);

            String msg = "Deseja excluir o Cliente: \n\nNome: " + deleteCliente.getNome() + "\nSobreome: " + deleteCliente.getSobrenome()
                    + "\nRG: " + deleteCliente.getRg() + "\nCPF: " + deleteCliente.getCpf() + "\n\n";

            int resp = JOptionPane.showConfirmDialog(this, msg, "Excluir Cliente", JOptionPane.YES_NO_OPTION);

            if (resp == JOptionPane.YES_OPTION) {
                ServicoDaoCliente.getIstance().deletarCliente(id);
                listarCliente();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Selecione um Cliente");
        }
    }//GEN-LAST:event_btnExcluirClienteActionPerformed

    private void btnEditarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProdutoActionPerformed
        if (telaEditProduto != null) {
            telaEditProduto.dispose();
        }
        try {
            int selectedRowIndex = jtProdutos.getSelectedRow();
            int id = Integer.parseInt(jtProdutos.getValueAt(selectedRowIndex, 0).toString());

            telaEditProduto = new EditarProduto();
            telaEditProduto.setVisible(true);
            telaEditProduto.toFront();

            Produto editProduto = ServicoDaoProduto.getIstance().pegaProdutoById(id);

            telaEditProduto.setTelaAdmin(this);
            telaEditProduto.setProdutoEdicao(editProduto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Selecione um Produto");
        }
    }//GEN-LAST:event_btnEditarProdutoActionPerformed

    private void imgLogoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgLogoutMousePressed
        setColor(btnLogout);

        int resp = JOptionPane.showConfirmDialog(this, "Deseja sair do sistema ?", "Sair", JOptionPane.YES_NO_OPTION);

        if (resp == JOptionPane.YES_OPTION) {
            ServicoAutenticacao.getInstance().logout(this);
        }
    }//GEN-LAST:event_imgLogoutMousePressed

    private void btnExcluirFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirFuncionarioActionPerformed
        try {
            int selectedRowIndex = jtFuncionarios.getSelectedRow();
            int id = Integer.parseInt(jtFuncionarios.getValueAt(selectedRowIndex, 0).toString());

            Funcionario deleteFuncionario = ServicoDaoFuncionario.getIstance().pegarFuncionarioById(id);

            String msg = "Deseja excluir o Funcionario: \n\nNome: " + deleteFuncionario.getNome() + "\nSobreome: " + deleteFuncionario.getSobrenome()
                    + "\nTipo: " + deleteFuncionario.getTipo() + "\n\n";

            int resp = JOptionPane.showConfirmDialog(this, msg, "Excluir Funcionario", JOptionPane.YES_NO_OPTION);

            if (resp == JOptionPane.YES_OPTION) {
                ServicoDaoFuncionario.getIstance().deletarFuncionario(id);
                listarFuncionario();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Selecione um Funcionario");
        }
    }//GEN-LAST:event_btnExcluirFuncionarioActionPerformed

    private void btnEditarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarFuncionarioActionPerformed
        if (telaEditFuncionario != null) {
            telaEditFuncionario.dispose();
        }

        try {
            int selectedRowIndex = jtFuncionarios.getSelectedRow();
            int id = Integer.parseInt(jtFuncionarios.getValueAt(selectedRowIndex, 0).toString());

            telaEditFuncionario = new EditarFuncionario();
            telaEditFuncionario.setVisible(true);
            telaEditFuncionario.toFront();

            Funcionario editFuncionario = ServicoDaoFuncionario.getIstance().pegarFuncionarioById(id);

            telaEditFuncionario.setTelaAdmin(this);
            telaEditFuncionario.setFuncionarioEdicao(editFuncionario);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Selecione um Funcionario");
        }
    }//GEN-LAST:event_btnEditarFuncionarioActionPerformed

    private void btnAddFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFuncionarioActionPerformed
        if (telaNewF == null || !telaNewF.isDisplayable()) {
            telaNewF = new CadastraFuncionario();
            telaNewF.setVisible(true);
            telaNewF.toFront();
        }

        telaNewF.setTelaAdmin(this);
    }//GEN-LAST:event_btnAddFuncionarioActionPerformed

    String ultimaBuscaF = null;
    private void btnBuscaFuncionarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscaFuncionarioMousePressed
        ultimaBuscaF = txtBuscaFuncionario.getText();

        boolean resultSearch = false;
        try {
            resultSearch = refreshBuscaFuncionario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Falha ao obter lista", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!resultSearch) {
            JOptionPane.showMessageDialog(rootPane, "A pesquisa não retornou resultados ", "Sem resultados", JOptionPane.ERROR_MESSAGE);
            listarFuncionario();
        }
    }//GEN-LAST:event_btnBuscaFuncionarioMousePressed

    private void chFiltroFuncionarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chFiltroFuncionarioItemStateChanged
        try {
            String item = chFiltroFuncionario.getSelectedItem();

            List<Funcionario> resulFiltro = ServicoDaoFuncionario.getIstance().filtroFuncionario(item);

            DefaultTableModel model = (DefaultTableModel) jtFuncionarios.getModel();
            model.setRowCount(0);

            if (!resulFiltro.isEmpty()) {
                for (Funcionario f : resulFiltro) {
                    model.addRow(new Object[]{
                        f.getIdFuncionario(),
                        f.getNome(),
                        f.getSobrenome(),
                        f.getTipo()
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "A pesquisa não retornou resultados ", "Sem resultados", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_chFiltroFuncionarioItemStateChanged

    String ultimaBuscaC = null;
    private void btnBuscaClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscaClienteMousePressed
        ultimaBuscaC = txtBuscaCliente.getText();

        boolean resultSearch = false;
        try {
            resultSearch = refreshBuscaCliente();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Falha ao obter lista", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!resultSearch) {
            JOptionPane.showMessageDialog(rootPane, "A pesquisa não retornou resultados ", "Sem resultados", JOptionPane.ERROR_MESSAGE);
            listarFuncionario();
        }
    }//GEN-LAST:event_btnBuscaClienteMousePressed

    String ultimaBuscaP = null;
    private void jpBuscaProdutoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpBuscaProdutoMousePressed
        ultimaBuscaP = txtBuscaProduto.getText();

        boolean resultSearch = false;
        try {
            resultSearch = refreshBuscaProduto();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Falha ao obter lista", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!resultSearch) {
            JOptionPane.showMessageDialog(rootPane, "A pesquisa não retornou resultados ", "Sem resultados", JOptionPane.ERROR_MESSAGE);
            listarFuncionario();
        }

    }//GEN-LAST:event_jpBuscaProdutoMousePressed

    private void chFiltroProdutoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chFiltroProdutoItemStateChanged
        try {
            int id = chFiltroProduto.getSelectedIndex();

            List<Produto> resulFiltro = ServicoDaoProduto.getIstance().filtroProduto(id);

            DefaultTableModel model = (DefaultTableModel) jtProdutos.getModel();
            model.setRowCount(0);

            NumberFormat format = new DecimalFormat("#0.00");

            if (!resulFiltro.isEmpty()) {
                for (Produto p : resulFiltro) {
                    String preco = format.format(p.getPrecoUni());
                    TipoSegmento segmento = TipoSegmento.getEnum(p.getSegmento());
                    model.addRow(new Object[]{
                        p.getIdProduto(),
                        p.getNome(),
                        p.getMarca(),
                        segmento.toString(),
                        p.getQuant(),
                        preco,});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "A pesquisa não retornou resultados ", "Sem resultados", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_chFiltroProdutoItemStateChanged

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
        try {
            Timestamp inicio = new Timestamp(jdInicio.getDate().getTime());
            Timestamp fim = new Timestamp(jdFim.getDate().getTime());

            boolean isBefore = inicio.before(fim);

            if (isBefore || jdInicio.getDate() == jdFim.getDate()) {
                try {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(FILE));
                    document.open();
                    addContent(document, inicio, fim);
                    document.close();
                    JOptionPane.showMessageDialog(this, "Download Concluido");

                } catch (Exception e) {
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Informe uma Data de inicio e de fim validas", "Data Invalida", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Informe uma Data de inicio e de fim", "Data Invalida", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDownloadActionPerformed

    private void chFiltroVendasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chFiltroVendasItemStateChanged
        try {
            int index = chFiltroVendas.getSelectedIndex();

            List<Venda> resulFiltro = ServicoDaoVenda.getIstance().filtroVenda(index);

            DefaultTableModel model = (DefaultTableModel) jtVendas.getModel();
            model.setRowCount(0);

            NumberFormat format = new DecimalFormat("#0.00");

            if (!resulFiltro.isEmpty()) {
                for (Venda v : resulFiltro) {
                    String preco = format.format(v.getTotalVenda());
                    model.addRow(new Object[]{
                        v.getIdVenda(),
                        v.getFuncionario().getNome(),
                        v.getCliente().getNome() + " " + v.getCliente().getSobrenome(),
                        preco,});
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "A pesquisa não retornou resultados ", "Sem resultados", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_chFiltroVendasItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (infoVenda != null) {
            infoVenda.dispose();
        }

        try {
            int selectedRowIndex = jtVendas.getSelectedRow();
            int id = Integer.parseInt(jtVendas.getValueAt(selectedRowIndex, 0).toString());

            infoVenda = new InfoVenda();
            infoVenda.setVisible(true);
            infoVenda.toFront();

            Venda v = ServicoDaoVenda.getIstance().pegarVendaById(id);
            infoVenda.setVenda(v);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Selecione uma Venda");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    public boolean refreshBuscaFuncionario() throws Exception {
        List<Funcionario> resultBusca = ServicoDaoFuncionario.getIstance().buscarFuncionario(ultimaBuscaF);

        DefaultTableModel model = (DefaultTableModel) jtFuncionarios.getModel();
        model.setRowCount(0);

        if (resultBusca == null || resultBusca.size() <= 0) {
            return false;
        }
        for (Funcionario f : resultBusca) {
            model.addRow(new Object[]{
                f.getIdFuncionario(),
                f.getNome(),
                f.getSobrenome(),
                f.getTipo()
            });
        }
        return true;
    }

    public boolean refreshBuscaProduto() throws Exception {
        List<Produto> resultBusca = ServicoDaoProduto.getIstance().buscarProduto(ultimaBuscaP);

        DefaultTableModel model = (DefaultTableModel) jtProdutos.getModel();
        model.setRowCount(0);

        if (resultBusca == null || resultBusca.size() <= 0) {
            return false;
        }

        NumberFormat format = new DecimalFormat("#0.00");

        for (Produto p : resultBusca) {
            String preco = format.format(p.getPrecoUni());
            TipoSegmento segmento = TipoSegmento.getEnum(p.getSegmento());
            model.addRow(new Object[]{
                p.getIdProduto(),
                p.getNome(),
                p.getMarca(),
                segmento.toString(),
                p.getQuant(),
                preco,});
        }
        return true;
    }

    public boolean refreshBuscaCliente() throws Exception {
        List<Cliente> resultBusca = ServicoDaoCliente.getIstance().buscarCliente(ultimaBuscaC);

        DefaultTableModel model = (DefaultTableModel) jtCliente.getModel();
        model.setRowCount(0);

        if (resultBusca == null || resultBusca.size() <= 0) {
            return false;
        }
        for (Cliente c : resultBusca) {
            model.addRow(new Object[]{
                c.getIdCliente(),
                c.getNome(),
                c.getSobrenome(),
                c.getEmail(),
                c.getRg(),
                c.getCpf()
            });
        }
        return true;
    }

    private static String FILE = "report" + System.currentTimeMillis() + ".pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 8,
            Font.ITALIC);

    private static Font tableItem = new Font(Font.FontFamily.TIMES_ROMAN, 9);

    private static void addContent(Document document, Timestamp inicio, Timestamp fim) throws DocumentException, DaoException {
        try {

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            DateFormat dateFormatEx = new SimpleDateFormat("dd/MM/yyyy");

            String data = dateFormat.format(new Date(System.currentTimeMillis()));

            Paragraph main = new Paragraph();
            Paragraph title = new Paragraph("FLUXO DE VENDAS", catFont);

            title.setAlignment(Element.ALIGN_CENTER);
            main.add(title);

            Paragraph emissao = new Paragraph(data, smallBold);
            emissao.setAlignment(Element.ALIGN_RIGHT);
            main.add(emissao);

            addEmptyLine(main, 2);

            main.add(new Paragraph("Periodo:", subFont));
            main.add(new Paragraph(dateFormatEx.format(inicio) + " A " + dateFormatEx.format(fim), smallBold));

            addEmptyLine(main, 2);

            PdfPTable table = new PdfPTable(5);

            PdfPCell c1 = new PdfPCell(new Phrase("CLIENTE", subFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell c2 = new PdfPCell(new Phrase("PRODUTO", subFont));
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell c3 = new PdfPCell(new Phrase("DATA", subFont));
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell c4 = new PdfPCell(new Phrase("QUANT", subFont));
            c4.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell c5 = new PdfPCell(new Phrase("PRECO UNI - DESC", subFont));
            c5.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(c1);
            table.addCell(c2);
            table.addCell(c3);
            table.addCell(c4);
            table.addCell(c5);
            table.setHeaderRows(1);

            List<Venda> listaVenda = ServicoDaoVenda.getIstance().filtroVendaByData(inicio, fim);
            NumberFormat format = new DecimalFormat("#0.00");
            DecimalFormat formatPorc = new DecimalFormat("#,##0.00%");

            float totalVal = 0;
            float cont = 0;
            int totalItem = 0;

            int auxId = 0;

            for (Venda v : listaVenda) {
                auxId = v.getIdVenda();
                for (ItemVenda i : v.getListaItensVendidos()) {
                    table.addCell(new Paragraph(v.getCliente().getNome() + " " + v.getCliente().getSobrenome(), tableItem));
                    table.addCell(new Paragraph(i.getProduto().getNome(), tableItem));
                    table.addCell(new Paragraph(dateFormatEx.format(v.getDtVenda()), tableItem));
                    table.addCell(new Paragraph(i.getQuant() + "", tableItem));
                    table.addCell(new Paragraph(format.format(i.getPrecoUni()) + "  -  " + formatPorc.format(v.getDesconto()), tableItem));

                    totalItem += i.getQuant();
                }
                if (v.getIdVenda() == auxId) {
                    totalVal += v.getTotalVenda();
                }
            }

            PdfPCell total = new PdfPCell(new Phrase("TOTAL", subFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(total);
            table.addCell(" - ");
            table.addCell(" - ");
            table.addCell(totalItem + "");
            table.addCell(format.format(totalVal));

            document.add(main);
            document.add(table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não ha vendas no periodo informado", "Sem resultados", JOptionPane.ERROR_MESSAGE);
        }

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void setColor(JPanel panel) {
        panel.setBackground(new Color(204, 204, 204));
    }

    private void resetColor(JPanel panel) {
        panel.setBackground(new Color(153, 153, 153));
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
            java.util.logging.Logger.getLogger(TelaAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdmin().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Vendas;
    private javax.swing.JButton btnAddCliente;
    private javax.swing.JButton btnAddFuncionario;
    private javax.swing.JButton btnAddProdutos;
    private javax.swing.JPanel btnBuscaCliente;
    private javax.swing.JPanel btnBuscaFuncionario;
    private javax.swing.JPanel btnBuscaProduto;
    private javax.swing.JPanel btnClientes;
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarFuncionario;
    private javax.swing.JButton btnEditarProduto;
    private javax.swing.JButton btnExcluirCliente;
    private javax.swing.JButton btnExcluirFuncionario;
    private javax.swing.JButton btnExcluirProduto;
    private javax.swing.JPanel btnFuncionarios;
    private javax.swing.JPanel btnLogout;
    private javax.swing.JPanel btnProdutos;
    private javax.swing.JPanel btnVendas;
    private java.awt.Choice chFiltroFuncionario;
    private java.awt.Choice chFiltroProduto;
    private java.awt.Choice chFiltroVendas;
    private javax.swing.JLabel iconBusca;
    private javax.swing.JLabel iconBuscaCliente;
    private javax.swing.JLabel iconBuscaProduto;
    private javax.swing.JLabel imgLogout;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdFim;
    private com.toedter.calendar.JDateChooser jdInicio;
    private javax.swing.JPanel jpBg;
    private javax.swing.JPanel jpBuscaCliente;
    private javax.swing.JPanel jpBuscaFuncionario;
    private javax.swing.JPanel jpBuscaProduto;
    private javax.swing.JPanel jpClientes;
    private javax.swing.JPanel jpFuncionarios;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JPanel jpProdutos;
    private javax.swing.JPanel jpSideBar;
    private javax.swing.JPanel jpTopCliente;
    private javax.swing.JPanel jpTopFuncionario;
    private javax.swing.JPanel jpTopProdutos;
    private javax.swing.JPanel jpTopVendas;
    private javax.swing.JPanel jpVenda;
    private javax.swing.JSeparator jsBuscaCliente;
    private javax.swing.JSeparator jsBuscaFuncionario;
    private javax.swing.JSeparator jsBuscaProdut;
    private javax.swing.JTable jtCliente;
    private javax.swing.JTable jtFuncionarios;
    private javax.swing.JTable jtProdutos;
    private javax.swing.JTable jtVendas;
    private javax.swing.JLabel lblBemVindo;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblFiltroFuncionario;
    private javax.swing.JLabel lblFiltroProduto;
    private javax.swing.JLabel lblFiltroVendas;
    private javax.swing.JLabel lblFim;
    private javax.swing.JLabel lblFuncionarios;
    private javax.swing.JLabel lblIconClientes;
    private javax.swing.JLabel lblIconFuncionarios;
    private javax.swing.JLabel lblIconProdutos;
    private javax.swing.JLabel lblIconVendas;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblProdutos;
    private javax.swing.JLabel lblSubTitleCliente;
    private javax.swing.JLabel lblSubTitleFuncionario;
    private javax.swing.JLabel lblSubTitleProduto;
    private javax.swing.JLabel lblSubTitleVendas;
    private javax.swing.JLabel lblTitleCliente;
    private javax.swing.JLabel lblTitleProduto;
    private javax.swing.JLabel lblTopFuncionario;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVendas;
    private javax.swing.JTextField txtBuscaCliente;
    private javax.swing.JTextField txtBuscaFuncionario;
    private javax.swing.JTextField txtBuscaProduto;
    // End of variables declaration//GEN-END:variables
}
