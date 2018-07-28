package com.github.gilbertotcc.vies.model;

import java.util.stream.Stream;

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
    SLOVAKIA("SK")
    ;

    private final String code;

    Country(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Country byCode(String countryCode) {
        return Stream.of(values()).filter(country -> country.code.equals(countryCode)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid country code '%s'", countryCode)));
    }
}
