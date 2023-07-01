package com.example.goalapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import com.example.goalapp.databinding.ActivityAddGoalBinding
import com.example.goalapp.databinding.ActivityGoalBinding
import java.text.SimpleDateFormat
import java.util.*

class AddGoalActivity : AppCompatActivity(), View.OnClickListener {

    private var binding: ActivityAddGoalBinding? = null

    //Calendar Var
    private var cal = Calendar.getInstance()
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGoalBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarAddGoal)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarAddGoal?.setNavigationOnClickListener {
            onBackPressed()
        }

        dateSetListener =
            DatePickerDialog.OnDateSetListener {view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                updateDateInView()
            }
        binding?.etDeadLine?.setOnClickListener(this)

        updateDateInView()

        binding?.addImage?.setOnClickListener{
            pickImageGallery()
        }

        binding?.btnSave?.setOnClickListener{

        }

    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            binding?.imageView?.setImageURI(data?.data)
        }
    }

    private fun updateDateInView(){
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding?.etDeadLine?.setText(sdf.format(cal.time).toString())
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.et_deadLine ->{
                DatePickerDialog(
                    this@AddGoalActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    (Calendar.MONTH),
                    (Calendar.DAY_OF_MONTH)).show()
            }
        }
    }
}