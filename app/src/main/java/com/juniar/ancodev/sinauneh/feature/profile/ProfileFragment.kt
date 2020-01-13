package com.juniar.ancodev.sinauneh.feature.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.juniar.ancodev.sinauneh.R
import com.juniar.ancodev.sinauneh.utils.onChangeValue
import kotlinx.android.synthetic.main.fragment_profile.tv_email
import kotlinx.android.synthetic.main.fragment_profile.tv_name
import kotlinx.android.synthetic.main.fragment_profile.tv_phone
import kotlinx.android.synthetic.main.fragment_profile.tv_username
import kotlinx.android.synthetic.main.fragment_profile.tv_website
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.getUsers(1)
        profileViewModel.liveDataUser.onChangeValue(this) {
            tv_name.text = it.name
            tv_email.text = it.email
            tv_phone.text = it.phone
            tv_username.text = it.username
            tv_website.text = it.website
        }
    }
}