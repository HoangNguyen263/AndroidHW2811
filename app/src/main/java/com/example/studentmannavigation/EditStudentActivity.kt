package com.example.studentmannavigation

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {
    private lateinit var editName: EditText
    private lateinit var editId: EditText
    private var pos: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        editName = findViewById(R.id.edit_name)
        editId = findViewById(R.id.edit_id)

        // Retrieve the student's data from the intent
        pos = intent.getIntExtra("pos", -1)
        editName.setText(intent.getStringExtra("student_name"))
        editId.setText(intent.getStringExtra("student_id"))

        findViewById<Button>(R.id.button_edit_ok).setOnClickListener {
            val name = editName.text.toString()
            val id = editId.text.toString()
            val data = intent.apply {
                putExtra("pos", pos)
                putExtra("student_name", name)
                putExtra("student_id", id)
            }
            setResult(Activity.RESULT_OK, data)
            finish()
        }

        findViewById<Button>(R.id.button_edit_cancel).setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}