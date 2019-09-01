package club.plus1.forcetaxi.model;

import org.junit.Test;

import club.plus1.forcetaxi.stub.ResponseAppsStub;

import static org.junit.Assert.assertNull;

public class ResponseAppsTest {

    @Test
    public void ResponseApps() {
        ResponseApps app = new ResponseAppsStub();
        assertNull(app.error);
    }
}