package com.assist.tutorial.damian.studentorganizer.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assist.tutorial.damian.studentorganizer.Adapters.MyRecyclerViewAdapter
import com.assist.tutorial.damian.studentorganizer.Objects.DailySchedule
import com.assist.tutorial.damian.studentorganizer.Objects.Lesson
import com.assist.tutorial.damian.studentorganizer.Objects.LessonType
import com.assist.tutorial.damian.studentorganizer.Objects.Professor

import com.assist.tutorial.damian.studentorganizer.R
import kotlinx.android.synthetic.main.fragment_week_schedule.*
import org.joda.time.DateTime
import java.util.*
import kotlin.collections.ArrayList


class WeekSchedule : Fragment() {

    private lateinit var tabLayout:TabLayout
    private lateinit var lessons:ArrayList<Lesson>
    private var currentDayOfWeek:Int = 1
    lateinit var weekSchedule:ArrayList<DailySchedule>
    private var linearLayoutManager: LinearLayoutManager? = null

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
        currentDayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        initializeData()
    }

    private fun initializeData(){
        weekSchedule = ArrayList<DailySchedule>()
        for (i in 0..4){
            weekSchedule.add(initializeDailySchedule(i))
        }
    }

    private fun initializeDailySchedule(dayOfWeek: Int): DailySchedule {
        val tempLessons:ArrayList<Lesson> = ArrayList()
        tempLessons.add(
                Lesson(LessonType.THEORY,"Programming  $dayOfWeek","C++ introduction course",
                        DateTime(2017,9,9,15,0),
                        DateTime(2017,9,9,17,0),
                        Professor("Dr. Prof. John","Doe")))
        tempLessons.add(
                Lesson(LessonType.LAB,"Mathematics $dayOfWeek","Linear Algebra",
                        DateTime(2017,9,9,9,30),
                        DateTime(2017,9,9,12,30),
                        Professor("Dr. Prof. William","Machiavelli")))
        tempLessons.add(Lesson(LessonType.THEORY,"Network Security $dayOfWeek","Introduction to network Security best practices",
                DateTime(2017,9,9,18,30),
                DateTime(2017,9,9,20,30),
                Professor("Dr. Prof. Robert","DeNiro")))
        return DailySchedule(tempLessons,dayOfWeek)
    }

    private fun initializeAdapter(){
        //if(lessons.isNotEmpty()){
            recycleView.adapter = MyRecyclerViewAdapter(lessons)
       // }
    }

    fun getDailySchedule(dayOfWeek: Int){
        lessons = ArrayList()
        lessons = weekSchedule[dayOfWeek].lessons as ArrayList<Lesson>
        initializeAdapter()
    }


        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_week_schedule, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = tabs
        linearLayoutManager = LinearLayoutManager(context)
        recycleView.layoutManager = linearLayoutManager
        recycleView.setHasFixedSize(true)
        //tabLayout.getTabAt(currentDayOfWeek)!!.select()
        getDailySchedule(currentDayOfWeek-2)
        tabLayout.getTabAt(currentDayOfWeek-2)?.select()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                getDailySchedule(tab.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Add new lesson", Snackbar.LENGTH_LONG)
                    .setAction("Add", myListener).show()
            initializeAdapter()
        }
    }

    private val myListener = View.OnClickListener { kotlin.run {
        lessons.add(
                Lesson(LessonType.THEORY,"Mathematics I","Linear Algebra",
                        DateTime(2017,9,9,9,30),
                        DateTime(2017,9,9,12,30),
                        Professor("Dr. Prof. William","Machiavelli")))
    } }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WeekSchedule.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): WeekSchedule {
            val fragment = WeekSchedule()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
