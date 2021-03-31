package com.example.excercise1_monstarlab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StudentAdapter(var context: Context, var mang: MutableList<Student>) : BaseAdapter() {

    class ViewHolder(row: View){
        var txtName: TextView
        var txtPhone: TextView
        var txtSystem: TextView
        init{
            txtName = row.findViewById(R.id.txtTen)
            txtPhone = row.findViewById(R.id.txtSDT)
            txtSystem = row.findViewById(R.id.txtHe)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        if (convertView == null){
            var layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_student,null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view =convertView
            viewHolder = convertView.tag as ViewHolder
        }
        var student: Student = getItem(position) as Student
        viewHolder.txtName.text = student.name
        viewHolder.txtPhone.text = student.phone
        viewHolder.txtSystem.text = student.system

        return view as View
    }

    override fun getItem(position: Int): Any {
        return mang[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mang.size
    }
}