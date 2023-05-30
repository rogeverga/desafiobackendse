package br.com.softexpert.desafiobackendse.util;

import br.com.softexpert.desafiobackendse.constant.NumeroConstant;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CalculoUtil {

    public BigDecimal adicionar(BigDecimal valor1, BigDecimal valor2) {
        return valor1.add(valor2);
    }

    public BigDecimal obterPorcentagem(BigDecimal primeiroValor, BigDecimal segundoValor) {
        return
            primeiroValor
                .divide(segundoValor, 4, RoundingMode.HALF_DOWN)
                .multiply(NumeroConstant.CEM)
                .divide(BigDecimal.ONE, 2, RoundingMode.HALF_DOWN);
    }

    public BigDecimal obterPercentualSobreValor(BigDecimal valor, BigDecimal porcentagem) {
        return valor.multiply(porcentagem).divide(NumeroConstant.CEM, 2, RoundingMode.HALF_DOWN);
    }

    public BigDecimal subtrair(BigDecimal valor1, BigDecimal valor2) {
        return valor1.subtract(valor2);
    }

}
