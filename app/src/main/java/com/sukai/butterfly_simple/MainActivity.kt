package com.sukai.butterfly_simple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goToRepositoryActivity.setOnClickListener {
            startActivity(
                Intent(this, RepositoryActivity::class.java)
                    .putExtra(RepositoryActivityBuilder.REQUIRED_NAME, "Kotlin")
                    .putExtra(RepositoryActivityBuilder.REQUIRED_OWNER, "JetBrains")
                    .putExtra(RepositoryActivityBuilder.OPTIONAL_CREATE_AT, "1329125398000L")
                    .putExtra(RepositoryActivityBuilder.OPTIONAL_URL, "https://www.sukaidev.top")
            )
        }
        goToUserActivity.setOnClickListener{
            UserActivityBuilder.start(this,22,"sukaidev","Kotliner","打杂的","广州")
        }
    }
}
