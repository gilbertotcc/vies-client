package com.github.gilbertotcc.vies.model;

import com.github.gilbertotcc.vies.service.CheckVat;

public class VatNumber {

    private String countryCode;
    private String number;

    private VatNumber(final String countryCode, final String number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public static VatNumber of(final String countryCode, final String number) {
        return new VatNumber(countryCode, number);
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    public CheckVat asCheckVat() {
        final CheckVat checkVatRequest = new CheckVat();
        checkVatRequest.setCountryCode(countryCode);
        checkVatRequest.setVatNumber(number);
        return checkVatRequest;
    }
}
