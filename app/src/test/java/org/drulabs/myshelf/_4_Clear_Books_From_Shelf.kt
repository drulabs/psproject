package org.drulabs.myshelf

import android.content.Context
import android.content.SharedPreferences
import org.drulabs.myshelf.data.DataProvider
import org.drulabs.myshelf.data.DataProviderImpl
import org.drulabs.myshelf.utils.PREFS_NAME
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.exceptions.base.MockitoAssertionError

class Task04ClearBooksFromShelf {

    private val context = mock(Context::class.java)
    private val sharedPreferences = mock(SharedPreferences::class.java)
    private val prefsEditor = mock(SharedPreferences.Editor::class.java)
    private val updatedPrefsEditor = mock(SharedPreferences.Editor::class.java)

    private lateinit var dataProvider: DataProvider

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE))
            .thenReturn(sharedPreferences)
        Mockito.`when`(sharedPreferences.edit()).thenReturn(prefsEditor)
        Mockito.`when`(prefsEditor.clear()).thenReturn(updatedPrefsEditor)
        Mockito.doNothing().`when`(updatedPrefsEditor).apply()

        try {
            dataProvider = DataProviderImpl(context)
        } catch (e: IllegalStateException) {
            Assert.fail("Incorrect preferences name or MODE passed while creating shared preferences")
        }
    }

    @Test
    fun `test if books are deleted from preferences when resetShelf is invoked`() {

        dataProvider.resetShelf()

        try {
            verify(sharedPreferences, times(1)).edit()
        } catch (e: MockitoAssertionError) {
            throw MockitoAssertionError("SharedPreference.edit() method was not invoked")
        }

        try {
            verify(prefsEditor, atLeastOnce()).clear()
        } catch (e: MockitoAssertionError) {
            throw MockitoAssertionError("SharedPreference.clear(...) method was not invoked")
        }

        try {
            verify(updatedPrefsEditor, atLeastOnce()).apply()
        } catch (e: MockitoAssertionError) {
            throw MockitoAssertionError("SharedPreference.Editor.apply() method was not invoked")
        }
    }
}
