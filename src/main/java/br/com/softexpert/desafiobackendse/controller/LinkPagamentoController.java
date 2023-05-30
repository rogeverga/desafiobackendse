package br.com.softexpert.desafiobackendse.controller;

import br.com.softexpert.desafiobackendse.dto.pagamento.LinkPagamentoDTO;
import br.com.softexpert.desafiobackendse.service.LinkPagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
@RequestMapping("/pagamentos/link")
@RestController
@Slf4j
public class LinkPagamentoController {

    @Autowired
    private LinkPagamentoService linkPagamentoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<LinkPagamentoDTO>> criar(@RequestBody @Valid LinkPagamentoDTO linkPagamento) {
        return ResponseEntity.ok(linkPagamentoService.criar(linkPagamento));
    }

}
