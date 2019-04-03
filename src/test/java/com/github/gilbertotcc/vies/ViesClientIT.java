package com.github.gilbertotcc.vies;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import com.github.gilbertotcc.vies.model.Country;
import com.github.gilbertotcc.vies.model.VatNumberInformation;
import com.github.gilbertotcc.vies.model.VatNumber;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatService;
import org.junit.Test;

public class ViesClientIT {

    private static final ViesClient TEST_VIES_CLIENT = new ViesClientImpl(checkVatTestService());

    @Test
    public void checkVatNumberShouldSuccess() {
        VatNumberInformation res = TEST_VIES_CLIENT.checkVatNumber(VatNumber.of(Country.ITALY, "100"));

        assertTrue(res.isValid());
        assertNotNull(res.getBusinessInformation().getName());
        assertNotNull(res.getBusinessInformation().getAddress());
    }

    @Test
    public void checkInvalidVatNumberShouldSuccessWithNotValidVatNumber() {
        VatNumberInformation res = TEST_VIES_CLIENT.checkVatNumber(VatNumber.of(Country.ITALY, "200"));

        assertFalse(res.isValid());
        assertNull(res.getBusinessInformation());
    }

    private static CheckVatService checkVatTestService() {
        try {
            return new CheckVatService(
                    new URL("http://ec.europa.eu/taxation_customs/vies/checkVatTestService.wsdl"),
                    new QName("urn:ec.europa.eu:taxud:vies:services:checkVat", "checkVatTestService")
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException("Cannot initialize test 'checkVatTestService'", e);
        }
    }
}
