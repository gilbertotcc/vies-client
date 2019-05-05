package com.github.gilbertotcc.vies;

import static com.github.gilbertotcc.vies.VatNumber.Country.ITALY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.github.gilbertotcc.vies.converter.CheckVatResponseToVatNumberInformationConverter;
import com.github.gilbertotcc.vies.converter.VatNumberToCheckVatRequestConverter;
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
  public void checkVatNumberShouldSuccess() throws CheckVatNumberException {
    final ObjectFactory objectFactory = new ObjectFactory();
    final CheckVatResponse checkVatResponse = new CheckVatResponse();
    checkVatResponse.setName(objectFactory.createCheckVatResponseName("businessName"));
    checkVatResponse.setAddress(objectFactory.createCheckVatResponseAddress("businessAddress"));
    checkVatResponse.setValid(true);

    when(checkVatPortType.checkVat(any(CheckVat.class))).thenReturn(checkVatResponse);
    when(checkVatService.getCheckVatPort()).thenReturn(checkVatPortType);

    final ViesClientImpl viesClient = new ViesClientImpl(
      checkVatService,
      new VatNumberToCheckVatRequestConverter(),
      new CheckVatResponseToVatNumberInformationConverter()
    );
    VatNumberInformation response = viesClient.checkVatNumber(VatNumber.vatNumber(ITALY, "vatNumber"));

    assertTrue(response.isValid());
    assertEquals("businessName", response.getBusiness().getName());
    assertEquals("businessAddress", response.getBusiness().getAddress());
  }

  @Test(expected = CheckVatNumberException.class)
  public void checkVatNumberShouldThrowException() throws CheckVatNumberException {
    when(checkVatPortType.checkVat(any(CheckVat.class))).thenThrow(new RuntimeException("Failure"));
    when(checkVatService.getCheckVatPort()).thenReturn(checkVatPortType);
    final ViesClient viesClient = new ViesClientImpl(
      checkVatService,
      new VatNumberToCheckVatRequestConverter(),
      new CheckVatResponseToVatNumberInformationConverter()
    );

    viesClient.checkVatNumber(VatNumber.vatNumber(ITALY, "vatNumber"));
  }
}
