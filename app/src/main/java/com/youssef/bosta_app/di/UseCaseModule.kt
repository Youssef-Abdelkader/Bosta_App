package com.youssef.bosta_app.di

import com.youssef.bosta_app.domain.repository.GamesRepository
import com.youssef.bosta_app.domain.usecase.GetGameDetailsUseCase
import com.youssef.bosta_app.domain.usecase.GetGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetGamesUseCase(
        repository: GamesRepository
    ): GetGamesUseCase {
        return GetGamesUseCase(repository)
    }

    @Provides
    fun provideGetGameDetailsUseCase(
        repository: GamesRepository
    ): GetGameDetailsUseCase {
        return GetGameDetailsUseCase(repository)
    }
}
