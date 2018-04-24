package br.senac.model.validador;

import br.senac.exception.UsuarioException;
import br.senac.model.Usuario;

public class ValidadorUsuario {

    public static void validar(Usuario user) throws UsuarioException {
        if (user == null) {
            throw new UsuarioException("Usuario vazio");
        }
        if (user.getNome() == null || user.getNome().trim().isEmpty()) {
            throw new UsuarioException("Nome de usuario não fornecido");
        }
        if (user.getSenha() == null || user.getSenha().isEmpty()) {
            throw new UsuarioException("Senha do usuario não fornecido");
        }
        if (user.getDataCad() == null) {
            throw new UsuarioException("Data de Cadastro invalida");
        }
        if (user.getPerfil() == null || user.getPerfil().trim().isEmpty() ||
            !"admin".equals(user.getPerfil()) && !"user".equals(user.getPerfil())) {
            throw new UsuarioException("Perfil invalido");
        }
        
    }
}
