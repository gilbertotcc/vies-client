package com.github.gilbertotcc.vies.model;

import com.github.gilbertotcc.vies.service.CheckVatResponse;

public class VatInformation {

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

    private VatInformation(final boolean valid, final BusinessInformation businessInformation) {
        this.valid = valid;
        this.businessInformation = businessInformation;
    }

    public VatInformation of(CheckVatResponse checkVatResponse) {
        // TODO
        return null;
    }

    public boolean isValid() {
        return valid;
    }

    public BusinessInformation getBusinessInformation() {
        return businessInformation;
    }
}
