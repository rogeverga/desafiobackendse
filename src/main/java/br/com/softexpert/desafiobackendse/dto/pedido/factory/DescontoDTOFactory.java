package br.com.softexpert.desafiobackendse.dto.pedido.factory;

import br.com.softexpert.desafiobackendse.dto.pedido.DescontoDTO;
import br.com.softexpert.desafiobackendse.enumerator.UnidadeEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DescontoDTOFactory {

    public DescontoDTO criar(BigDecimal valor, UnidadeEnum unidadeEnum) {
        DescontoDTO desconto = new DescontoDTO();

        desconto.setUnidade(unidadeEnum);
        desconto.setValor(valor);

        return desconto;
    }

}