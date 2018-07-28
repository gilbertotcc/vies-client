package com.github.gilbertotcc.vies;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.github.gilbertotcc.vies.model.Country;
import com.github.gilbertotcc.vies.model.VatNumberInformation;
import com.github.gilbertotcc.vies.model.VatNumber;
import org.junit.Test;

public class ViesClientIT {

    private static final String VALID_ITALIAN_VAT_NUMBER = System.getenv("VALID_ITALIAN_VAT_NUMBER");

    @Test
    public void checkVatNumberShouldSuccess() {
        VatNumberInformation res = ViesClient.create().checkVatNumber(VatNumber.of(Country.ITALY, VALID_ITALIAN_VAT_NUMBER));

        assertTrue(res.isValid());
        assertNotNull(res.getBusinessInformation().getName());
        assertNotNull(res.getBusinessInformation().getAddress());
    }

    @Test
    public void checkInvalidVatNumberShouldSuccessWithNotValidVatNumber() {
        VatNumberInformation res = ViesClient.create().checkVatNumber(VatNumber.of(Country.ITALY, "01234567890"));

        assertFalse(res.isValid());
        assertNull(res.getBusinessInformation());
    }
}
