package br.com.softexpert.desafiobackendse.dto.factory;

import br.com.softexpert.desafiobackendse.dto.PedidoDTO;
import org.springframework.stereotype.Component;

@Component
public class PedidoDTOFactory {

    public PedidoDTO criar() {
        PedidoDTO pedido = new PedidoDTO();

        return pedido;
    }

}
