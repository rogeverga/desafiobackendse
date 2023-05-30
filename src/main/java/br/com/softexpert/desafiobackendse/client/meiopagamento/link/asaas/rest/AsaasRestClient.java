package br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest;

import br.com.softexpert.desafiobackendse.client.meiopagamento.link.MeioPagamentoLinkClient;
import br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest.request.PaymentLinksRequest;
import br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest.request.factory.PaymentLinkRequestFactory;
import br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest.response.PaymentLinksResponse;
import br.com.softexpert.desafiobackendse.dto.pagamento.LinkPagamentoDTO;
import br.com.softexpert.desafiobackendse.exception.ErroIntegracaoMeioPagamento;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AsaasRestClient implements MeioPagamentoLinkClient {

    @Autowired
    private PaymentLinkRequestFactory paymentLinkRequestFactory;

    @Autowired
    @Qualifier("asaasRestClient")
    private WebClient webClient;

    @Override
    @SneakyThrows
    public Mono<LinkPagamentoDTO> criarLink(LinkPagamentoDTO linkPagamento) {
        return
            Flux
                .fromIterable(linkPagamento.getPedido().getListas())
                .flatMap(lista -> {
                    PaymentLinksRequest paymentLinksRequest = paymentLinkRequestFactory.criar(lista);

                    return
                        webClient
                            .post()
                            .uri("/api/v3/paymentLinks")
                            .bodyValue(paymentLinksRequest)
                            .retrieve()
                            .onStatus(HttpStatus::is3xxRedirection, clientResponse -> {
                                return Mono.error(ErroIntegracaoMeioPagamento::new);
                            }).onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                                return Mono.error(ErroIntegracaoMeioPagamento::new);
                            }).onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                                return Mono.error(ErroIntegracaoMeioPagamento::new);
                            })
                            .bodyToMono(PaymentLinksResponse.class)
                            .flatMap(paymentLinksResponse -> {
                                lista.setUrlPagamento(paymentLinksResponse.getUrl());

                                return Mono.just(lista);
                            });
                })
                .then(Mono.fromCallable(() -> {
                    return linkPagamento;
                }));
    }

}