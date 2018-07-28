package com.github.gilbertotcc.vies.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountryTest {

    @Test
    public void countryByCodeShouldSuccess() {
        Country country = Country.byCode("IT");
        assertEquals(Country.ITALY, country);
    }

    @Test(expected = IllegalArgumentException.class)
    public void countryByCodeShouldFail() {
        Country.byCode("XXX"); // Invalid country code
    }
}
