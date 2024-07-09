package com.hirotomurayama.project.calculator.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hirotomurayama.project.calculator.R

class TopMenuActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.top_menu)

            // 開始するボタンが押下されたら計算画面に遷移
            findViewById<Button>(R.id.button).setOnClickListener(startButtonClickListener())
        }

        /**
         * 開始するボタンが押下された時、計算画面に遷移させるリスナー
         */
        private fun startButtonClickListener() : View.OnClickListener {
            return View.OnClickListener {
                val intent = Intent(this, CalculateActivity::class.java)
                startActivity(intent)
            }
        }
}
