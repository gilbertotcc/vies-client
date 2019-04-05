package com.github.gilbertotcc.vies;

import com.github.gilbertotcc.vies.converter.CheckVatResponseToVatNumberInformationConverter;
import com.github.gilbertotcc.vies.converter.VatNumberToCheckVatRequestConverter;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatService;
import io.vavr.Lazy;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ViesClientImpl implements ViesClient {

  static final Lazy<ViesClient> INSTANCE = Lazy.of(() -> new ViesClientImpl(
    new CheckVatService(),
    new VatNumberToCheckVatRequestConverter(),
    new CheckVatResponseToVatNumberInformationConverter()
  ));

  private CheckVatService checkVatService;

  private VatNumberToCheckVatRequestConverter vatNumberToCheckVatRequestConverter;

  private CheckVatResponseToVatNumberInformationConverter checkVatResponseToVatNumberInformationConverter;

  @Override
  public VatNumberInformation checkVatNumber(final VatNumber vatNumber) throws CheckVatNumberException {
    return Try.of(() -> vatNumberToCheckVatRequestConverter.convert(vatNumber))
      .map(req -> checkVatService.getCheckVatPort().checkVat(req))
      .map(res -> checkVatResponseToVatNumberInformationConverter.convert(res))
      .getOrElseThrow(error -> new CheckVatNumberException(vatNumber, error));
  }
}
