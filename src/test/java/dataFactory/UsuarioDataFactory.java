package dataFactory;

import pojo.UsuarioPojo;

public class UsuarioDataFactory {
    public static UsuarioPojo criarUsuarioAdministrador(){

        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioNome("Admintrador");
        usuario.setUsuarioLogin("admin");
        usuario.setUsuarioSenha("admin");

        return usuario;
    }

}
