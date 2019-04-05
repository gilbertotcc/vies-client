package com.github.gilbertotcc.vies.converter;

import java.util.function.Function;

import javax.xml.bind.JAXBElement;

import com.github.gilbertotcc.vies.VatNumberInformation;
import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVatResponse;
import io.vavr.control.Option;

public class CheckVatResponseToVatNumberInformationConverter
  implements Converter<CheckVatResponse, VatNumberInformation> {

  @Override
  public VatNumberInformation convert(final CheckVatResponse checkVatResponse) {
    return checkVatResponse.isValid()
      ? new VatNumberInformation(true, createBusinessInformation().apply(checkVatResponse))
      : VatNumberInformation.NOT_VALID;
  }

  private static Function<CheckVatResponse, VatNumberInformation.Business> createBusinessInformation() {
    return checkVatResponse -> new VatNumberInformation.Business(
      contentOf(checkVatResponse.getName())
        .getOrNull(),
      contentOf(checkVatResponse.getAddress())
        .map(CheckVatResponseToVatNumberInformationConverter::reformatAddress)
        .getOrNull()
    );
  }

  private static <T> Option<T> contentOf(JAXBElement<T> jaxbElement) {
    return Option.of(jaxbElement)
      .filter(element -> !element.isNil())
      .map(JAXBElement::getValue);
  }

  private static String reformatAddress(String address) {
    return address.replace("\n", " ").replaceAll("\\s{2,}", " ").trim();
  }
}
