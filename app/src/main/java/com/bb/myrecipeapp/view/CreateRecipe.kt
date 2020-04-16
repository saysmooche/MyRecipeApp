package com.bb.myrecipeapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bb.myrecipeapp.R
import com.bb.myrecipeapp.util.Constants.Companion.REQUEST_CODE
import com.bb.myrecipeapp.util.DebugLogger
import kotlinx.android.synthetic.main.activity_create_recipe.*

class CreateRecipe : AppCompatActivity() {

    private lateinit var imageDirectory: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        select_image_button.setOnClickListener {
            val imageIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            context.let{ activityContext->
            if(imageIntent.resolveActivity(activityContext.packageManager) != null) {
                    try {
                        val temporaryFile = createTemporaryFile()
                        temporaryFile?.let { file ->
                            val imageUri: Uri = FileProvider.getUriForFile(
                                activityContext, getString(R.string.my_provider_name),
                                temporaryFile
                            )
                            imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                            startActivityForResult(imageIntent, REQUEST_CODE)
                        }
                    }
                        catch(exception: Exception) {
                            DebugLogger.logError(exception)

                        }
                    }
                }

            }
        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }


}
