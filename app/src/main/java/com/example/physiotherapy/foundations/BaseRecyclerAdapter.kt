package com.example.physiotherapy.foundations

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class
BaseRecyclerAdapter<T :Any> (
    protected val masterList: MutableList<T> = mutableListOf(),
    protected val touchActionDelegate: (() -> Unit)?
):  RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    fun updateList(list: List<T>){
        val result = DiffUtil.calculateDiff(DiffUtilCallbackImpl(masterList, list))
        masterList.clear()
        masterList.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) {
        TYPE_ADD_BUTTON
    } else {
        TYPE_INFO
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AddButtonViewHolder){
            holder.onBind(Unit, position, touchActionDelegate, position-1)
        }
       else {
            (holder as BaseViewHolder<T>).onBind(masterList[position - 1], position, null,position-1)
        }

    }

    override fun getItemCount(): Int = masterList.size + 1

    abstract class AddButtonViewHolder(view: View): BaseViewHolder<Unit>(view)


    abstract class BaseViewHolder<E>(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(data: E, position: Int, touchActionDelegate: (() -> Unit)? =null, listIndex :Int)
    }

    companion object {
        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1
    }

    class DiffUtilCallbackImpl<T> (val oldList: List<T>, val newList: List<T>) : DiffUtil.Callback(){
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}