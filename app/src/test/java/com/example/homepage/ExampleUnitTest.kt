package com.example.homepage.utils

import com.example.homepage.models.BusinessModel
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun `test isBusinessNameValid with non-empty name`() {
        // Arrange
        val validator = Validator("1", "John Doe", "ABC Inc.", "123456", "123 Main St")

        // Act
        val result = validator.isBusinessNameValid()

        // Assert
        assertTrue(result)
    }

    @Test
    fun `test isBusinessNameValid with empty name`() {
        // Arrange
        val validator = Validator("1", "John Doe", "", "123456", "123 Main St")

        // Act
        val result = validator.isBusinessNameValid()

        // Assert
        assertFalse(result)
    }

    @Test
    fun `test isOwnerNameValid with non-empty name`() {
        // Arrange
        val validator = Validator("1", "John Doe", "ABC Inc.", "123456", "123 Main St")

        // Act
        val result = validator.isOwnerNameValid()

        // Assert
        assertTrue(result)
    }

    @Test
    fun `test isOwnerNameValid with empty name`() {
        // Arrange
        val validator = Validator("1", "", "ABC Inc.", "123456", "123 Main St")

        // Act
        val result = validator.isOwnerNameValid()

        // Assert
        assertFalse(result)
    }

    @Test
    fun `test isPostAddressValid with non-empty address`() {
        // Arrange
        val validator = Validator("1", "John Doe", "ABC Inc.", "123456", "123 Main St")

        // Act
        val result = validator.isPostAddressValid()

        // Assert
        assertTrue(result)
    }

    @Test
    fun `test isPostAddressValid with empty address`() {
        // Arrange
        val validator = Validator("1", "John Doe", "ABC Inc.", "123456", "")

        // Act
        val result = validator.isPostAddressValid()

        // Assert
        assertFalse(result)
    }

    @Test
    fun `test isRegNumberValid with non-empty registration number`() {
        // Arrange
        val validator = Validator("1", "John Doe", "ABC Inc.", "123456", "123 Main St")

        // Act
        val result = validator.isRegNumberValid()

        // Assert
        assertTrue(result)
    }

    @Test
    fun `test isRegNumberValid with empty registration number`() {
        // Arrange
        val validator = Validator("1", "John Doe", "ABC Inc.", "", "123 Main St")

        // Act
        val result = validator.isRegNumberValid()

        // Assert
        assertFalse(result)
    }

    @Test
    fun `test isInputValid with valid input fields`() {
        // Arrange
        val validator = Validator("1", "John Doe", "ABC Inc.", "123456", "123 Main St")

        // Act
        val result = validator.isInputValid()

        // Assert
        assertTrue(result)
    }

    @Test
    fun `test isInputValid with invalid input fields`() {
        // Arrange
        val validator = Validator("1", "", "", "", "")

        // Act
        val result = validator.isInputValid()

        // Assert
        assertFalse(result)
    }

}