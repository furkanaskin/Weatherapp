package com.faskn.app.weatherapp

import android.os.Build
import org.junit.Assert.assertEquals
import org.junit.Test
import org.robolectric.annotation.Config

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }
}
