package com.bb.myrecipeapp.view

    import android.content.Intent
    import android.os.Bundle
    import android.view.Menu
    import android.view.MenuItem
    import android.view.View
    import android.widget.Button
    import android.widget.EditText
    import androidx.appcompat.widget.Toolbar
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.FragmentTransaction
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.bb.myrecipeapp.R
    import com.bb.myrecipeapp.adapter.IngredientAdapter
    import com.bb.myrecipeapp.base.BaseClass
    import java.util.*


class IngredientViewActivity : BaseClass() {

        private val mIngredientList = LinkedList<String>()
        private lateinit var mRecyclerView: RecyclerView
        private var mAdapter: IngredientAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            val button123 = findViewById<Button>(R.id.button)
            var editName = findViewById<EditText?>(R.id.editText1)
            val value = editName!!.text.toString()

            button123.setOnClickListener {
                val ingredientListSize = mIngredientList.size
                mIngredientList.addLast(" $value Ingredient Name")
                mRecyclerView.adapter!!.notifyItemInserted(ingredientListSize)
                mRecyclerView.smoothScrollToPosition(ingredientListSize)
            }
            for (i in 0..10) {
                mIngredientList.addLast("Default Ingredient $value")
            }
            mRecyclerView = findViewById(R.id.recyclerview)
            mAdapter =
                IngredientAdapter(
                    this,
                    mIngredientList
                )
            mRecyclerView.adapter = mAdapter
            mRecyclerView.layoutManager = LinearLayoutManager(this)
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_layout, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            return if (id == R.id.action_settings) {
                true
            } else super.onOptionsItemSelected(item)
        }

        fun goBack(v: View){
            val intent = Intent(this, ExistingRecipeActivity::class.java)
            startActivity(intent)
        }

        fun displayAll(v: View) {
            val newFragment: Fragment = RecipeFragment()
            val transaction: FragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
            transaction.replace(R.id.recipe_frame, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }