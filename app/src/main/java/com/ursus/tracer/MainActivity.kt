package com.ursus.tracer

import android.os.Bundle
import android.widget.Button
import com.ursus.base.app.FragmentWrapperActivity
import com.ursus.base.app.URBaseActivity
import com.ursus.base.developer.DeveloperFragment
import com.ursus.base.utils.bind

class MainActivity : URBaseActivity() {

    private val btnDeveloper by bind<Button>(R.id.btn_developer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDeveloper.setOnClickListener {
            startActivity(FragmentWrapperActivity.intent(DeveloperFragment::class.java))
        }
    }
}
