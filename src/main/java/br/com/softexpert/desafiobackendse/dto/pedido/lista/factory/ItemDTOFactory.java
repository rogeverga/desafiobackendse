package br.com.softexpert.desafiobackendse.dto.pedido.lista.factory;

import br.com.softexpert.desafiobackendse.dto.pedido.lista.ItemDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ItemDTOFactory {

    public ItemDTO criar(String nome, BigDecimal valor) {
        ItemDTO item = new ItemDTO();

        item.setNome(nome);
        item.setValor(valor);

        return item;
    }

}
