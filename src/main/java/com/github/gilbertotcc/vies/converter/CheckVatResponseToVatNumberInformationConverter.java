package com.github.gilbertotcc.vies.converter;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.xml.bind.JAXBElement;

import com.github.gilbertotcc.vies.VatNumberInformation;
import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVatResponse;

public class CheckVatResponseToVatNumberInformationConverter
  implements Converter<CheckVatResponse, VatNumberInformation> {

  @Override
  public VatNumberInformation convert(final CheckVatResponse checkVatResponse) {
    return checkVatResponse.isValid()
      ? new VatNumberInformation(
      true, Optional.of(checkVatResponse)
      .filter(businessInformationIsNotEmpty())
      .map(createBusinessInformation())
      .orElse(null))
      : VatNumberInformation.NOT_VALID;
  }

  private static Function<CheckVatResponse, VatNumberInformation.Business> createBusinessInformation() {
    return checkVatResponse -> {
      String businessName = contentOf(checkVatResponse::getName).orElse(null);
      String businessAddress = contentOf(checkVatResponse::getAddress)
        .map(address -> address.replace("\n", " ").replaceAll("\\s{2,}", " ").trim())
        .orElse(null);
      return new VatNumberInformation.Business(businessName, businessAddress);
    };
  }

  private static <T> Optional<T> contentOf(Supplier<JAXBElement<T>> jaxbElementSupplier) {
    return Optional.ofNullable(jaxbElementSupplier.get()).map(JAXBElement::getValue);
  }

  private static Predicate<CheckVatResponse> businessInformationIsNotEmpty() {
    return checkVatResponse -> checkVatResponse.getName() != null || checkVatResponse.getAddress() != null;
  }
}
