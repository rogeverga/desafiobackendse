package br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentLinksRequest {

    private String billingType;

    private String chargeType;

    private String description;

    private Integer dueDateLimitDays;

    private String endDate;

    private String name;

    private BigDecimal value;

}