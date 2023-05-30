package br.com.softexpert.desafiobackendse.client.meiopagamento.link;

import br.com.softexpert.desafiobackendse.dto.pagamento.LinkPagamentoDTO;
import reactor.core.publisher.Mono;

public interface MeioPagamentoLinkClient {

    Mono<LinkPagamentoDTO> criarLink(LinkPagamentoDTO linkPagamento);

}
