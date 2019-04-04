package com.github.gilbertotcc.vies;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VatNumberInformation {

  @Getter
  @AllArgsConstructor
  public static class Business {

    private String name;

    private String address;

  }

  public static final VatNumberInformation NOT_VALID = new VatNumberInformation(false, null);

  private boolean valid;

  private Business business;

}
