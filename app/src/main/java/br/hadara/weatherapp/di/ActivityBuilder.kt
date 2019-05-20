package br.hadara.weatherapp.di

import br.hadara.weatherapp.ui.MainActivity
import br.hadara.weatherapp.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity (): MainActivity


    @ContributesAndroidInjector
    abstract fun bindSplashActivity (): SplashActivity
}