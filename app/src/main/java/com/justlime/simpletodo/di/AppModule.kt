package com.justlime.simpletodo.di

import android.content.Context
import androidx.room.Room
import com.justlime.simpletodo.data.dao.AppDatabase
import com.justlime.simpletodo.data.dao.TaskDao
import com.justlime.simpletodo.data.source.TaskLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "tasks-db"
        ).build()
    }

    @Provides
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(taskDao: TaskDao): TaskLocalDataSource {
        return TaskLocalDataSource(taskDao)
    }
}