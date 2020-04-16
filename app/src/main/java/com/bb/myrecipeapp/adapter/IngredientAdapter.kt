package com.bb.myrecipeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bb.myrecipeapp.R
import java.util.*


class IngredientAdapter(context: Context?, ingredientList: LinkedList<String>) : RecyclerView.Adapter<IngredientAdapter.ListViewHolder>() {

    private val mIngredientList: LinkedList<String>
    private val mInflater: LayoutInflater

    inner class ListViewHolder(itemView: View, adapter: IngredientAdapter) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val ingredientItemView: TextView
        val mAdapter: IngredientAdapter
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