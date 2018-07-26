package com.github.gilbertotcc.vies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.gilbertotcc.vies.model.VatNumberInformation;
import com.github.gilbertotcc.vies.model.VatNumber;
import org.junit.Test;

public class ViesClientIT {

    private static final String VALID_ITALIAN_VAT_NUMBER = System.getenv("VALID_ITALIAN_VAT_NUMBER");

    @Test
    public void checkVatNumberShouldSuccess() {
        VatNumberInformation res = ViesClient.create().checkVatNumber(VatNumber.of("IT", VALID_ITALIAN_VAT_NUMBER));

        assertEquals(true, res.isValid());
        assertNotNull(res.getBusinessInformation().getName());
        assertNotNull(res.getBusinessInformation().getAddress());
    }

}
