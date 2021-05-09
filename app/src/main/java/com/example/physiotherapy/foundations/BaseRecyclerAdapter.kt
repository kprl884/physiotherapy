package com.example.physiotherapy.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class
BaseRecyclerAdapter<T>(
    protected val masterList: MutableList<T> = mutableListOf(),
    protected val touchActionDelegate: (() -> Unit)?
):  RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemViewType(position: Int): Int = if (position == 0) {
        TYPE_ADD_BUTTON
    } else {
        TYPE_INFO
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AddButtonViewHolder){
            holder.onBind(Unit, position, touchActionDelegate)
        }
       else {
            (holder as BaseViewHolder<T>).onBind(masterList[position - 1], position)
        }

    }

    override fun getItemCount(): Int = masterList.size + 1

    abstract class AddButtonViewHolder(view: View): BaseViewHolder<Unit>(view)


    abstract class BaseViewHolder<E>(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(data: E, position: Int, touchActionDelegate: (() -> Unit)? =null)
    }

    companion object {
        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1
    }
}