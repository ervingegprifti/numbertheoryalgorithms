package com.gegprifti.android.numbertheoryalgorithms;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4; // New Runner
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 * Uses modern AndroidX Test libraries.
 */
@RunWith(AndroidJUnit4.class) // Use the AndroidX JUnit4 runner
public class ApplicationTest { // Renamed for clarity, often "ExampleInstrumentedTest"

    private Context appContext;

    @Before
    public void setup() {
        // Get the application context using ApplicationProvider
        appContext = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void useAppContext() {
        // Verify the package name or other application-level properties
        assertEquals("com.gegprifti.android.numbertheoryalgorithms", appContext.getPackageName());
        // You can use 'appContext' to interact with app-level components, like SharedPreferences, database helpers, etc.
    }
}