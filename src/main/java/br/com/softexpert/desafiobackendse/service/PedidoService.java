package br.com.softexpert.desafiobackendse.service;

import br.com.softexpert.desafiobackendse.dto.PedidoDTO;
import br.com.softexpert.desafiobackendse.dto.pedido.ListaDTO;
import br.com.softexpert.desafiobackendse.util.CalculoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PedidoService  {

    @Autowired
    private CalculoUtil calculoUtil;

    public void aplicarDescontosAcrescimos(PedidoDTO pedido) {
        log.info("Calculando valor bruto por lista");

        calcularValorBrutoPorLista(pedido);

        log.info("Calculando valor somatoria itens das listas");

        calcularValorSomatoriaItensListas(pedido);

        log.info("Calculando desconto por lista");

        calcularDescontoPorLista(pedido);

        log.info("Calculando desconto do pedido");

        calcularDescontoPedido(pedido);

        switch (pedido.getTipo()) {
            case ENTREGA:
                log.info("Calculando taxa de entrega por lista");

                calcularTaxaEntregaPorLista(pedido);

                break;

            case PRESENCIAL:
                log.info("Calculando gorjeta por lista");

                calcularGorjetaPorLista(pedido);

                log.info("Calculando gorjeta pedido");

                calcularGorjetaPedido(pedido);

                break;
        }

        log.info("Calculando valor liquido do pedido");

        calcularValorLiquidoPedido(pedido);
    }

    private void calcularDescontoPedido(PedidoDTO pedido) {
        BigDecimal valorDescontoPedido =
                pedido
                        .getListas()
                        .stream()
                        .map(lista -> {
                            return lista.getValorDesconto();
                        })
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal valorAposDescontoPedido =
                pedido
                        .getListas()
                        .stream()
                        .map(lista -> {
                            return lista.getValorAposDesconto();
                        })
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValorDesconto(valorDescontoPedido);
        pedido.setValorAposDesconto(valorAposDescontoPedido);
    }

    private void calcularDescontoPorLista(PedidoDTO pedido) {
        pedido
                .getListas()
                .stream()
                .forEach(lista -> {
                    BigDecimal percentualEmRelacaoAoPedido = BigDecimal.ZERO;
                    BigDecimal valorDesconto = BigDecimal.ZERO;

                    switch (pedido.getDesconto().getUnidade()) {
                        case PORCENTAGEM:
                            percentualEmRelacaoAoPedido = pedido.getDesconto().getValor();
                            valorDesconto =
                                    calculoUtil.obterPercentualSobreValor(
                                            lista.getValorSomatoriaItens(),
                                            percentualEmRelacaoAoPedido
                                    );

                            break;

                        case REAL:
                            percentualEmRelacaoAoPedido = obterPorcentagemEmRelacaoAoPedido(pedido, lista);
                            valorDesconto =
                                    calculoUtil.obterPercentualSobreValor(
                                            pedido.getDesconto().getValor(),
                                            percentualEmRelacaoAoPedido
                                    );

                            break;
                    }

                    BigDecimal valorAposDesconto =
                            calculoUtil.subtrair(lista.getValorSomatoriaItens(), valorDesconto);

                    lista.setValorDesconto(valorDesconto);
                    lista.setValorAposDesconto(valorAposDesconto);
                });
    }

    private void calcularGorjetaPorLista(PedidoDTO pedido) {
        pedido
                .getListas()
                .stream()
                .forEach(lista -> {
                    BigDecimal percentualEmRelacaoAoPedido = BigDecimal.ZERO;
                    BigDecimal valorGorjeta = BigDecimal.ZERO;

                    switch (pedido.getGorjeta().getUnidade()) {
                        case PORCENTAGEM:
                            percentualEmRelacaoAoPedido = pedido.getGorjeta().getValor();
                            valorGorjeta =
                                    calculoUtil.obterPercentualSobreValor(
                                            lista.getValorAposDesconto(),
                                            percentualEmRelacaoAoPedido
                                    );

                            break;

                        case REAL:
                            percentualEmRelacaoAoPedido = obterPorcentagemEmRelacaoAoPedido(pedido, lista);
                            valorGorjeta =
                                    calculoUtil.obterPercentualSobreValor(
                                            pedido.getGorjeta().getValor(),
                                            percentualEmRelacaoAoPedido
                                    );

                            break;
                    }

                    BigDecimal valorFinal =
                            calculoUtil.adicionar(lista.getValorAposDesconto(), valorGorjeta);

                    lista.setValorGorjeta(valorGorjeta);
                    lista.setValorFinal(valorFinal);
                });
    }

    private void calcularGorjetaPedido(PedidoDTO pedido) {
        BigDecimal valorGorjeta =
                pedido
                        .getListas()
                        .stream()
                        .map(lista -> {
                            return lista.getValorGorjeta();
                        })
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValorGorjeta(valorGorjeta);
    }

    private void calcularTaxaEntregaPorLista(PedidoDTO pedido) {
        pedido
                .getListas()
                .stream()
                .forEach(lista -> {
                    BigDecimal porcentagemEmRelacaoAoPedido =
                            obterPorcentagemEmRelacaoAoPedido(pedido, lista);
                    BigDecimal valorTaxaEntrega =
                            calculoUtil.obterPercentualSobreValor(
                                    pedido.getValorTaxaEntrega(),
                                    porcentagemEmRelacaoAoPedido
                            );
                    BigDecimal valorFinal =
                            calculoUtil.adicionar(lista.getValorAposDesconto(), valorTaxaEntrega);

                    lista.setValorTaxaEntrega(valorTaxaEntrega);
                    lista.setValorFinal(valorFinal);
                });
    }

    private void calcularValorSomatoriaItensListas(PedidoDTO pedido) {
        BigDecimal valorSomatoriaItensListas =
                pedido
                        .getListas()
                        .stream()
                        .map(lista -> {
                            return lista.getValorSomatoriaItens();
                        })
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValorSomatoriaItensListas(valorSomatoriaItensListas);
    }

    private void calcularValorBrutoPorLista(PedidoDTO pedido) {
        pedido
                .getListas()
                .stream()
                .forEach(lista -> {
                    BigDecimal valorSomatoriaItens =
                            lista
                                    .getItens()
                                    .stream()
                                    .map(item -> {
                                        return item.getValor();
                                    })
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);

                    lista.setValorSomatoriaItens(valorSomatoriaItens);
                });
    }

    private void calcularValorLiquidoPedido(PedidoDTO pedido) {
        BigDecimal valorFinalPedido =
                pedido
                        .getListas()
                        .stream()
                        .map(lista -> {
                            return lista.getValorFinal();
                        })
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValorFinal(valorFinalPedido);
    }

    private BigDecimal obterPorcentagemEmRelacaoAoPedido(PedidoDTO pedido, ListaDTO lista) {
        return calculoUtil.obterPorcentagem(lista.getValorSomatoriaItens(), pedido.getValorSomatoriaItensListas());
    }

}