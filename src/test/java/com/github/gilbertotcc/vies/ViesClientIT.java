package com.github.gilbertotcc.vies;

import static com.github.gilbertotcc.vies.VatNumber.Country.ITALY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import com.github.gilbertotcc.vies.converter.CheckVatResponseToVatNumberInformationConverter;
import com.github.gilbertotcc.vies.converter.VatNumberToCheckVatRequestConverter;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatService;
import org.junit.Test;

public class ViesClientIT {

  private static final ViesClient TEST_VIES_CLIENT = new ViesClientImpl(
    checkVatTestService(),
    new VatNumberToCheckVatRequestConverter(),
    new CheckVatResponseToVatNumberInformationConverter()
  );

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

  @Test
  public void checkVatNumberShouldSuccess() throws ViesServiceException {
    VatNumberInformation res = TEST_VIES_CLIENT.checkVatNumber(VatNumber.vatNumber(ITALY, "100"));

    assertTrue(res.isValid());
    assertNotNull(res.getBusiness().getName());
    assertNotNull(res.getBusiness().getAddress());
  }

  @Test
  public void checkInvalidVatNumberShouldSuccessWithNotValidVatNumber() throws ViesServiceException {
    VatNumberInformation res = TEST_VIES_CLIENT.checkVatNumber(VatNumber.vatNumber(ITALY, "200"));

    assertFalse(res.isValid());
    assertNull(res.getBusiness());
  }
}
