package com.falikiali.reqresuserapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.falikiali.reqresuserapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userUseCase: UserUseCase): ViewModel() {

    val users = userUseCase.getUsers().cachedIn(viewModelScope)

}