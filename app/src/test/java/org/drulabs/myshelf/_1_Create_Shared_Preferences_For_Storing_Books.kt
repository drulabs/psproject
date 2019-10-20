package org.drulabs.myshelf

import android.content.Context
import android.content.SharedPreferences
import org.drulabs.myshelf.data.DataProviderImpl
import org.drulabs.myshelf.utils.PREFS_NAME
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.exceptions.base.MockitoAssertionError

class Task01CreateSharedPreferencesForStoringBooks {

    private val context = mock(Context::class.java)
    private val sharedPreferences = mock(SharedPreferences::class.java)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE))
            .thenReturn(sharedPreferences)
    }

    @Test
    fun `test if correct shared preferences object gets created`() {

        val prefsNameCaptor = ArgumentCaptor.forClass(String::class.java)
        val prefsModeCaptor = ArgumentCaptor.forClass(Int::class.java)

        try {
            DataProviderImpl(context)

            verify(context).getSharedPreferences(
                prefsNameCaptor.capture(),
                prefsModeCaptor.capture()
            )
        } catch (e: IllegalStateException) {
            Assert.fail("Incorrect preferences name or MODE passed while creating shared preferences")
        } catch (e: MockitoAssertionError) {
            Assert.fail("SharedPreferences not created while initializing DataProviderImpl")
        }

        assertTrue(
            "Incorrect preferences name passed while creating shared preferences",
            prefsNameCaptor.value == PREFS_NAME
        )

        assertTrue(
            "Incorrect MODE passed while creating shared preferences",
            prefsModeCaptor.value == Context.MODE_PRIVATE
        )
    }
}
