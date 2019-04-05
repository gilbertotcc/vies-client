package com.github.gilbertotcc.vies;

public interface ViesClient {

  static ViesClient create() {
    return ViesClientImpl.INSTANCE.get();
  }

  VatNumberInformation checkVatNumber(final VatNumber vatNumber) throws CheckVatNumberException;
}
