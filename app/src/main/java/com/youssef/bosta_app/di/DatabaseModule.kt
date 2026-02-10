package com.youssef.bosta_app.di

import android.content.Context
import androidx.room.Room
import com.youssef.bosta_app.data.local.AppDatabase
import com.youssef.bosta_app.data.local.GamesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "games_db"
        ).build()
    }

    @Provides
    fun provideGamesDao(database: AppDatabase): GamesDao {
        return database.gamesDao()
    }
}