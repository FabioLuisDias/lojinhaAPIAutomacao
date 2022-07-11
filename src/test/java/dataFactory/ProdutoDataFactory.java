package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComOValorIgualA(double valor) {
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("PlayStation 7");
        produto.setProdutoValor(0.00);

        List<String> cores = new ArrayList<>();
        cores.add("Preto");
        cores.add("Dourado");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Controle");
        componente.setComponenteQuantidade(2);
        componentes.add(componente);

        ComponentePojo segundoComponente = new ComponentePojo();
        segundoComponente.setComponenteNome("Jogo de Futebol");
        segundoComponente.setComponenteQuantidade(1);
        componentes.add(componente);

        produto.setComponentes(componentes);

        return produto;
    }
}
