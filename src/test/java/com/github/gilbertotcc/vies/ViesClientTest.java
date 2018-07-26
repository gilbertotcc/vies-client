package com.github.gilbertotcc.vies;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ViesClientTest {

    @Test
    public void createViesClientShouldSuccess() {
        assertNotNull(ViesClient.create());
    }
}
