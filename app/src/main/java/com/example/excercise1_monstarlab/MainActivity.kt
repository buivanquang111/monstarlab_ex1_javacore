package com.example.excercise1_monstarlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_student.*

class MainActivity : AppCompatActivity() {
    var mutableList: MutableList<Student> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayUniversity = arrayOf("University","College")
        spHe.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayUniversity)
        spHe.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

        }

        btnThem.setOnClickListener {
            addStudent(mutableList)
        }
        btnHienThi.setOnClickListener {
            display(mutableList)
        }
        btnXoa.setOnClickListener {
            deleteStudent(mutableList)
        }
        btnSua.setOnClickListener {
            updateStudent(mutableList)
        }
        btnLoc.setOnClickListener {
            filterStudent(mutableList)
        }
        btnSapXep.setOnClickListener {
            sortStudent(mutableList)
        }
        imgTimKiem.setOnClickListener {
            searchStudent(mutableList)
        }

    }

    fun searchStudent(mutableList: MutableList<Student>){
        val list: ArrayList<Student> = arrayListOf()
        for (i in 0 until mutableList.size){
            /*if (mutableList[i].name.contains(edTimKiem.text.toString().toLowerCase())
                || mutableList[i].specializ.contains(edChuyenNganh.text.toString().toLowerCase())
                || mutableList[i].system.contains(spHe.selectedItem.toString()) ){
                list.add(mutableList[i])
            }*/
            if(mutableList[i].name.contains(edTimKiem.text.toString().toLowerCase())){
                list.add(mutableList[i])
            }

        }
        display(list)
    }

    fun sortStudent(mutableList: MutableList<Student>){
        for (i in 0 until mutableList.size-1){
            for (j in 0 until mutableList.size-1-i){
                if(mutableList[j].yearOfBirth > mutableList[j+1].yearOfBirth){
                    var tmp = mutableList[j]
                    mutableList[j] = mutableList[j+1]
                    mutableList[j+1] = tmp

                }
            }
        }
        display(mutableList)
    }

    fun filterStudent(mutableList: MutableList<Student>){

        if (spHe.selectedItem.toString().toLowerCase() == "university"){
            val filter = mutableList.filter {
                it.system.toLowerCase() == "university"
            }
            display(filter as MutableList<Student>)
        }else if( spHe.selectedItem.toString().toLowerCase() == "college"){
            val filter = mutableList.filter {
                it.system.toLowerCase() == "college"
            }
            display(filter as MutableList<Student>)
        }



    }

    fun updateStudent(mutableList: MutableList<Student>){
        if (edSDT.text.toString().isEmpty()){
            Toast.makeText(this,"bạn chưa nhập số điện thoại muốn sửa",Toast.LENGTH_LONG).show()
        } else if (edTenSv.text.toString().isEmpty()){
            Toast.makeText(this,"bạn chưa nhập tên sinh viên sửa",Toast.LENGTH_LONG).show()
        }else if(edChuyenNganh.text.toString().isEmpty()){
            Toast.makeText(this,"bạn chưa nhập chuyên ngành sửa",Toast.LENGTH_LONG).show()
        }else if(edNamSinh.text.toString().isEmpty()){
            Toast.makeText(this,"bạn chưa nhập năm sinh sửa",Toast.LENGTH_LONG).show()
        }else{
            for(i in 0 until mutableList.size){
                val phone: String =edSDT.text.toString()
                if(mutableList[i].phone == phone){
                    val student: Student = Student()
                    student.name = edTenSv.text.toString()
                    student.specializ = edChuyenNganh.text.toString()
                    student.system = spHe.selectedItem.toString()
                    student.phone = phone
                    student.yearOfBirth = edNamSinh.text.toString().toInt()
                    mutableList.set(i,student)
                }
            }
            clearText()
            display(mutableList)
        }
    }

    fun deleteStudent(mutableList: MutableList<Student>){
        if(edSDT.text.toString().isEmpty()){
            Toast.makeText(this,"bạn chưa nhập số điện thoại muốn xóa",Toast.LENGTH_LONG).show()
            return
        }

        for (i in 0 until mutableList.size){
            val phone: String =edSDT.text.toString()
            if (mutableList[i].phone == phone){
                mutableList.removeAt(i)
                    break
                }
            }
        edSDT.text.clear()
        display(mutableList)
    }

    fun addStudent(mutableList: MutableList<Student>){
        if (edTenSv.text.isEmpty()){
            Toast.makeText(this@MainActivity,"bạn chưa nhập tên",Toast.LENGTH_SHORT).show()
        }else if(edChuyenNganh.text.isEmpty()){
            Toast.makeText(this@MainActivity,"bạn chưa nhập chuyên ngành",Toast.LENGTH_SHORT).show()
        }else if(edSDT.text.isEmpty()){
            Toast.makeText(this@MainActivity,"bạn chưa nhập số điện thoại",Toast.LENGTH_SHORT).show()
        }else if(edNamSinh.text.isEmpty()){
            Toast.makeText(this@MainActivity,"bạn chưa nhập số năm sinh",Toast.LENGTH_SHORT).show()
        }else{
            if (mutableList.size > 0) {
                for (i in 0 until mutableList.size) {
                    if (mutableList[i].phone == edSDT.text.toString()) {
                        Toast.makeText(this@MainActivity, "số dt ton tại", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
                val student: Student = Student()
                student.name = edTenSv.text.toString()
                student.phone = edSDT.text.toString()
                student.specializ = edChuyenNganh.text.toString()
                student.yearOfBirth = edNamSinh.text.toString().toInt()
                student.system = spHe.selectedItem.toString()
                mutableList.add(student)
                display(mutableList)
                clearText()

            }else{
                val student: Student = Student()
                student.name = edTenSv.text.toString()
                student.phone = edSDT.text.toString()
                student.specializ = edChuyenNganh.text.toString()
                student.yearOfBirth = edNamSinh.text.toString().toInt()
                student.system = spHe.selectedItem.toString()
                mutableList.add(student)
                display(mutableList)
                clearText()
            }



        }

    }
    fun  display(mutableList: MutableList<Student>){
        listSV.adapter = StudentAdapter(this,mutableList)
    }

    fun clearText(){
        edTenSv.text.clear()
        edChuyenNganh.text.clear()
        edNamSinh.text.clear()
        edSDT.text.clear()
    }

}