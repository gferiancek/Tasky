package com.gavinferiancek.tasky.auth.domain.validation

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class TextValidationManagerTest {

    private lateinit var validationManager: TextValidationManager

    private val emailMatcher = object : EmailMatcher {
        override fun matches(email: String): Boolean {
            return true
        }
    }

    @Before
    fun setUp() {
        validationManager = TextValidationManager(emailMatcher)
    }

    @Test
    fun `Name length less than 2 fails to validate`() {
        val name = "G"

        val actual = validationManager.validateName(name)

        assertThat(actual.all { it.isValid }).isFalse()
    }

    @Test
    fun `Name length greater than 50 fails to validate`() {
        val name = "Insert super long name that contains more than 50 characters"

        val actual = validationManager.validateName(name)

        assertThat(actual.all { it.isValid }).isFalse()
    }

    @Test
    fun `Name length between 2 and 50 validates successfully`() {
        val name = "Ga"

        val actual = validationManager.validateName(name)

        assertThat(actual.all { it.isValid }).isTrue()
    }

    @Test
    fun `Password less than 9 characters fails to validate`() {
        val password = "Aa345678"

        val actual = validationManager.validatePassword(password)

        assertThat(actual.all { it.isValid }).isFalse()
    }

    @Test
    fun `Password without number fails to validate`() {
        val password = "AaBbCcDdEe"

        val actual = validationManager.validatePassword(password)

        assertThat(actual.all { it.isValid }).isFalse()
    }

    @Test
    fun `Password without lowercase fails to validate`() {
        val password = "A23456789"

        val actual = validationManager.validatePassword(password)

        assertThat(actual.all { it.isValid }).isFalse()
    }

    @Test
    fun `Password without uppercase fails to validate`() {
        val password = "a3456789"

        val actual = validationManager.validatePassword(password)

        assertThat(actual.all { it.isValid }).isFalse()
    }

    @Test
    fun `Properly formatted password validates successfully`() {
        val password = "Aa3456789"

        val actual = validationManager.validatePassword(password)

        assertThat(actual.all { it.isValid }).isTrue()
    }
}