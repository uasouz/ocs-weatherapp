package br.hadara.weatherapp.di

import android.content.Context
import br.hadara.weatherapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule
{
    @Provides
    @Singleton
    fun provideApplication(app : App): Context = app
}