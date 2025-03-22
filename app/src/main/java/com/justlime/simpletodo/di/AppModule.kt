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

/**
 * - Module: Marks this object as a Dagger module that provides dependencies.
 * - InstallIn: Specifies the component in which the module will be installed.
 * - Provides: Marks a method inside a Dagger module as a provider of dependencies. which we used with inject in constructor.
 * - Singleton: Ensures that only a single instance of the dependency is created and reused.
 * - ApplicationContext: Provides the application context for dependency injection.
 * @return: Returns the AppDatabase instance.*/
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