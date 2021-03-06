package com.example.excercise1_monstarlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
            sortStudentWithPhone(mutableList)
        }
        imgTimKiem.setOnClickListener {
            searchStudent(mutableList)
        }
        btnSapXepNamsinh.setOnClickListener {
            sortStudent(mutableList)
        }
        btnSapXepTen.setOnClickListener {
            sortStudentWithName(mutableList)
        }

    }

    fun searchStudent(mutableList: MutableList<Student>){
        val list: ArrayList<Student> = arrayListOf()
        for (i in 0 until mutableList.size){
            if (mutableList[i].name.contains(edTimKiem.text.toString().toLowerCase())
                || mutableList[i].specializ.contains(edTimKiem.text.toString().toLowerCase())
                || mutableList[i].system.contains(edTimKiem.text.toString().toLowerCase())
                || mutableList[i].phone.contains(edTimKiem.text.toString().toLowerCase())
                || mutableList[i].yearOfBirth.contains(edTimKiem.text.toString().toLowerCase())){
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

    fun sortStudentWithPhone(mutableList: MutableList<Student>){
        for (i in 0 until mutableList.size-1){
            for (j in 0 until mutableList.size-1-i){
                if(mutableList[j].phone > mutableList[j+1].phone){
                    var tmp = mutableList[j]
                    mutableList[j] = mutableList[j+1]
                    mutableList[j+1] = tmp
                }
            }
        }
        display(mutableList)
    }
    fun sortStudentWithName(mutableList: MutableList<Student>){
        for (i in 0 until mutableList.size-1){
            for (j in 0 until mutableList.size-1-i){
                if(mutableList[j].name > mutableList[j+1].name){
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
            Toast.makeText(this,"b???n ch??a nh???p s??? ??i???n tho???i mu???n s???a",Toast.LENGTH_LONG).show()
        } else if (edTenSv.text.toString().isEmpty()){
            Toast.makeText(this,"b???n ch??a nh???p t??n sinh vi??n s???a",Toast.LENGTH_LONG).show()
        }else if(edChuyenNganh.text.toString().isEmpty()){
            Toast.makeText(this,"b???n ch??a nh???p chuy??n ng??nh s???a",Toast.LENGTH_LONG).show()
        }else if(edNamSinh.text.toString().isEmpty()){
            Toast.makeText(this,"b???n ch??a nh???p n??m sinh s???a",Toast.LENGTH_LONG).show()
        }else{
            for(i in 0 until mutableList.size){
                val phone: String =edSDT.text.toString()
                if(mutableList[i].phone == phone){
                    val student: Student = Student()
                    student.name = edTenSv.text.toString()
                    student.specializ = edChuyenNganh.text.toString()
                    student.system = spHe.selectedItem.toString()
                    student.phone = phone
                    student.yearOfBirth = edNamSinh.text.toString()
                    mutableList.set(i,student)
                }
            }
            clearText()
            display(mutableList)
        }
    }

    fun deleteStudent(mutableList: MutableList<Student>){
        if(edSDT.text.toString().isEmpty()){
            Toast.makeText(this,"b???n ch??a nh???p s??? ??i???n tho???i mu???n x??a",Toast.LENGTH_LONG).show()
            return
        }
        

        val dialog = AlertDialog.Builder(this).create()
        dialog.setMessage("b???n c?? ch???c ch???n mu???n x??a")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE,"Yes"){
            dialog,
            which -> for (i in 0 until mutableList.size){
            val phone: String =edSDT.text.toString()
            if (mutableList[i].phone == phone){
                mutableList.removeAt(i)
                break
            }
        }
            edSDT.text.clear()
            display(mutableList)
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE,"No"){
            dialog, which ->  dialog.dismiss()
        }
        dialog.show()


    }

    fun addStudent(mutableList: MutableList<Student>){
        if (edTenSv.text.isEmpty()){
            Toast.makeText(this@MainActivity,"b???n ch??a nh???p t??n",Toast.LENGTH_SHORT).show()
        }else if(edChuyenNganh.text.isEmpty()){
            Toast.makeText(this@MainActivity,"b???n ch??a nh???p chuy??n ng??nh",Toast.LENGTH_SHORT).show()
        }else if(edSDT.text.isEmpty()){
            Toast.makeText(this@MainActivity,"b???n ch??a nh???p s??? ??i???n tho???i",Toast.LENGTH_SHORT).show()
        }else if(edNamSinh.text.isEmpty()){
            Toast.makeText(this@MainActivity,"b???n ch??a nh???p s??? n??m sinh",Toast.LENGTH_SHORT).show()
        }else{
            if (mutableList.size > 0) {
                for (i in 0 until mutableList.size) {
                    if (mutableList[i].phone == edSDT.text.toString()) {
                        Toast.makeText(this@MainActivity, "s??? dt ton t???i", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
                val student: Student = Student()
                student.name = edTenSv.text.toString()
                student.phone = edSDT.text.toString()
                student.specializ = edChuyenNganh.text.toString()
                student.yearOfBirth = edNamSinh.text.toString()
                student.system = spHe.selectedItem.toString()
                mutableList.add(student)
                display(mutableList)
                clearText()

            }else{
                val student: Student = Student()
                student.name = edTenSv.text.toString()
                student.phone = edSDT.text.toString()
                student.specializ = edChuyenNganh.text.toString()
                student.yearOfBirth = edNamSinh.text.toString()
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
