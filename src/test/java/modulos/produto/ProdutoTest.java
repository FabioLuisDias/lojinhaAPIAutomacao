package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.UtilsPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Produto")
public class ProdutoTest extends UtilsPojo {
    private String token;

    @BeforeEach //Antes de cada teste, faça algo.
    @Override
    public void caminhoLojinha() {
        super.caminhoLojinha();

        // Obter o token do usuario admin
        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdministrador())
            .when()
                .post("/v2/login")
            .then()
                .extract()
                    .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 0.00 não é permitido")
    public void testValidarLimitesZeradoProibidoValorProduto(){
        // Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada e o
        // status code retornado foi 422
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(0.00))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()//Significa: valide que
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))// equalTo é "igual a"
                    .statusCode(422); //Valida o status code
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 7000.01 não é permitido")
    public void testValidarLimitesMaiorSeteMilProibidoValorProduto(){
        // Tentar inserir um produto com valor 7000.01 e validar que a mensagem de erro foi apresentada e o
        // status code retornado foi 422
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(7000.01))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()//Significa: valide que
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))// equalTo é "igual a"
                    .statusCode(422); //Valida o status code
    }

    @Test
    @DisplayName("Validar que só consigo listar os produtos do usuário pelo token")
    public void testValidarListaDeProdutosDoUsuarioPeloToken(){
        // Tentar listar os produtos de determinado usuário existente e validar o status code retornado foi 200
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
        .when()
                .get("/v2/produtos")
        .then()
                .assertThat()//Significa: valide que:
                    .statusCode(200); //Valida o status code sucesso.
    }

}
