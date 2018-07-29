package com.github.gilbertotcc.vies;

import com.github.gilbertotcc.vies.model.VatNumberInformation;
import com.github.gilbertotcc.vies.model.VatNumber;

public interface ViesClient {

    VatNumberInformation checkVatNumber(final VatNumber vatNumber) throws ViesServiceException;

    static ViesClient create() {
        return new ViesClientImpl();
    }
}
