package modulos.usuario;

import dataFactory.UsuarioDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import pojo.UtilsPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Usuário")
public class UsuarioTest extends UtilsPojo {

    @BeforeEach //Antes de cada teste, faça algo.
    @Override
    public void caminhoLojinha() {
        super.caminhoLojinha();
    }

    @Test
    @DisplayName("Validar que não é permitido criar um login igual já cadastrado")
    public void testValidarCriarNovoUsuarioJaCadastrado() {
        // Tentar criar um novo usuario com um login já cadastrado e validar que a mensagem de erro foi apresentada e o
        // status code retornado foi 409
        given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdministrador())
        .when()
                .post("/v2/usuarios")
        .then()
                .assertThat()
                    .body("error", equalTo("O usuário admin já existe."))
                    .statusCode(409);
    }
}
