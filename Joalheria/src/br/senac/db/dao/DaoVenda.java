package br.senac.db.dao;

import br.senac.controller.ServicoDaoProduto;
import br.senac.db.utils.ConnectionUtils;
import br.senac.model.Cliente;
import br.senac.model.Funcionario;
import br.senac.model.ItemVenda;
import br.senac.model.Produto;
import br.senac.model.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaoVenda {

    public static void inserirVenda(Venda v) throws SQLException, Exception {
        String sql = "INSERT INTO venda (dtVenda, tipoPagamento, desconto, parcelas, totalVenda, idCliente, idFuncionario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            Timestamp t = new Timestamp(v.getDtVenda().getTime());
            pst.setTimestamp(1, t);
            pst.setString(2, v.getTipoPagamento());
            pst.setFloat(3, v.getDesconto());
            pst.setInt(4, v.getParcelas());
            pst.setFloat(5, v.getTotalVenda());
            pst.setInt(6, v.getCliente().getIdCliente());
            pst.setInt(7, v.getFuncionario().getIdFuncionario());

            pst.execute();

            Integer ultimaChave = null;
            ResultSet chaveGeradaVenda = pst.getGeneratedKeys();
            if (chaveGeradaVenda.next()) {
                ultimaChave = chaveGeradaVenda.getInt(1);
            }

            for (ItemVenda item : v.getListaItensVendidos()) {
                inserirItemVenda(item, ultimaChave);
                decrementarEstoque(item);
            }

        } finally {
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
            if (result != null && !result.isClosed()) {
                result.isClosed();
            }
        }
    }

    public static void inserirItemVenda(ItemVenda item, int chaveVenda) throws SQLException, Exception {
        String sql = "INSERT INTO item_venda (quant, precoUni, idVenda, idProduto) VALUES (?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setInt(1, item.getQuant());
            pst.setFloat(2, item.getPrecoUni());
            pst.setInt(3, chaveVenda);
            pst.setInt(4, item.getProduto().getIdProduto());

            pst.execute();

        } finally {
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
    }

    //decrementa a quantidade vendida do produto no estoque
    public static void decrementarEstoque(ItemVenda item) throws SQLException, Exception {
        String sql = "UPDATE produto SET quant=? WHERE idProduto=?";

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            Produto p = ServicoDaoProduto.getIstance().pegaProdutoById(item.getProduto().getIdProduto());
            int totalQuant = p.getQuant() - item.getQuant();

            if (totalQuant < 0) {
                totalQuant = 0;
            }

            pst.setInt(1, totalQuant);
            pst.setInt(2, item.getProduto().getIdProduto());
            //Configura os atributos do SQL do item de venda         

            pst.executeUpdate();

        } finally {
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
    }

    public static List<Venda> getAllProduto() throws SQLException {
        String sql = "SELECT V.*, I.*, C.*, P.*, F.* FROM  cliente C, produto P, Funcionario F, venda V INNER JOIN item_venda I ON I.idVenda = V.idVenda WHERE V.idCliente = C.idCliente AND V.idFuncionario = F.idFuncionario AND I.idProduto = P.idProduto";

        List<Venda> listaVenda = null;
        List<ItemVenda> listaItemVenda = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            result = pst.executeQuery();

            while (result.next()) {
                if (listaVenda == null) {
                    listaVenda = new ArrayList<>();
                }
                if (listaItemVenda == null) {
                    listaItemVenda = new ArrayList<>();
                }

                Cliente c = new Cliente();
                c.setIdCliente(result.getInt("C.idCliente"));
                c.setNome(result.getString("C.nome"));
                c.setSobrenome(result.getString("C.sobrenome"));
                c.setEmail(result.getString("C.email"));
                c.setRg(result.getString("C.rg"));
                c.setCpf(result.getString("C.cpf"));
                c.setEndereco(result.getString("C.endereco"));
                c.setTelefone(result.getString("C.telefone"));
                c.setSexo(result.getString("C.sexo"));
                Date d = new Date(result.getDate("C.dtNascimento").getTime());
                c.setDtNascimento(d);

                Produto p = new Produto();
                p.setIdProduto(result.getInt("P.idProduto"));
                p.setNome(result.getString("P.nome"));
                p.setMarca(result.getString("P.marca"));
                p.setQuant(result.getInt("P.quant"));
                p.setMaterial(result.getString("P.material"));
                p.setPrecoUni(result.getFloat("P.precoUni"));
                p.setSegmento(result.getString("P.segmento"));
                p.setCodigo(result.getInt("P.codigo"));
                p.setTamanho(result.getString("P.tamanho"));

                Funcionario f = new Funcionario();
                f.setIdFuncionario(result.getInt("F.idFuncionario"));
                f.setNome(result.getString("F.nome"));
                f.setSobrenome(result.getString("F.sobrenome"));
                f.setTipo(result.getString("F.tipo"));

                ItemVenda i = new ItemVenda();
                i.setIdVenda(result.getInt("I.idVenda"));
                i.setQuant(result.getInt("I.quant"));
                i.setPrecoUni(result.getFloat("I.precoUni"));
                i.setProduto(p);

                listaItemVenda.add(i);

                Venda v = new Venda();
                v.setIdVenda(result.getInt("V.idVenda"));
                v.setDtVenda(result.getDate("V.dtVenda"));
                v.setTipoPagamento(result.getString("V.tipoPagamento"));
                v.setParcelas(result.getInt("V.parcelas"));
                v.setTotalVenda(result.getFloat("V.totalVenda"));
                v.setDesconto(result.getFloat("V.desconto"));
                v.setCliente(c);
                v.setFuncionario(f);
                v.setListaItensVendidos(listaItemVenda);

                listaVenda.add(v);

            }

        } finally {
            if (result != null && !result.isClosed()) {
                result.isClosed();
            }
            if (pst != null && !pst.isClosed()) {
                pst.isClosed();
            }
            if (con != null && !con.isClosed()) {
                con.isClosed();
            }
        }

        return listaVenda;
    }

    public static Venda getVendaById(int val) {
        String sql = "SELECT V.*, I.*, C.*, P.*, F.* FROM  cliente C, produto P, Funcionario F, venda V INNER JOIN item_venda I ON I.idVenda = V.idVenda WHERE V.idVenda=? AND I.idVenda=? AND V.idCliente=C.idCliente AND P.idProduto=I.idProduto AND F.idFuncionario=V.idFuncionario;";

        List<ItemVenda> listaItemVenda = null;
        Venda v = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setInt(1, val);
            pst.setInt(2, val);

            result = pst.executeQuery();

            while (result.next()) {
                if (v == null) {
                    v = new Venda();
                }
                if (listaItemVenda == null) {
                    listaItemVenda = new ArrayList<>();
                }

                Cliente c = new Cliente();
                c.setIdCliente(result.getInt("C.idCliente"));
                c.setNome(result.getString("C.nome"));
                c.setSobrenome(result.getString("C.sobrenome"));
                c.setEmail(result.getString("C.email"));
                c.setRg(result.getString("C.rg"));
                c.setCpf(result.getString("C.cpf"));
                c.setEndereco(result.getString("C.endereco"));
                c.setTelefone(result.getString("C.telefone"));
                c.setSexo(result.getString("C.sexo"));
                Date d = new Date(result.getDate("C.dtNascimento").getTime());
                c.setDtNascimento(d);

                Produto p = new Produto();
                p.setIdProduto(result.getInt("P.idProduto"));
                p.setNome(result.getString("P.nome"));
                p.setMarca(result.getString("P.marca"));
                p.setQuant(result.getInt("P.quant"));
                p.setMaterial(result.getString("P.material"));
                p.setPrecoUni(result.getFloat("P.precoUni"));
                p.setSegmento(result.getString("P.segmento"));
                p.setCodigo(result.getInt("P.codigo"));
                p.setTamanho(result.getString("P.tamanho"));

                Funcionario f = new Funcionario();
                f.setIdFuncionario(result.getInt("F.idFuncionario"));
                f.setNome(result.getString("F.nome"));
                f.setSobrenome(result.getString("F.sobrenome"));
                f.setTipo(result.getString("F.tipo"));

                ItemVenda i = new ItemVenda();
                i.setIdVenda(result.getInt("I.idVenda"));
                i.setQuant(result.getInt("I.quant"));
                i.setPrecoUni(result.getFloat("I.precoUni"));
                i.setProduto(p);

                listaItemVenda.add(i);

                v.setIdVenda(result.getInt("V.idVenda"));
                v.setDtVenda(result.getDate("V.dtVenda"));
                v.setTipoPagamento(result.getString("V.tipoPagamento"));
                v.setParcelas(result.getInt("V.parcelas"));
                v.setTotalVenda(result.getFloat("V.totalVenda"));
                v.setDesconto(result.getFloat("V.desconto"));
                v.setCliente(c);
                v.setFuncionario(f);
                v.setListaItensVendidos(listaItemVenda);

            }

        } catch (Exception e) {
        }

        return v;
    }

    public static List<Venda> filtroVenda(int val) {
        String sql = "SELECT V.*, I.*, C.*, P.*, F.* FROM  cliente C, produto P, Funcionario F, venda V INNER JOIN item_venda I ON I.idVenda = V.idVenda WHERE V.idCliente=? AND C.idCliente=? AND V.idFuncionario = F.idFuncionario AND I.idProduto = P.idProduto";

        List<Venda> listaVenda = null;
        List<ItemVenda> listaItemVenda = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setInt(1, val);
            pst.setInt(2, val);

            result = pst.executeQuery();

            while (result.next()) {
                if (listaVenda == null) {
                    listaVenda = new ArrayList<>();
                }
                if (listaItemVenda == null) {
                    listaItemVenda = new ArrayList<>();
                }

                Cliente c = new Cliente();
                c.setIdCliente(result.getInt("C.idCliente"));
                c.setNome(result.getString("C.nome"));
                c.setSobrenome(result.getString("C.sobrenome"));
                c.setEmail(result.getString("C.email"));
                c.setRg(result.getString("C.rg"));
                c.setCpf(result.getString("C.cpf"));
                c.setEndereco(result.getString("C.endereco"));
                c.setTelefone(result.getString("C.telefone"));
                c.setSexo(result.getString("C.sexo"));
                Date d = new Date(result.getDate("C.dtNascimento").getTime());
                c.setDtNascimento(d);

                Produto p = new Produto();
                p.setIdProduto(result.getInt("P.idProduto"));
                p.setNome(result.getString("P.nome"));
                p.setMarca(result.getString("P.marca"));
                p.setQuant(result.getInt("P.quant"));
                p.setMaterial(result.getString("P.material"));
                p.setPrecoUni(result.getFloat("P.precoUni"));
                p.setSegmento(result.getString("P.segmento"));
                p.setCodigo(result.getInt("P.codigo"));
                p.setTamanho(result.getString("P.tamanho"));

                Funcionario f = new Funcionario();
                f.setIdFuncionario(result.getInt("F.idFuncionario"));
                f.setNome(result.getString("F.nome"));
                f.setSobrenome(result.getString("F.sobrenome"));
                f.setTipo(result.getString("F.tipo"));

                ItemVenda i = new ItemVenda();
                i.setIdVenda(result.getInt("I.idVenda"));
                i.setQuant(result.getInt("I.quant"));
                i.setPrecoUni(result.getFloat("I.precoUni"));
                i.setProduto(p);

                listaItemVenda.add(i);

                Venda v = new Venda();
                v.setIdVenda(result.getInt("V.idVenda"));
                v.setDtVenda(result.getDate("V.dtVenda"));
                v.setTipoPagamento(result.getString("V.tipoPagamento"));
                v.setParcelas(result.getInt("V.parcelas"));
                v.setTotalVenda(result.getFloat("V.totalVenda"));
                v.setDesconto(result.getFloat("V.desconto"));
                v.setCliente(c);
                v.setFuncionario(f);
                v.setListaItensVendidos(listaItemVenda);

                listaVenda.add(v);

            }

        } catch (Exception e) {
        }

        return listaVenda;
    }

    public static List<Venda> filtroVendaByData(Timestamp inicio, Timestamp fim) {
        String sql = "SELECT V.*, I.*, C.*, P.*, F.* FROM  cliente C, produto P, Funcionario F, venda V INNER JOIN item_venda I ON I.idVenda = V.idVenda WHERE V.dtVenda>=? AND V.dtVenda<=? AND V.idCliente=C.idCliente AND P.idProduto=I.idProduto AND F.idFuncionario=V.idFuncionario;";

        List<Venda> listaVenda = null;
        List<ItemVenda> listaItemVenda = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, dateFormat.format(inicio));
            pst.setString(2, dateFormat.format(fim));

            result = pst.executeQuery();

            while (result.next()) {
                if (listaVenda == null) {
                    listaVenda = new ArrayList<>();
                }
                if (listaItemVenda == null) {
                    listaItemVenda = new ArrayList<>();
                }

                Cliente c = new Cliente();
                c.setIdCliente(result.getInt("C.idCliente"));
                c.setNome(result.getString("C.nome"));
                c.setSobrenome(result.getString("C.sobrenome"));
                c.setEmail(result.getString("C.email"));
                c.setRg(result.getString("C.rg"));
                c.setCpf(result.getString("C.cpf"));
                c.setEndereco(result.getString("C.endereco"));
                c.setTelefone(result.getString("C.telefone"));
                c.setSexo(result.getString("C.sexo"));
                Date d = new Date(result.getDate("C.dtNascimento").getTime());
                c.setDtNascimento(d);

                Produto p = new Produto();
                p.setIdProduto(result.getInt("P.idProduto"));
                p.setNome(result.getString("P.nome"));
                p.setMarca(result.getString("P.marca"));
                p.setQuant(result.getInt("P.quant"));
                p.setMaterial(result.getString("P.material"));
                p.setPrecoUni(result.getFloat("P.precoUni"));
                p.setSegmento(result.getString("P.segmento"));
                p.setCodigo(result.getInt("P.codigo"));
                p.setTamanho(result.getString("P.tamanho"));

                Funcionario f = new Funcionario();
                f.setIdFuncionario(result.getInt("F.idFuncionario"));
                f.setNome(result.getString("F.nome"));
                f.setSobrenome(result.getString("F.sobrenome"));
                f.setTipo(result.getString("F.tipo"));

                ItemVenda i = new ItemVenda();
                i.setIdVenda(result.getInt("I.idVenda"));
                i.setQuant(result.getInt("I.quant"));
                i.setPrecoUni(result.getFloat("I.precoUni"));
                i.setProduto(p);

                listaItemVenda.add(i);

                Venda v = new Venda();
                v.setIdVenda(result.getInt("V.idVenda"));
                v.setDtVenda(result.getDate("V.dtVenda"));
                v.setTipoPagamento(result.getString("V.tipoPagamento"));
                v.setParcelas(result.getInt("V.parcelas"));
                v.setTotalVenda(result.getFloat("V.totalVenda"));
                v.setDesconto(result.getFloat("V.desconto"));
                v.setCliente(c);
                v.setFuncionario(f);
                v.setListaItensVendidos(listaItemVenda);

                listaVenda.add(v);

            }

        } catch (Exception e) {
        }

        return listaVenda;
    }
}
