package io.swagger.api.HoldersController;

import io.swagger.model.Holder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HoldersApiControllerTest {

    @Test
    public void createHolderShouldNotBeNull() {
        Holder holder = new Holder();
        assertNotNull(holder);
    }
}