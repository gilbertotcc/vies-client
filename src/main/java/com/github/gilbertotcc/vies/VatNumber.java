package com.github.gilbertotcc.vies;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE, staticName = "vatNumber")
public class VatNumber {

  @Getter
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public enum Country {
    AUSTRIA("AT"),
    BELGIUM("BE"),
    BULGARIA("BG"),
    CYPRUS("CY"),
    CZECH_REPUBLIC("CZ"),
    GERMANY("DE"),
    DENMARK("DK"),
    ESTONIA("EE"),
    GREECE("EL"),
    SPAIN("ES"),
    FINLAND("FI"),
    FRANCE("FR"),
    UNITED_KINGDOM("UK"),
    HUNGARY("HU"),
    IRELAND("IE"),
    ITALY("IT"),
    LITHUANIA("LT"),
    LUXEMBOURG("LU"),
    LATVIA("LV"),
    MALTA("MT"),
    THE_NETHERLANDS("NL"),
    POLAND("PL"),
    PORTUGAL("PT"),
    ROMANIA("RO"),
    SWEDEN("SE"),
    SLOVAKIA("SK");

    private String code;

  }

  private Country country;

  private String number;

}
