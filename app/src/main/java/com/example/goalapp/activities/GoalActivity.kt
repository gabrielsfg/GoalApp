package com.example.goalapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goalapp.databinding.ActivityGoalBinding

class GoalActivity : AppCompatActivity() {
    private var binding: ActivityGoalBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.newGoalBtn?.setOnClickListener {
            val intent = Intent(this, AddGoalActivity::class.java)
            startActivity(intent)
        }

    }
}