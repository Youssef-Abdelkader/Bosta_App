package com.youssef.bosta_app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiKeyModule {

    @Provides
    @Singleton
    @Named("api_key")
    fun provideApiKey(): String {
        return "08cbbd7d6f9d464bb4bc99acd410c1f9"
    }
}
