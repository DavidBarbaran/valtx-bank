package com.valtx.bank.presentation.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valtx.bank.R
import com.valtx.bank.domain.login.LoginResult
import com.valtx.bank.domain.login.LoginUseCase
import com.valtx.bank.presentation.providers.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val resourcesProvider: ResourcesProvider,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<LoginEvent>()
    val events: SharedFlow<LoginEvent> = _events.asSharedFlow()

    fun login(document: String, password: String) = viewModelScope.launch {

        when {
            document.isBlank() || password.isBlank() -> {
                _events.emit(
                    LoginEvent.ShowError(
                        resourcesProvider.getString(R.string.login_error_validate_empty_fields)
                    )
                )
                return@launch
            }

            document.isInvalidDocument() -> {
                _events.emit(
                    LoginEvent.ShowError(
                        resourcesProvider.getString(R.string.login_error_document)
                    )
                )
                return@launch
            }

            password.isInvalidPassword() -> {
                _events.emit(
                    LoginEvent.ShowError(
                        resourcesProvider.getString(R.string.login_error_password)
                    )
                )
                return@launch
            }
        }

        _uiState.update { uiState ->
            uiState.copy(isLoading = true)
        }

        val result = loginUseCase.invoke(document, password)

        when (result) {
            is LoginResult.Error -> {
                _events.emit(
                    LoginEvent.ShowError(
                        result.message
                    )
                )
            }

            LoginResult.Success -> {
                _events.emit(
                    LoginEvent.LoginSuccess
                )
            }
        }

        _uiState.update { uiState ->
            uiState.copy(isLoading = false)
        }
    }

    private fun String.isInvalidDocument(): Boolean = length < 8

    private fun String.isInvalidPassword(): Boolean = length < 10
}