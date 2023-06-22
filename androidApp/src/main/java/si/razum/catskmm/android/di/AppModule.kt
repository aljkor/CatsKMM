package si.razum.catskmm.android.di


import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import si.razum.catskmm.android.ui.home.HomeScreenViewModel
import si.razum.catskmm.android.ui.details.DetailsScreenViewModel

val appModule = module {
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::DetailsScreenViewModel)
}