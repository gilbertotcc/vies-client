package com.github.gilbertotcc.vies;

import static java.lang.String.format;

import com.github.gilbertotcc.vies.converter.CheckVatResponseToVatNumberInformationConverter;
import com.github.gilbertotcc.vies.converter.VatNumberToCheckVatRequestConverter;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatService;
import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVat;
import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVatResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ViesClientImpl implements ViesClient {

  private CheckVatService checkVatService;

  private VatNumberToCheckVatRequestConverter vatNumberToCheckVatRequestConverter;

  private CheckVatResponseToVatNumberInformationConverter checkVatResponseToVatNumberInformationConverter;

  ViesClientImpl() {
    this(
      new CheckVatService(),
      new VatNumberToCheckVatRequestConverter(),
      new CheckVatResponseToVatNumberInformationConverter()
    );
  }

  @Override
  public VatNumberInformation checkVatNumber(final VatNumber vatNumber) throws ViesServiceException {
    try {
      CheckVat checkVatRequest = vatNumberToCheckVatRequestConverter.convert(vatNumber);
      CheckVatResponse checkVatResponse = checkVatService.getCheckVatPort().checkVat(checkVatRequest);
      return checkVatResponseToVatNumberInformationConverter.convert(checkVatResponse);
    } catch (Exception e) {
      throw new ViesServiceException(
        format("Error occurred while checking VAT number %s: %s", vatNumber, e.getMessage()), e
      );
    }
  }
}
