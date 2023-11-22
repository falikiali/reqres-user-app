package com.falikiali.reqresuserapp.di

import com.falikiali.reqresuserapp.data.ImplAuthRepository
import com.falikiali.reqresuserapp.data.ImplUserRepository
import com.falikiali.reqresuserapp.domain.repository.AuthRepository
import com.falikiali.reqresuserapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthRepository(implAuthRepository: ImplAuthRepository): AuthRepository

    @Binds
    abstract fun provideUserRepository(implUserRepository: ImplUserRepository): UserRepository

}