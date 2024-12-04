package com.example.studentmannavigation

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<StudentModel>
    private val students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002")
        // Add other students here
    )

    private val addStudentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val hoten = result.data?.getStringExtra("student_name") // Change "hoten" to "student_name" to match the data key used in AddStudentFragment
            val mssv = result.data?.getStringExtra("student_mssv") // Change "mssv" to "student_mssv" to match the data key used in AddStudentFragment
            if (!hoten.isNullOrEmpty() && !mssv.isNullOrEmpty()) {
                students.add(StudentModel(hoten, mssv))
                adapter.notifyDataSetChanged() // Cập nhật giao diện ListView
            }
        }
    }

    private val editStudentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val pos = result.data?.getIntExtra("pos", -1) ?: -1
            val studentName = result.data?.getStringExtra("student_name")
            val studentId = result.data?.getStringExtra("student_id")

            if (pos != -1 && !studentName.isNullOrEmpty() && !studentId.isNullOrEmpty()) {
                students[pos] = StudentModel(studentName, studentId)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up Toolbar as Action Bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set up ListView and Adapter
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, students)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
        registerForContextMenu(listView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_student -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                addStudentLauncher.launch(intent)
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Settings selected", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val pos = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        when (item.itemId) {
            R.id.action_edit_student -> {
                val intent = Intent(this, EditStudentActivity::class.java).apply {
                    putExtra("pos", pos)
                    putExtra("student_name", students[pos].studentName)
                    putExtra("student_id", students[pos].studentId)
                }
                editStudentLauncher.launch(intent)
            }
            R.id.action_delete -> {
                students.removeAt(pos)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Removed student", Toast.LENGTH_LONG).show()
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("students", ArrayList(students))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedStudents = savedInstanceState.getParcelableArrayList<StudentModel>("students")
        if (savedStudents != null) {
            students.clear()
            students.addAll(savedStudents)
            adapter.notifyDataSetChanged()
        }
    }
}