package com.github.gilbertotcc.vies;

public interface ViesClient {

  static ViesClient create() {
    return new ViesClientImpl();
  }

  VatNumberInformation checkVatNumber(final VatNumber vatNumber) throws ViesServiceException;
}
