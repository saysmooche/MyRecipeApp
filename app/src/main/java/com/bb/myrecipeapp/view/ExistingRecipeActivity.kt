package com.bb.myrecipeapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bb.myrecipeapp.R
import com.bb.myrecipeapp.adapter.ExistingRecipeAdapter
import com.bb.myrecipeapp.viewmodel.RecipeViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment_layout.*
import java.util.*

    class ExistingRecipeActivity : AppCompatActivity() {

        private val loginFragment: LoginFragment = LoginFragment()
        private lateinit var viewModel: RecipeViewModel

        private val mRecipeList = LinkedList<String>()
        private lateinit var mRecyclerView: RecyclerView
        private var mAdapter: ExistingRecipeAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.existing_main)

            val toolbar = findViewById<Toolbar>(R.id.toolbar2)
            setSupportActionBar(toolbar)
            val buttonPlus = findViewById<Button>(R.id.button)
            val buttonMinus = findViewById<Button>(R.id.button2)
            val buttonCreate = findViewById<Button>(R.id.button3)
            val editName = findViewById<EditText>(R.id.editText2)
            var value = editName.text.toString()

            buttonPlus.setOnClickListener {
                val recipeListSize = mRecipeList.size
                mRecipeList.addLast(" $value (Recipe Name)")
                mRecyclerView.adapter!!.notifyItemInserted(recipeListSize)
                mRecyclerView.smoothScrollToPosition(recipeListSize)
            }
            for (i in 0..10) {
                mRecipeList.addLast("Default Recipe $value")
            }
            mRecyclerView = findViewById(R.id.recyclerview2)
            mAdapter = ExistingRecipeAdapter(
                this,
                mRecipeList
            )
            mRecyclerView.adapter = mAdapter
            mRecyclerView.layoutManager = LinearLayoutManager(this)

            viewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)
            if(viewModel.getUserLoggedIn()){
                getRecipes();
                setEmailAsUsername();
            } else {

                getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.login_fragment_view, loginFragment)
                    .commit();

            }

        }

        private fun setEmailAsUsername() {
            user_name_edittext2 = FirebaseAuth.getInstance().currentUser.email
        }

        fun loginSuccess() {
            getRecipes()
            setEmailAsUsername()
            supportFragmentManager.beginTransaction()
                .remove(loginFragment)
                .commit()
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_layout2, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            return if (id == R.id.action_settings2) {
                true
            } else super.onOptionsItemSelected(item)
        }

        fun goCreate(view: View?){
            val intent = Intent(applicationContext, RecyclerViewActivity::class.java)
            startActivity(intent)
        }
    }