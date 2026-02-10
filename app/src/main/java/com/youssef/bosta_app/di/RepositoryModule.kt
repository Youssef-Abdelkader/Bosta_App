package com.youssef.bosta_app.di

import com.youssef.bosta_app.data.repository.GamesRepositoryImpl
import com.youssef.bosta_app.domain.repository.GamesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGamesRepository(
        repositoryImpl: GamesRepositoryImpl
    ): GamesRepository {
        return repositoryImpl
    }
}
