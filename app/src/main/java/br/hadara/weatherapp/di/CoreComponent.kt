package br.hadara.weatherapp.di

import br.hadara.weatherapp.App
import br.hadara.weatherapp.data.WeatherService
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class,ActivityBuilder::class, AppModule::class, NetworkModule::class])
interface CoreComponent: AndroidInjector<App> {

    fun retrofit(): Retrofit

    fun apiService(): WeatherService

    fun okHTTPClient(): OkHttpClient

    fun gson(): Gson

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): CoreComponent
    }

    override fun inject(app: App)

}