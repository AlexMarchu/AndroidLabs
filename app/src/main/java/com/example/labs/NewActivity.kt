package com.example.labs

import ActivityTypeAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val activityTypes = listOf("Велосипед", "Бег", "Шаг")
        val adapter = ActivityTypeAdapter(activityTypes) // { selectedType ->
//            pass
//        }

        val recyclerView = findViewById<RecyclerView>(R.id.activityTypeRecyclerView)
        recyclerView.adapter = adapter

        findViewById<MaterialButton>(R.id.startButton).setOnClickListener {
            finish()
        }
    }
}