package si.razum.catskmm.repository

import io.ktor.client.engine.android.Android
import org.koin.dsl.module

actual fun platformModule() = module {
    single { Android.create() }
}