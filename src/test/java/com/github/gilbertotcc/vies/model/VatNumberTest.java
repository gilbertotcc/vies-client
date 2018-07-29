package com.github.gilbertotcc.vies.model;

import static org.junit.Assert.assertEquals;

import com.github.gilbertotcc.vies.service.CheckVat;
import org.junit.Test;

public class VatNumberTest {

    @Test
    public void vatNumberAsCheckVatShouldSuccess() {
        VatNumber vatNumber = VatNumber.of(Country.ITALY, "vatNumber");

        CheckVat checkVat = vatNumber.asCheckVat();

        assertEquals("IT", checkVat.getCountryCode());
        assertEquals("vatNumber", checkVat.getVatNumber());
    }

    @Test
    public void vatNumberToStringShouldReturnRightString() {
        VatNumber vatNumber = VatNumber.of(Country.ITALY, "vatNumber");
        assertEquals("(IT) vatNumber", vatNumber.toString());
    }
}
