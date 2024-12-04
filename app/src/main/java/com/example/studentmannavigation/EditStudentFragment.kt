package com.example.studentmannavigation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var mssvEditText: EditText
    private lateinit var saveButton: Button
    private var pos: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

        nameEditText = view.findViewById(R.id.edit_name)
        mssvEditText = view.findViewById(R.id.edit_id)
        saveButton = view.findViewById(R.id.button_edit_ok)

        // Retrieve the student's data from the arguments
        arguments?.let {
            pos = it.getInt("pos")
            nameEditText.setText(it.getString("student_name"))
            mssvEditText.setText(it.getString("student_mssv"))
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val mssv = mssvEditText.text.toString()
            val data = Intent().apply {
                putExtra("pos", pos)
                putExtra("student_name", name)
                putExtra("student_mssv", mssv)
            }
            requireActivity().setResult(AppCompatActivity.RESULT_OK, data)
            requireActivity().finish()
        }

        return view
    }
}