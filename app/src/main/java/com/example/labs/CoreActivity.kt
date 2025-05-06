package com.example.labs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class CoreActivity : AppCompatActivity() {

    lateinit var fragmentActivity: ActivityFragment
    lateinit var fragmentProfile: ProfileFragment
    lateinit var fragmentPasswordChange: PasswordChangeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_core)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fragmentActivity = ActivityFragment.newInstance("", "")
        fragmentProfile = ProfileFragment.newInstance("", "")
        fragmentPasswordChange = PasswordChangeFragment.newInstance("", "")

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainerView, fragmentActivity, "activity")
            add(R.id.fragmentContainerView, fragmentProfile, "profile")
            add(R.id.fragmentContainerView, fragmentPasswordChange, "passwordChange")
            hide(fragmentProfile)
            hide(fragmentPasswordChange)
            commit()
        }

        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_1 -> {
                    supportFragmentManager.beginTransaction().apply {
                        show(fragmentActivity)
                        hide(fragmentProfile)
                        hide(fragmentPasswordChange)
                        commit()
                    }
                    true
                }

                R.id.item_2 -> {
                    supportFragmentManager.beginTransaction().apply {
                        show(fragmentProfile)
                        hide(fragmentActivity)
                        hide(fragmentPasswordChange)
                        commit()
                    }
                    true
                }
                else -> false
            }
        }
    }

    fun showPasswordChangeFragment() {
        supportFragmentManager.beginTransaction().apply {
            hide(fragmentActivity)
            hide(fragmentProfile)
            show(fragmentPasswordChange)
            addToBackStack(null)
            commit()
        }
    }
}