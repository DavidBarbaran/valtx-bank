package com.valtx.bank.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valtx.bank.domain.product.GetProductsUseCase
import com.valtx.bank.domain.product.Product
import com.valtx.bank.domain.result.Result
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
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<HomeEvent>()
    val events: SharedFlow<HomeEvent> = _events.asSharedFlow()

    fun getProducts() = viewModelScope.launch {

        _uiState.update { uiState ->
            uiState.copy(isLoading = true)
        }

        val result = getProductsUseCase.invoke()

        when (result) {
            is Result.Error -> _events.emit(HomeEvent.ShowError(result.message))
            is Result.Success<List<Product>> -> {
                _uiState.update { uiState ->
                    uiState.copy(products = result.data.map { it.toUi() })
                }
            }
        }

        _uiState.update { uiState ->
            uiState.copy(isLoading = false)
        }
    }

    fun refreshProducts() = viewModelScope.launch {

        _uiState.update { uiState ->
            uiState.copy(isRefreshing = true)
        }

        val result = getProductsUseCase.invoke()

        when (result) {
            is Result.Error -> _events.emit(HomeEvent.ShowError(result.message))
            is Result.Success<List<Product>> -> {
                _uiState.update { uiState ->
                    uiState.copy(products = result.data.map { it.toUi() })
                }
            }
        }

        _uiState.update { uiState ->
            uiState.copy(isRefreshing = false)
        }
    }
}