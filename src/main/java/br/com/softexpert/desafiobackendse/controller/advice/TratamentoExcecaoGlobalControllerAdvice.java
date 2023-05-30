package br.com.softexpert.desafiobackendse.controller.advice;

import br.com.softexpert.desafiobackendse.dto.ErroDTO;
import br.com.softexpert.desafiobackendse.dto.factory.ErroDTOFactory;
import br.com.softexpert.desafiobackendse.exception.ErroIntegracaoMeioPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class TratamentoExcecaoGlobalControllerAdvice {

    @Autowired
    private ErroDTOFactory erroDTOFactory;

    @ExceptionHandler(ErroIntegracaoMeioPagamento.class)
    public ResponseEntity<ErroDTO> tratarErroIntegracaoMeioPagamento(
            ErroIntegracaoMeioPagamento erroIntegracaoMeioPagamento
    ) {
        ErroDTO erro = erroDTOFactory.criar(erroIntegracaoMeioPagamento.getMessage());

        return ResponseEntity.internalServerError().body(erro);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErroDTO> tratarWebExchangeBindException(
            WebExchangeBindException webExchangeBindException
    ) {
        ErroDTO erro = erroDTOFactory.criar(webExchangeBindException);

        return ResponseEntity.badRequest().body(erro);
    }

}