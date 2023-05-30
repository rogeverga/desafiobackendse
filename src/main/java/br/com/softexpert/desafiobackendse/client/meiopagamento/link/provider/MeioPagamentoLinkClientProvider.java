package br.com.softexpert.desafiobackendse.client.meiopagamento.link.provider;

import br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest.AsaasRestClient;
import br.com.softexpert.desafiobackendse.dto.pagamento.LinkPagamentoDTO;
import br.com.softexpert.desafiobackendse.dto.pedido.ListaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MeioPagamentoLinkClientProvider {

    @Autowired
    private AsaasRestClient asaasRestClient;

    public Mono<LinkPagamentoDTO> criarLink(LinkPagamentoDTO linkPagamento) {
        return asaasRestClient.criarLink(linkPagamento);
    }

}
