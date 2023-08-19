package com.mustafaunlu.ecommerce_compose.di.repository

import com.mustafaunlu.ecommerce_compose.domain.repository.FirebaseRepository
import com.mustafaunlu.ecommerce_compose.domain.repository.LocalRepository
import com.mustafaunlu.ecommerce_compose.domain.repository.RemoteRepository
import com.mustafaunlu.ecommerce_compose.data.repository.FirebaseRepositoryImpl
import com.mustafaunlu.ecommerce_compose.data.repository.LocalRepositoryImpl
import com.mustafaunlu.ecommerce_compose.data.repository.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteRepository(
        repository: RemoteRepositoryImpl,
    ): RemoteRepository

    @Binds
    @ViewModelScoped
    abstract fun bindLocalRepository(
        repository: LocalRepositoryImpl,
    ): LocalRepository

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseRepository(
        repository: FirebaseRepositoryImpl,
    ): FirebaseRepository
}
