package com.sukai.butterfly_simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goToRepositoryActivity.setOnClickListener {
            startRepositoryActivity(
                "Kotlin",
                "JetBrains",
                url = "https://www.sukaidev.top"
            )
        }
        goToUserActivity.setOnClickListener {
            UserActivityBuilder.start(this, 22, "sukaidev", "Kotliner", "打杂的", "广州")
        }
    }
}
