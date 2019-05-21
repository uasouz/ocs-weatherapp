package br.hadara.weatherapp.di

import br.hadara.weatherapp.ui.main.MainActivity
import br.hadara.weatherapp.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity (): MainActivity


    @ContributesAndroidInjector
    abstract fun bindSplashActivity (): SplashActivity
}