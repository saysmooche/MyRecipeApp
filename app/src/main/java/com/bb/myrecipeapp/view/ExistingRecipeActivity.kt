package com.bb.myrecipeapp.view


import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bb.myrecipeapp.R
import com.bb.myrecipeapp.adapter.ExistingRecipeAdapter
import com.bb.myrecipeapp.util.Constants.Companion.REQUEST_CODE
import com.bb.myrecipeapp.util.Constants.Companion.STORAGE_PERMISSION
import com.bb.myrecipeapp.viewmodel.RecipeViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment_layout.*
import java.util.*

class ExistingRecipeActivity : AppCompatActivity(){

    private val loginFragment: LoginFragment = LoginFragment()
    private lateinit var viewModel: RecipeViewModel

    private val mRecipeList = LinkedList<String>()
    private lateinit var mRecyclerView: RecyclerView
    private var mAdapter: ExistingRecipeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.existing_main)
        getSupportFragmentManager().beginTransaction()
            .add(android.R.id.content, LoginFragment()).commit()

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
        if(viewModel.getUserLoggedIn() == true){
            setEmailAsUsername();
        }

    }

    private fun setEmailAsUsername() {
        user_name_edittext2.setText(FirebaseAuth.getInstance().currentUser?.email?:"unknown")
    }

    fun loginSuccess() {
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
        val intent = Intent(applicationContext, IngredientViewActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        if (ContextCompat.checkSelfPermission(
                this,
                STORAGE_PERMISSION
            ) != 0
        ) {
            this.requestPermissions()
        } else {
            showLoginFragment()
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(STORAGE_PERMISSION),
            REQUEST_CODE
        )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (permissions[0] == STORAGE_PERMISSION);
            run {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) showLoginFragment() else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            STORAGE_PERMISSION
                        )
                    ) requestPermissions()
                }
            }
        }
    }

    private fun showLoginFragment() {
        TODO("Not yet implemented")
    }

}
