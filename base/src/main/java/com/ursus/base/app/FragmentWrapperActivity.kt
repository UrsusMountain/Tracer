package com.ursus.base.app

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment

class FragmentWrapperActivity : URBaseActivity() {

    companion object {
        fun intent(fragment: Class<out Fragment>): Intent {
            val intent = Intent()
            intent.setClassName(URApplication.INSTANCE.packageName, FragmentWrapperActivity::class.java.name)
            intent.putExtra("fragment", fragment.name)
            return intent
        }
    }

    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val f: Fragment?
        if (savedInstanceState == null) {
            f = createRootFragment()
            if (f == null) {
                finish()
                return
            }
            fragment = f
            supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, fragment, "fragment")
                    .commit()
        } else {
            fragment = supportFragmentManager.findFragmentByTag("fragment")!!
        }
    }

    private fun createRootFragment(): Fragment? {
        return try {
            val clazzName = getStringParam("fragment")
            if (!TextUtils.isEmpty(clazzName)) {
                classLoader.loadClass(clazzName).newInstance() as Fragment?
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}