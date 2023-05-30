package br.com.softexpert.desafiobackendse.dto.factory;

import br.com.softexpert.desafiobackendse.dto.pagamento.LinkPagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LinkPagamentoDTOFactory {

    @Autowired
    private PedidoDTOFactory pedidoDTOFactory;

    public LinkPagamentoDTO criar() {
        LinkPagamentoDTO linkPagamento = new LinkPagamentoDTO();

        linkPagamento.setPedido(pedidoDTOFactory.criar());

        return linkPagamento;
    }

}
