package com.github.gilbertotcc.vies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.github.gilbertotcc.vies.model.Country;
import com.github.gilbertotcc.vies.model.VatNumber;
import com.github.gilbertotcc.vies.model.VatNumberInformation;

import eu.europa.ec.taxud.vies.services.checkvat.CheckVatPortType;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatService;
import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVat;
import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVatResponse;
import eu.europa.ec.taxud.vies.services.checkvat.types.ObjectFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ViesClientImplTest {

    @Mock
    private CheckVatService checkVatService;

    @Mock
    private CheckVatPortType checkVatPortType;

    @Test
    public void checkVatNumberShouldSuccess() {
        final ObjectFactory objectFactory = new ObjectFactory();
        final CheckVatResponse checkVatResponse = new CheckVatResponse();
        checkVatResponse.setName(objectFactory.createCheckVatResponseName("businessName"));
        checkVatResponse.setAddress(objectFactory.createCheckVatResponseAddress("businessAddress"));
        checkVatResponse.setValid(true);

        when(checkVatPortType.checkVat(any(CheckVat.class))).thenReturn(checkVatResponse);
        when(checkVatService.getCheckVatPort()).thenReturn(checkVatPortType);

        final ViesClientImpl viesClient = new ViesClientImpl(checkVatService);
        VatNumberInformation response = viesClient.checkVatNumber(VatNumber.of(Country.ITALY, "vatNumber"));

        assertTrue(response.isValid());
        assertEquals("businessName", response.getBusinessInformation().getName());
        assertEquals("businessAddress", response.getBusinessInformation().getAddress());
    }

    @Test
    public void createViesClientImplWithoutParametersShouldSuccess() {
        new ViesClientImpl();
    }
}
