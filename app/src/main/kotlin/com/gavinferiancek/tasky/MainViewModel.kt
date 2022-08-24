package com.gavinferiancek.tasky

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gavinferiancek.tasky.auth.domain.repository.AuthRepository
import com.gavinferiancek.tasky.core.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: AuthRepository,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _startDestination = MutableStateFlow(Screens.Login.route)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            _isLoading.value = true
            repository.authenticateToken()
                .onSuccess { _startDestination.value = Screens.Agenda.route }
            _isLoading.value = false
        }
    }
}