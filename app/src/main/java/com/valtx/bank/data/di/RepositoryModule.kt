package com.valtx.bank.data.di

import com.valtx.bank.data.repository.LoginRepositoryImpl
import com.valtx.bank.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsLoginRepository(impl: LoginRepositoryImpl): LoginRepository
}