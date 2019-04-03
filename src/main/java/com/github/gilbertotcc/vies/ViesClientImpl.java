package com.github.gilbertotcc.vies;

import com.github.gilbertotcc.vies.model.VatNumberInformation;
import com.github.gilbertotcc.vies.model.VatNumber;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatService;
import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVat;
import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVatResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class ViesClientImpl implements ViesClient {

    private CheckVatService checkVatService;

    ViesClientImpl() {
        this(new CheckVatService());
    }

    @Override
    public VatNumberInformation checkVatNumber(final VatNumber vatNumber) throws ViesServiceException {
        final CheckVat checkVatRequest = vatNumber.asCheckVat();
        try {
            final CheckVatResponse checkVatResponse = checkVatService.getCheckVatPort().checkVat(checkVatRequest);
            return VatNumberInformation.of(checkVatResponse);
        } catch (Exception e) {
            throw new ViesServiceException(String.format("Error occurred while checking VAT number %s: %s", vatNumber, e.getMessage()), e);
        }
    }
}
