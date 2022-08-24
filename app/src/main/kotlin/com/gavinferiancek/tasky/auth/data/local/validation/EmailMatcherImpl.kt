package com.gavinferiancek.tasky.auth.data.local.validation

import android.util.Patterns
import com.gavinferiancek.tasky.auth.domain.validation.EmailMatcher

class EmailMatcherImpl : EmailMatcher {
    override fun matches(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}