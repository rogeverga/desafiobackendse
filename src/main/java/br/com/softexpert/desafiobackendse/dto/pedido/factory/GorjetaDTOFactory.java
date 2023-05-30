package br.com.softexpert.desafiobackendse.dto.pedido.factory;

import br.com.softexpert.desafiobackendse.dto.pedido.GorjetaDTO;
import br.com.softexpert.desafiobackendse.enumerator.UnidadeEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GorjetaDTOFactory {

    public GorjetaDTO criar(BigDecimal valor, UnidadeEnum unidadeEnum) {
        GorjetaDTO gorjeta = new GorjetaDTO();

        gorjeta.setUnidade(unidadeEnum);
        gorjeta.setValor(valor);

        return gorjeta;
    }

}
