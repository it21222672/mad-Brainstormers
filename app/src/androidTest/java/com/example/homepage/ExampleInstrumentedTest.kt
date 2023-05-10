package com.example.homepage.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidatorInstrumentedTest {

    @Test
    fun testIsBusinessValid() {
        // Arrange
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val validator = Validator("1", "John Doe", "ABC Inc.", "123456", "123 Main St")
        val businessList = mutableListOf(validator)

        // Act
        val result = validator.isBusinessValid(businessList)

        // Assert
        assertTrue(result)
    }

    @Test
    fun testIsRegNumberUnique() {
        // Arrange
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val validator1 = Validator("1", "John Doe", "ABC Inc.", "123456", "123 Main St")
        val validator2 = Validator("2", "Jane Smith", "XYZ Corp.", "789012", "456 Main St")
        val businessList = mutableListOf(validator1, validator2)

        // Act
        val result1 = validator1.isRegNumberUnique(businessList)
        val result2 = validator2.isRegNumberUnique(businessList)

        // Assert
        assertTrue(result1)
        assertTrue(result2)
    }

}
