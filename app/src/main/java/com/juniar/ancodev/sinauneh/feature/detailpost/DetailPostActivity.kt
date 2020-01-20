package com.juniar.ancodev.sinauneh.feature.detailpost

import android.os.Bundle
import com.juniar.ancodev.sinauneh.R
import com.juniar.ancodev.sinauneh.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_post.tv_body
import kotlinx.android.synthetic.main.activity_detail_post.tv_title

class DetailPostActivity : BaseActivity(R.layout.activity_detail_post) {

    override fun onViewReady(savedInstanceState: Bundle?) {
        tv_title.text = intent.getStringExtra("title")
        tv_body.text = intent.getStringExtra("body")
    }
}