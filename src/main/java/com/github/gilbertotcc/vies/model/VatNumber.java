package com.github.gilbertotcc.vies.model;

import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@AllArgsConstructor(staticName = "of")
public class VatNumber {

    private Country country;
    private String number;

    public CheckVat asCheckVat() {
        final CheckVat checkVatRequest = new CheckVat();
        checkVatRequest.setCountryCode(country.getCode());
        checkVatRequest.setVatNumber(number);
        return checkVatRequest;
    }

    @Override
    public String toString() {
        return String.format("(%s) %s", country.getCode(), number);
    }
}
