package com.github.gilbertotcc.vies.model;

import com.github.gilbertotcc.vies.service.CheckVat;

public class VatNumber {

    private Country country;
    private String number;

    private VatNumber(final Country country, final String number) {
        this.country = country;
        this.number = number;
    }

    public static VatNumber of(final Country country, final String number) {
        return new VatNumber(country, number);
    }

    public CheckVat asCheckVat() {
        final CheckVat checkVatRequest = new CheckVat();
        checkVatRequest.setCountryCode(country.getCode());
        checkVatRequest.setVatNumber(number);
        return checkVatRequest;
    }
}
