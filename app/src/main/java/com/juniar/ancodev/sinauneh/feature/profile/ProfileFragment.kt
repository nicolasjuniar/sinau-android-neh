package com.juniar.ancodev.sinauneh.feature.profile

import android.os.Bundle
import android.view.View
import com.juniar.ancodev.sinauneh.R
import id.nyoman.core.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.tv_email
import kotlinx.android.synthetic.main.fragment_profile.tv_name
import kotlinx.android.synthetic.main.fragment_profile.tv_phone
import kotlinx.android.synthetic.main.fragment_profile.tv_username
import kotlinx.android.synthetic.main.fragment_profile.tv_website
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.getUsers(1)
        profileViewModel.liveDataUser.onChangeValue {
            tv_name.text = it.name
            tv_email.text = it.email
            tv_phone.text = it.phone
            tv_username.text = it.username
            tv_website.text = it.website
        }
    }
}