package com.example.physiotherapy.view.students.selectedStudentDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.physiotherapy.model.SelectedStudentSlideModel
import kotlinx.android.synthetic.main.selected_student_item_slide.view.*


class SelectedStudentSlidePagerAdapter(private val list: List<SelectedStudentSlideModel>) : RecyclerView.Adapter<SelectedStudentViewHolder> (){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SelectedStudentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val lisItem : View = layoutInflater.inflate(com.example.physiotherapy.R.layout.selected_student_item_slide, parent, false)
        return SelectedStudentViewHolder(lisItem)
    }

    override fun onBindViewHolder(holder: SelectedStudentViewHolder, position: Int) {
        val m: SelectedStudentSlideModel = list!![position]
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

 class SelectedStudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        selectedStudentSlideModel: SelectedStudentSlideModel
    ) {
        itemView.ivPoster.setImageResource(selectedStudentSlideModel.imageId)
        itemView.tvTitle.text = selectedStudentSlideModel.title
    }
}