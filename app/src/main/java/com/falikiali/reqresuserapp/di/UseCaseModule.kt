package com.falikiali.reqresuserapp.di

import com.falikiali.reqresuserapp.domain.usecase.AuthUseCase
import com.falikiali.reqresuserapp.domain.usecase.ImplAuthUseCase
import com.falikiali.reqresuserapp.domain.usecase.ImplUserUseCase
import com.falikiali.reqresuserapp.domain.usecase.UserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun provideAuthUseCase(implAuthUseCase: ImplAuthUseCase): AuthUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideUserUseCase(implUserUseCase: ImplUserUseCase): UserUseCase

}