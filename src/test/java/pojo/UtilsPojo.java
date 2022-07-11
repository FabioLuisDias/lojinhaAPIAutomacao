package pojo;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class UtilsPojo {
    public void caminhoLojinha() {
        // Configurando os dados da API Rest da Lojinha
        baseURI = "http://165.227.93.41";
        // port = 8080; Porta utilizada quando necessário
        basePath = "/lojinha";
    }
}
