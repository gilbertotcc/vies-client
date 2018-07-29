package com.github.gilbertotcc.vies.model;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.xml.bind.JAXBElement;

import com.github.gilbertotcc.vies.service.CheckVatResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class VatNumberInformation {

    public static class BusinessInformation {

        private String name;
        private String address;

        private BusinessInformation(final String name, final String address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }
    }

    private static final VatNumberInformation INVALID_VAT_NUMBER_INFORMATION = new VatNumberInformation(false, null);

    private boolean valid;
    private BusinessInformation businessInformation;

    private VatNumberInformation(final boolean valid, final BusinessInformation businessInformation) {
        this.valid = valid;
        this.businessInformation = businessInformation;
    }

    public static VatNumberInformation of(CheckVatResponse checkVatResponse) {
        if (!checkVatResponse.isValid()) {
            return INVALID_VAT_NUMBER_INFORMATION;
        }

        final BusinessInformation businessInformation = Optional.of(checkVatResponse)
                .filter(businessInformationIsNotEmpty())
                .map(createBusinessInformation())
                .orElse(null);

        return new VatNumberInformation(checkVatResponse.isValid(), businessInformation);
    }

    public boolean isValid() {
        return valid;
    }

    public BusinessInformation getBusinessInformation() {
        return businessInformation;
    }

    private static Function<CheckVatResponse, BusinessInformation> createBusinessInformation() {
        return checkVatResponse -> {
            String businessName = contentOf(checkVatResponse::getName).orElse(null);
            String businessAddress = contentOf(checkVatResponse::getAddress)
                    .map(address -> address.replace("\n", " ").replaceAll("\\s{2,}", " ").trim())
                    .orElse(null);
            return new BusinessInformation(businessName, businessAddress);
        };
    }

    private static <T> Optional<T> contentOf(Supplier<JAXBElement<T>> jaxbElementSupplier) {
        return Optional.ofNullable(jaxbElementSupplier.get()).map(JAXBElement::getValue);
    }

    private static Predicate<CheckVatResponse> businessInformationIsNotEmpty() {
        return checkVatResponse -> checkVatResponse.getName() != null || checkVatResponse.getAddress() != null;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE, false);
    }
}
