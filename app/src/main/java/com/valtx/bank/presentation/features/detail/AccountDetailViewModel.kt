package com.valtx.bank.presentation.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valtx.bank.domain.detail.AccountDetail
import com.valtx.bank.domain.result.Result
import com.valtx.bank.domain.detail.GetAccountDetailUseCase
import com.valtx.bank.presentation.mapper.toUi
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
class AccountDetailViewModel @Inject constructor(
    private val getAccountDetailUseCase: GetAccountDetailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountDetailUiState())
    val uiState: StateFlow<AccountDetailUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<AccountDetailEvent>()
    val events: SharedFlow<AccountDetailEvent> = _events.asSharedFlow()

    fun getAccountDetail() = viewModelScope.launch {
        _uiState.update { uiState ->
            uiState.copy(isLoading = true)
        }

        val result = getAccountDetailUseCase.invoke()

        when (result) {
            is Result.Error -> _events.emit(AccountDetailEvent.ShowError(result.message))
            is Result.Success<AccountDetail> -> {
                _uiState.update { uiState ->
                    uiState.copy(accountDetail = result.data.toUi())
                }
            }
        }

        _uiState.update { uiState ->
            uiState.copy(isLoading = false)
        }
    }
}