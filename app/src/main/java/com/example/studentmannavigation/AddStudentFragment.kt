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
import androidx.navigation.fragment.findNavController

class AddStudentFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var mssvEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        nameEditText = view.findViewById(R.id.add_name)
        mssvEditText = view.findViewById(R.id.add_id)
        saveButton = view.findViewById(R.id.button_add_ok)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val mssv = mssvEditText.text.toString()
            val data = Intent().apply {
                putExtra("student_name", name)
                putExtra("student_mssv", mssv)
            }
            requireActivity().setResult(AppCompatActivity.RESULT_OK, data)
            requireActivity().finish()
        }

        return view
    }
}