package com.github.gilbertotcc.vies.converter;

import com.github.gilbertotcc.vies.VatNumber;
import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVat;

public class VatNumberToCheckVatRequestConverter implements Converter<VatNumber, CheckVat> {

  @Override
  public CheckVat convert(final VatNumber vatNumber) {
    CheckVat checkVatRequest = new CheckVat();
    checkVatRequest.setCountryCode(vatNumber.getCountry().getCode());
    checkVatRequest.setVatNumber(vatNumber.getNumber());
    return checkVatRequest;
  }
}
