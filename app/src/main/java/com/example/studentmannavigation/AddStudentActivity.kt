package com.example.studentmannavigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

//        val editHoten = findViewById<EditText>(R.id.add_hoten)
//        val editMssv = findViewById<EditText>(R.id.add_mssv)
//
//        findViewById<Button>(R.id.button_add_ok).setOnClickListener {
//            val hoten = editHoten.text.toString()
//            val mssv = editMssv.text.toString()
//
//            val resultIntent = Intent() // Khởi tạo một Intent mới thay vì dùng intent mặc định
//            resultIntent.putExtra("hoten", hoten)
//            resultIntent.putExtra("mssv", mssv)
//
//            setResult(Activity.RESULT_OK, resultIntent)
//            finish()
//        }
//
//        findViewById<Button>(R.id.button_add_cancel).setOnClickListener {
//            setResult(Activity.RESULT_CANCELED)
//            finish()
//        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AddStudentFragment())
                .commit()
        }
    }
}
