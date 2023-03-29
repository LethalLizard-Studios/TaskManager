package net.group15.taskmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class NoteAdapter : BaseAdapter {
    lateinit var dataList: MutableList<Task>
    var removeListener: OnItemRemoveClick? = null
    fun setListener(lis: OnItemRemoveClick) {
        this.removeListener = lis
    }

    constructor(dataList: MutableList<Task>) : super() {
        this.dataList = dataList
    }


    override fun getCount() = this.dataList.size

    override fun getItem(position: Int) = this.dataList[position]

    override fun getItemId(position: Int) = 0L

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var item = this.dataList[position]
        var itemView =
            LayoutInflater.from(parent!!.context).inflate(R.layout.adapter_task_list, null, false)
        var titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = item.name
        var removeTextView = itemView.findViewById<TextView>(R.id.tv_del)

        removeTextView.setOnClickListener {
            removeListener?.let{
                it.itemClick(position)
            }
        }
        return itemView

    }

}

interface OnItemRemoveClick {
    fun itemClick(position: Int)
}