package com.github.gilbertotcc.vies;

import static com.github.gilbertotcc.vies.VatNumber.Country.ITALY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import javax.xml.namespace.QName;

import com.github.gilbertotcc.vies.converter.CheckVatResponseToVatNumberInformationConverter;
import com.github.gilbertotcc.vies.converter.VatNumberToCheckVatRequestConverter;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatService;
import io.vavr.control.Try;
import org.junit.BeforeClass;
import org.junit.Test;

public class ViesClientIT {

  private static final VatNumber VALID_TEST_VAT_NUMBER = VatNumber.vatNumber(ITALY, "100");
  private static final VatNumber INVALID_TEST_VAT_NUMBER = VatNumber.vatNumber(ITALY, "200");

  private static ViesClient testViesClient;

  @BeforeClass
  public static void setUp() {
    testViesClient = Try
      .of(() -> new CheckVatService(
        new URL("http://ec.europa.eu/taxation_customs/vies/checkVatTestService.wsdl"),
        new QName("urn:ec.europa.eu:taxud:vies:services:checkVat", "checkVatTestService")
      ))
      .map(checkVatService -> new ViesClientImpl(
        checkVatService,
        new VatNumberToCheckVatRequestConverter(),
        new CheckVatResponseToVatNumberInformationConverter()
      ))
      .getOrElseThrow(e -> new RuntimeException("Cannot initialize test VIES client", e));
  }

  @Test
  public void checkVatNumberShouldSuccess() throws CheckVatNumberException {
    VatNumberInformation res = testViesClient.checkVatNumber(VALID_TEST_VAT_NUMBER);

    assertTrue(res.isValid());
    assertNotNull(res.getBusiness().getName());
    assertNotNull(res.getBusiness().getAddress());
  }

  @Test
  public void checkInvalidVatNumberShouldSuccessWithNotValidVatNumber() throws CheckVatNumberException {
    VatNumberInformation res = testViesClient.checkVatNumber(INVALID_TEST_VAT_NUMBER);

    assertFalse(res.isValid());
    assertNull(res.getBusiness());
  }
}
