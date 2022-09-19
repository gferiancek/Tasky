package com.gavinferiancek.tasky

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gavinferiancek.tasky.auth.domain.repository.AuthRepository
import com.gavinferiancek.tasky.core.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: AuthRepository,
) : ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.authenticateToken()
                .onSuccess { state = state.copy(startDestination = Screens.AgendaList.route) }
            state = state.copy(isLoading = false)
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            repository.logoutUser()
        }
    }
}