package com.github.gilbertotcc.vies;

import com.github.gilbertotcc.vies.model.VatInformation;

public interface ViesClient {

    VatInformation checkVatNumber(final String countryCode, final String vatNumber);

    default ViesClient create() {
        return new ViesClientImpl();
    }
}
