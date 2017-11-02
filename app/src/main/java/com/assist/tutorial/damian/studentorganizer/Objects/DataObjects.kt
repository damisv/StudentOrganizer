package com.assist.tutorial.damian.studentorganizer.Objects

import org.joda.time.DateTime

/**
 * Created by damian on 02.11.2017.
 */

data class DailySchedule(var lessons:List<Lesson>, var dayOfWeek: Int)

data class Lesson(var lessonType:LessonType,var name:String,var description:String,var startTime:DateTime?,var endTime:DateTime?,var professor:Professor) {}

enum class LessonType(val type:String,val id:Int){
    THEORY("Theory",0),
    LAB("Laboratory",1)
}

data class Professor(var name:String,var surname:String?){}
