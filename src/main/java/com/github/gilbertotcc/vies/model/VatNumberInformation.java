package com.github.gilbertotcc.vies.model;

import java.util.Optional;

import javax.xml.bind.JAXBElement;

import com.github.gilbertotcc.vies.service.CheckVatResponse;

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

    private boolean valid;
    private BusinessInformation businessInformation;

    private VatNumberInformation(final boolean valid, final BusinessInformation businessInformation) {
        this.valid = valid;
        this.businessInformation = businessInformation;
    }

    public static VatNumberInformation of(CheckVatResponse checkVatResponse) {
        if (!checkVatResponse.isValid()) return new VatNumberInformation(false, null);

        final String businessName = Optional.ofNullable(checkVatResponse.getName()).map(JAXBElement::getValue).orElse(null);
        final String businessAddress = Optional.ofNullable(checkVatResponse.getAddress()).map(JAXBElement::getValue).orElse(null);
        final BusinessInformation businessInformation = businessName != null || businessAddress != null ?
                new BusinessInformation(businessName, businessAddress) : null;

        return new VatNumberInformation(checkVatResponse.isValid(), businessInformation);
    }

    public boolean isValid() {
        return valid;
    }

    public BusinessInformation getBusinessInformation() {
        return businessInformation;
    }
}
