package br.com.softexpert.desafiobackendse.service;

import br.com.softexpert.desafiobackendse.client.meiopagamento.link.provider.MeioPagamentoLinkClientProvider;
import br.com.softexpert.desafiobackendse.dto.pagamento.LinkPagamentoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LinkPagamentoService {

    @Autowired
    private MeioPagamentoLinkClientProvider meioPagamentoLinkClientProvider;

    @Autowired
    private PedidoService pedidoService;

    public Mono<LinkPagamentoDTO> criar(LinkPagamentoDTO linkPagamento) {
        log.info("Aplicando descontos e acrescimos no pedido");

        pedidoService.aplicarDescontosAcrescimos(linkPagamento.getPedido());

        log.info("Criando link de pagamento");

        return meioPagamentoLinkClientProvider.criarLink(linkPagamento);
    }

}