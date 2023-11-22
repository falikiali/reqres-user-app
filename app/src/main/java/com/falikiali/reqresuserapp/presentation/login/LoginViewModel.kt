package com.falikiali.reqresuserapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falikiali.reqresuserapp.data.remote.dto.LoginRequest
import com.falikiali.reqresuserapp.domain.model.Authentication
import com.falikiali.reqresuserapp.domain.usecase.AuthUseCase
import com.falikiali.reqresuserapp.helper.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel() {

    private val _loginState = MutableLiveData<ResultState<Authentication>>()
    val loginState: LiveData<ResultState<Authentication>> get() = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(
                LoginRequest(email, password)
            ).collect {
                _loginState.postValue(it)
            }
        }
    }

}