package com.github.gilbertotcc.vies;

import com.github.gilbertotcc.vies.model.VatNumberInformation;
import com.github.gilbertotcc.vies.model.VatNumber;
import com.github.gilbertotcc.vies.service.CheckVat;
import com.github.gilbertotcc.vies.service.CheckVatResponse;
import com.github.gilbertotcc.vies.service.CheckVatService;

public class ViesClientImpl implements ViesClient {

    private final CheckVatService checkVatService;

    ViesClientImpl(final CheckVatService checkVatService) {
        this.checkVatService = checkVatService;
    }

    ViesClientImpl() {
        this(new CheckVatService());
    }

    @Override
    public VatNumberInformation checkVatNumber(final VatNumber vatNumber) {
        final CheckVat checkVatRequest = vatNumber.asCheckVat();
        final CheckVatResponse checkVatResponse = checkVatService.getCheckVatPort().checkVat(checkVatRequest);
        return VatNumberInformation.of(checkVatResponse);
    }
}
