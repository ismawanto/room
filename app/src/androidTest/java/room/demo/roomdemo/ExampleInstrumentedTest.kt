package room.demo.roomdemo

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Pada bagian ini terjadi pengujian instrumen, yang akan dijalankan.
 *
 * 
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Pada bagian ini adalah konteks aplikasi yang akan diuji
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.demo.roomdemo", appContext.packageName)
    }
}
