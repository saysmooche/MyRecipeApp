package com.bb.myrecipeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class RecycleViewAdapter(context: Context?, ingredientList: LinkedList<String>) : RecyclerView.Adapter<RecycleViewAdapter.ListViewHolder>() {

    private val mIngredientList: LinkedList<String>
    private val mInflater: LayoutInflater

    inner class ListViewHolder(itemView: View, adapter: RecycleViewAdapter) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val ingredientItemView: TextView
        val mAdapter: RecycleViewAdapter
        override fun onClick(view: View) {
            val mPosition = layoutPosition
            val element = mIngredientList[mPosition]
            mIngredientList[mPosition] = "Clicked! $element"
            mAdapter.notifyDataSetChanged()
        }

        init {
            ingredientItemView = itemView.findViewById(R.id.ingredient)
            mAdapter = adapter
            itemView.setOnClickListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ListViewHolder {
        val mItemView = mInflater.inflate(
                R.layout.score_layout, parent, false)

        return ListViewHolder(mItemView, this)
    }

    override fun onBindViewHolder(holder: ListViewHolder,
                                  position: Int) {
        val mCurrent = mIngredientList[position]
        holder.ingredientItemView.text = mCurrent
    }

    override fun getItemCount(): Int {
        return mIngredientList.size
    }

    init {
        mInflater = LayoutInflater.from(context)
        mIngredientList = ingredientList
    }
}