package br.com.softexpert.desafiobackendse.dto.pedido.factory;

import br.com.softexpert.desafiobackendse.dto.pedido.ListaDTO;
import br.com.softexpert.desafiobackendse.dto.pedido.lista.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ListaDTOFactory {

    public ListaDTO criar(ItemDTO item) {
        ListaDTO lista = new ListaDTO();

        lista.setItens(Arrays.asList(item));

        return lista;
    }

}
