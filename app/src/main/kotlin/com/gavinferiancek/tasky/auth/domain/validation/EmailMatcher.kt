package com.gavinferiancek.tasky.auth.domain.validation

interface EmailMatcher {
    fun matches(email: String): Boolean
}