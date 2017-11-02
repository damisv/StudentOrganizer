package com.assist.tutorial.damian.studentorganizer.Adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.assist.tutorial.damian.studentorganizer.Objects.Lesson
import com.assist.tutorial.damian.studentorganizer.Objects.LessonType
import com.assist.tutorial.damian.studentorganizer.R

/**
 * Created by damian on 02.11.2017.
 */
class MyRecyclerViewAdapter(private var lessons:List<Lesson>):RecyclerView.Adapter<MyRecyclerViewAdapter.LessonViewHolder>() {

    override fun getItemCount(): Int {
        return lessons.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onBindViewHolder(holder: LessonViewHolder?, position: Int) {
        with(holder!!){
            lessonName.text = lessons[position].name
            lessonDescription.text = lessons[position].description
            val tempTime = lessons[position].startTime?.toLocalTime()?.hourOfDay.toString() +
                    ":" + lessons[position].startTime?.toLocalTime()?.minuteOfHour.toString() +
                    " - " + lessons[position].endTime?.toLocalTime()?.hourOfDay.toString() +
                    ":" + lessons[position].endTime?.toLocalTime()?.minuteOfHour.toString()
            lessonTimeSchedule.text = tempTime
            val tempName = lessons[position].professor.name +" "+ lessons[position].professor.surname
            professor.text = tempName
            if (lessons[position].lessonType == LessonType.LAB) {
                cardView.cardBackgroundColor = ColorStateList.valueOf(Color.DKGRAY)
            }
            cardView.setOnClickListener(View.OnClickListener {
                val builder: AlertDialog.Builder = AlertDialog.Builder(holder.cardView.context, android.R.style.Theme_Material_Dialog_Alert)
                builder.setTitle(lessons[position].name)
                        //.setView(layout)
                        .setMessage("At $tempTime with $tempName \n" +
                                " Turn off reminder?")
                        .setPositiveButton(android.R.string.yes) { dialog, which ->
                            //
                        }
                        .setNegativeButton(android.R.string.no) { dialog, which ->
                            // do nothing
                        }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()

            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LessonViewHolder {
        val view:View = LayoutInflater.from(parent!!.context).inflate(R.layout.item,parent,false)
        return LessonViewHolder(view)
    }

    class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView:CardView = itemView.findViewById(R.id.cardView)
        var lessonName:TextView = itemView.findViewById(R.id.lessonName)
        var lessonTimeSchedule: TextView = itemView.findViewById(R.id.lessonTime)
        var lessonDescription:TextView = itemView.findViewById(R.id.lessonDescription)
        var professor:TextView = itemView.findViewById(R.id.professorName)
    }



}