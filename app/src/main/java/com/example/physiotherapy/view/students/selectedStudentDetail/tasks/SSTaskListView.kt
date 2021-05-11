package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.physiotherapy.model.Task
import kotlinx.android.synthetic.main.fragment_selected_student_tasks.view.*

class SSTaskListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1,
    ) : ConstraintLayout(context, attrs, defStyleAttr) {
    private lateinit var adapter: SSTaskAdapter
    private lateinit var touchDelegate: () -> Unit
    private lateinit var dataDelegate: SSTaskListViewContract
    fun initView(touchActionDelegate: () -> Unit, daDelegate:SSTaskListViewContract){
        setTouchFunc(touchActionDelegate, daDelegate)
        setUpView()
    }

    private fun setTouchFunc(touchActionDelegate: () -> Unit, daDelegate:SSTaskListViewContract){
        dataDelegate = daDelegate
        touchDelegate = touchActionDelegate
    }

    private fun setUpView() {
        selected_student_task_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = SSTaskAdapter(
            touchActionDelegate = touchDelegate,
            dataDelegate = dataDelegate
        )
        selected_student_task_recycler_view.adapter = adapter
    }

    fun updateList(list: List<Task>){
        adapter.updateList(list as MutableList<Task>)
    }
}

