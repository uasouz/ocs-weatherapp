package br.hadara.weatherapp

import android.app.Activity
import android.app.Application
import android.content.Context
import br.hadara.weatherapp.di.CoreComponent
import br.hadara.weatherapp.di.DaggerCoreComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class App: Application(),HasActivityInjector{
    override fun activityInjector(): AndroidInjector<Activity> = androidInjector

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

//    companion object {
//        @JvmStatic
//        fun coreComponent(context: Context) =
//            (context.applicationContext as App).coreComponent
//    }

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder().application(this).build()
    }


    override fun onCreate() {
        super.onCreate()
        DaggerCoreComponent.builder().application(this).build().inject(this)
        Stetho.initializeWithDefaults(this)
    }

}

//fun Activity.coreComponent() = App.coreComponent(this)