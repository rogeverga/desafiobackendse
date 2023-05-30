package br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest.request.factory;

import br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest.request.PaymentLinksRequest;
import br.com.softexpert.desafiobackendse.dto.pedido.ListaDTO;
import br.com.softexpert.desafiobackendse.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.stream.Collectors;

@Component
public class PaymentLinkRequestFactory {

    private static final String BILLING_TYPE = "UNDEFINED";
    private static final String CHARGE_TYPE = "DETACHED";
    private static final Integer DUE_DATE_LIMIT_DAYS = 10;

    @Autowired
    private StringUtil stringUtil;

    public PaymentLinksRequest criar(ListaDTO lista) {
        PaymentLinksRequest paymentLinksRequest = new PaymentLinksRequest();

        paymentLinksRequest.setBillingType(BILLING_TYPE);
        paymentLinksRequest.setChargeType(CHARGE_TYPE);
        paymentLinksRequest.setDescription(
                lista
                    .getItens()
                    .stream()
                    .map(item -> {
                        return
                            new StringBuilder()
                                    .append("- ")
                                    .append(item.getNome())
                                    .append(": R$ ")
                                    .append(
                                        stringUtil.substituir(
                                                item.getValor().toString(),
                                                ".",
                                                ","
                                        )
                                    )
                                    .toString();
                    })
                    .collect(Collectors.joining("\n"))
        );
        paymentLinksRequest.setDueDateLimitDays(DUE_DATE_LIMIT_DAYS);
        paymentLinksRequest.setEndDate(
                new StringBuilder()
                        .append(YearMonth.now().getYear())
                        .append("-")
                        .append(YearMonth.now().getMonthValue())
                        .append("-")
                        .append(LocalDate.now().getDayOfMonth())
                        .toString()
        );
        paymentLinksRequest.setName(
                new StringBuilder()
                        .append("Pedido de ")
                        .append(lista.getNome())
                        .toString()
        );
        paymentLinksRequest.setValue(lista.getValorFinal());

        return paymentLinksRequest;
    }

}