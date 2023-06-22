package si.razum.catskmm.android

import android.app.Application
import si.razum.catskmm.android.di.appModule
import si.razum.catskmm.di.initKoin
import org.koin.android.ext.koin.androidContext

class CatsApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@CatsApplication)
            modules(appModule)
        }
    }
}