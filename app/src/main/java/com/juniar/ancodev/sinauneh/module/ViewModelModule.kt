package com.juniar.ancodev.sinauneh.module

import com.juniar.ancodev.sinauneh.feature.post.PostViewModel
import com.juniar.ancodev.sinauneh.feature.profile.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelModule {
    fun getModules(): Module {
        return module {
            viewModel { PostViewModel(get()) }
            viewModel { ProfileViewModel(get()) }
        }
    }
}