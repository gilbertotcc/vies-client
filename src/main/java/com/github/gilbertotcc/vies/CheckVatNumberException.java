package com.github.gilbertotcc.vies;

import static java.lang.String.format;

public class CheckVatNumberException extends Exception {

  private static final long serialVersionUID = 4518195240220996717L;

  CheckVatNumberException(VatNumber vatNumber, Throwable cause) {
    super(format("Cannot check VAT number %s", vatNumber), cause);
  }
}
