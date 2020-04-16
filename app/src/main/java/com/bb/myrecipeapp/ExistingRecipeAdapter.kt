package com.bb.myrecipeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

    class ExistingRecipeAdapter(context: Context?, recipeList: LinkedList<String>) : RecyclerView.Adapter<ExistingRecipeAdapter.ListViewHolder>() {
        private val mRecipeList: LinkedList<String>
        private val mInflater: LayoutInflater

        inner class ListViewHolder(itemView: View, adapter: ExistingRecipeAdapter) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
            val recipeItemView: TextView
            val mAdapter: ExistingRecipeAdapter
            override fun onClick(view: View) {
                val mPosition = layoutPosition
                val element = mRecipeList[mPosition]
                mRecipeList[mPosition] = "Clicked! $element"
                mAdapter.notifyDataSetChanged()
            }

            init {
                recipeItemView = itemView.findViewById(R.id.recipe)
                mAdapter = adapter
                itemView.setOnClickListener(this)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): ListViewHolder {
            val mItemView = mInflater.inflate(
                R.layout.recipe_layout, parent, false)

            return ListViewHolder(mItemView, this)
        }

        override fun onBindViewHolder(holder: ListViewHolder,
                                      position: Int) {
            val mCurrent = mRecipeList[position]
            holder.recipeItemView.text = mCurrent
        }

        override fun getItemCount(): Int {
            return mRecipeList.size
        }

        init {
            mInflater = LayoutInflater.from(context)
            mRecipeList = recipeList
        }
    }