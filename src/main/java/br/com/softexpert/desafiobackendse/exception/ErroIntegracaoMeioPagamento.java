package br.com.softexpert.desafiobackendse.exception;

public class ErroIntegracaoMeioPagamento extends RuntimeException {

    public ErroIntegracaoMeioPagamento() {
        super("Não foi possivel integrar com o meio de pagamento");
    }

}
