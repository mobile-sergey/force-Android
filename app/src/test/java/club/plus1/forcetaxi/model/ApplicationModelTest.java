package club.plus1.forcetaxi.model;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicationModelTest {

    private ApplicationModel model;

    @Before
    public void createObject() {
        model = new ApplicationModel();
    }

    @Test
    public void onCreate() {
        model.onCreate();
        assertNotNull(model);
    }

    @Test
    public void getAppContext() {
        Context context = ApplicationModel.getAppContext();
        assertEquals(context, ApplicationModel.getAppContext());
    }

    @After
    public void destroyObject() {
        model = null;
    }

}