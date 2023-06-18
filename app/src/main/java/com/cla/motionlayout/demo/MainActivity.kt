package com.cla.motionlayout.demo

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class MainActivity : AppCompatActivity() {

    private val motionLayout by lazy { findViewById<MotionLayout>(R.id.motionLayout) }
    private val tvHello by lazy { findViewById<TextView>(R.id.tvHello) }
    private val rvData by lazy { findViewById<RecyclerView>(R.id.rvData) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvHello.setOnClickListener {
//            motionLayout.transitionToEnd()
        }

        val dataList = mutableListOf<String>()
        for (i in 0..100) {
            dataList.add("Item $i")
        }
        val adapter = TextAdapter(dataList)
        rvData.layoutManager = LinearLayoutManager(this)
        rvData.adapter = adapter
    }

    class TextAdapter(private val dataList: List<String>) : Adapter<TextAdapter.TextViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
            val context = parent.context
            val tv = TextView(context).also {
                it.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                it.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                it.gravity = Gravity.CENTER
                context.apply {
                    it.setPadding(0, 14.dp, 0, 14.dp)
                }
            }

            return TextViewHolder(tv)
        }

        override fun getItemCount() = dataList.size

        override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
            holder.bind(dataList[position], position)
        }

        class TextViewHolder(private val tv: TextView) : RecyclerView.ViewHolder(tv) {

            fun bind(text: String, position: Int) {
                tv.text = text
                tv.setBackgroundColor(if (position % 2 == 0) Color.WHITE else Color.BLACK)
                tv.setTextColor(if (position % 2 == 0) Color.BLACK else Color.WHITE)
            }
        }
    }
}

context(Context)
val Int.dp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), resources.displayMetrics).toInt()


