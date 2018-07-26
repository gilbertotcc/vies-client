package com.github.gilbertotcc.vies.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.github.gilbertotcc.vies.service.CheckVatResponse;
import com.github.gilbertotcc.vies.service.ObjectFactory;
import org.junit.Test;

public class VatNumberInformationTest {

    @Test
    public void vatNumberInformationOfValidCheckVatResponseShouldSuccess() {
        final ObjectFactory objectFactory = new ObjectFactory();
        final CheckVatResponse checkVatResponse = new CheckVatResponse();
        checkVatResponse.setName(objectFactory.createCheckVatResponseName("businessName"));
        checkVatResponse.setAddress(objectFactory.createCheckVatResponseAddress("businessAddress"));
        checkVatResponse.setValid(true);

        final VatNumberInformation vatNumberInformation = VatNumberInformation.of(checkVatResponse);
        assertEquals(true, vatNumberInformation.isValid());
        assertEquals("businessName", vatNumberInformation.getBusinessInformation().getName());
        assertEquals("businessAddress", vatNumberInformation.getBusinessInformation().getAddress());
    }

    @Test
    public void vatNumberInformationOfInvalidCheckVatResponseShouldSuccess() {
        final ObjectFactory objectFactory = new ObjectFactory();
        final CheckVatResponse checkVatResponse = new CheckVatResponse();
        checkVatResponse.setName(null);
        checkVatResponse.setAddress(null);
        checkVatResponse.setValid(false);

        final VatNumberInformation vatNumberInformation = VatNumberInformation.of(checkVatResponse);
        assertEquals(false, vatNumberInformation.isValid());
        assertNull(vatNumberInformation.getBusinessInformation());
    }

    @Test
    public void vatNumberInformationOfValidCheckVatResponseWithoutAddressShouldSuccess() {
        final ObjectFactory objectFactory = new ObjectFactory();
        final CheckVatResponse checkVatResponse = new CheckVatResponse();
        checkVatResponse.setName(objectFactory.createCheckVatResponseName("businessName"));
        checkVatResponse.setAddress(null);
        checkVatResponse.setValid(true);

        final VatNumberInformation vatNumberInformation = VatNumberInformation.of(checkVatResponse);
        assertEquals(true, vatNumberInformation.isValid());
        assertEquals("businessName", vatNumberInformation.getBusinessInformation().getName());
        assertNull(vatNumberInformation.getBusinessInformation().getAddress());
    }

    @Test
    public void vatNumberInformationOfValidCheckVatResponseWithoutNameShouldSuccess() {
        final ObjectFactory objectFactory = new ObjectFactory();
        final CheckVatResponse checkVatResponse = new CheckVatResponse();
        checkVatResponse.setName(null);
        checkVatResponse.setAddress(objectFactory.createCheckVatResponseAddress("businessAddress"));
        checkVatResponse.setValid(true);

        final VatNumberInformation vatNumberInformation = VatNumberInformation.of(checkVatResponse);
        assertEquals(true, vatNumberInformation.isValid());
        assertNull(vatNumberInformation.getBusinessInformation().getName());
        assertEquals("businessAddress", vatNumberInformation.getBusinessInformation().getAddress());
    }
}
