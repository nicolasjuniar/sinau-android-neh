package com.juniar.ancodev.sinauneh.module

import com.juniar.ancodev.sinauneh.feature.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelModule {
    fun getModules(): Module {
        return module {
            viewModel { HomeViewModel(get()) }
        }
    }
}