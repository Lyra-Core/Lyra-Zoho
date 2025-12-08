package com.lyracore.zoho.department

import android.app.Application
import android.content.Context
import android.os.Looper
import androidx.test.core.app.ApplicationProvider
import com.lyracore.zoho.LyraZoho
import com.lyracore.zoho.core.CoreInitializer
import com.lyracore.zoho.core.interfaces.ExceptionHandlingCallback
import com.lyracore.zoho.core.models.ZohoConfig
import kotlin.collections.count
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class DepartmentClientTest {
    private lateinit var context: Context
    private lateinit var application: Application
    private lateinit var exceptionHandlingCallback: ExceptionHandlingCallback

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Context>()
        application = ApplicationProvider.getApplicationContext()
        exceptionHandlingCallback = Mockito.mock(ExceptionHandlingCallback::class.java)
    }

    @After
    fun tearDown() {
        // Reset CoreInitializer state after each test to ensure test isolation
        CoreInitializer.reset()
        shadowOf(Looper.getMainLooper()).runToEndOfTasks()
    }

    @Test
    fun getAllDepartments_Success_ReturnsData() {
        val zohoConfig: ZohoConfig = ZohoConfig("test", "test", exceptionHandlingCallback)

        LyraZoho.initialize(application,  zohoConfig)

        val result = DepartmentClient.getAllDepartments()

        assertEquals(16, result.count())
    }

    @Test
    fun getAllDepartments_Failure_SDKNotInitialized() {
        val result = DepartmentClient.getAllDepartments()

        assertEquals(0, result.count())
    }

    @Test
    fun getDefaultDepartment_Success_ReturnsData() {
        val zohoConfig: ZohoConfig = ZohoConfig("test", "test", exceptionHandlingCallback)

        LyraZoho.initialize(application, zohoConfig)

        val result = DepartmentClient.getDefaultDepartment()

        assertNotNull(result)
    }

    @Test
    fun getDefaultDepartment_Failure_SDKNotInitialized() {
        val result = DepartmentClient.getDefaultDepartment()

        assertNull(result)
    }

    @Test
    fun getDepartmentByCountry_Success_ReturnsData() {
        val zohoConfig: ZohoConfig = ZohoConfig("test", "test", exceptionHandlingCallback)

        LyraZoho.initialize(application, zohoConfig)

        val result = DepartmentClient.getDepartmentByCountry("za")

        assertNotNull(result)
    }

    @Test
    fun getDepartmentByCountry_Failure_NoDataReturned() {

        val result = DepartmentClient.getDepartmentByCountry("za")

        assertNull(result)
    }
}
