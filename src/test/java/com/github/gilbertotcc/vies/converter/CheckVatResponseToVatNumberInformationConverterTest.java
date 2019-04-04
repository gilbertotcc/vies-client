package com.github.gilbertotcc.vies.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.github.gilbertotcc.vies.VatNumberInformation;
import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVatResponse;
import eu.europa.ec.taxud.vies.services.checkvat.types.ObjectFactory;
import org.junit.BeforeClass;
import org.junit.Test;

public class CheckVatResponseToVatNumberInformationConverterTest {

  private static CheckVatResponseToVatNumberInformationConverter converter;

  @BeforeClass
  public static void setup() {
    converter = new CheckVatResponseToVatNumberInformationConverter();
  }

  @Test
  public void thatConverterSuccessfullyConvertValidCheckVatResponse() {
    CheckVatResponse checkVatResponse = createCheckVatResponse("businessName", "businessAddress", true);

    VatNumberInformation vatNumberInformation = converter.convert(checkVatResponse);

    assertTrue(vatNumberInformation.isValid());
    assertEquals("businessName", vatNumberInformation.getBusiness().getName());
    assertEquals("businessAddress", vatNumberInformation.getBusiness().getAddress());
  }

  @Test
  public void thatConverterSuccessfullyConvertValidCheckVatResponseWithMultilineAddress() {
    CheckVatResponse checkVatResponse = createCheckVatResponse("businessName", "address \ncity \n", true);

    VatNumberInformation vatNumberInformation = converter.convert(checkVatResponse);

    assertTrue(vatNumberInformation.isValid());
    assertEquals("businessName", vatNumberInformation.getBusiness().getName());
    assertEquals("address city", vatNumberInformation.getBusiness().getAddress());
  }

  @Test
  public void thatConverterSuccessfullyConvertNotValidCheckVatResponse() {
    CheckVatResponse checkVatResponse = createCheckVatResponse(null, null, false);

    VatNumberInformation vatNumberInformation = converter.convert(checkVatResponse);

    assertFalse(vatNumberInformation.isValid());
    assertNull(vatNumberInformation.getBusiness());
  }

  @Test
  public void thatConverterSuccessfullyConvertValidCheckVatResponseWithoutName() {
    CheckVatResponse checkVatResponse = createCheckVatResponse(null, "businessAddress", true);

    VatNumberInformation vatNumberInformation = converter.convert(checkVatResponse);

    assertTrue(vatNumberInformation.isValid());
    assertNull(vatNumberInformation.getBusiness().getName());
    assertEquals("businessAddress", vatNumberInformation.getBusiness().getAddress());
  }

  @Test
  public void thatConverterSuccessfullyConvertValidCheckVatResponseWithoutAddress() {
    CheckVatResponse checkVatResponse = createCheckVatResponse("businessName", null, true);

    VatNumberInformation vatNumberInformation = converter.convert(checkVatResponse);

    assertTrue(vatNumberInformation.isValid());
    assertEquals("businessName", vatNumberInformation.getBusiness().getName());
    assertNull(vatNumberInformation.getBusiness().getAddress());
  }

  private CheckVatResponse createCheckVatResponse(String name, String address, boolean valid) {
    ObjectFactory objectFactory = new ObjectFactory();
    CheckVatResponse checkVatResponse = new CheckVatResponse();
    checkVatResponse.setName(objectFactory.createCheckVatResponseName(name));
    checkVatResponse.setAddress(objectFactory.createCheckVatResponseAddress(address));
    checkVatResponse.setValid(valid);
    return checkVatResponse;
  }
}
