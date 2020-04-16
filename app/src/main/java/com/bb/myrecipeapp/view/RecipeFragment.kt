package com.bb.myrecipeapp.view


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bb.myrecipeapp.R
import com.bb.myrecipeapp.util.Constants.Companion.REQUEST_CODE
import com.bb.myrecipeapp.util.DebugLogger
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.recipe_fragment_layout.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class RecipeFragment: Fragment() {

    private lateinit var imageDirectory: String
    private lateinit var firebaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = layoutInflater.inflate(R.layout.recipe_fragment_layout, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        select_image_button.setOnClickListener {
            val imageIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            context?.let { activityContext ->
                if (imageIntent.resolveActivity(activityContext.packageManager) != null) {//Verified that this device has a camera
                    try {
                        val temporaryFile = createTemporaryFile()
                        temporaryFile?.let { file ->

                            val imageUri: Uri = FileProvider.getUriForFile(
                                activityContext,
                                getString(R.string.my_provider_name),
                                temporaryFile
                            )

                            imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                            startActivityForResult(imageIntent, REQUEST_CODE)
                        }

                    } catch (exception: Exception) {
                        DebugLogger.logError(exception)
                    }
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createTemporaryFile(): File {
        val fileName = getString(
            R.string.image_name_text,
            SimpleDateFormat(getString(R.string.date_format_text), Locale.US).format(Date())
        )

        val fileDirectory = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        val actualImage: File = File.createTempFile(
            fileName,
            getString(R.string.image_postfix),
            fileDirectory
        )

        imageDirectory = actualImage.absolutePath
        return actualImage


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {

            val storedImage = BitmapFactory.decodeFile(imageDirectory)

            val baOS = ByteArrayOutputStream()
            storedImage.compress(Bitmap.CompressFormat.JPEG, 100, baOS)

            val firebaseStorageReference = FirebaseStorage.getInstance()
                .reference
                .child("ProfilePicture/")
            val uploadTask = firebaseStorageReference.putBytes(baOS.toByteArray())

            uploadTask.addOnCompleteListener{ completeTask ->

                if(completeTask.isSuccessful){
                    firebaseStorageReference.downloadUrl
                        .addOnCompleteListener { downloadTask ->
                            if(downloadTask.isSuccessful){
                                DebugLogger.logDebug(downloadTask.result.toString())
                                displayFromFirebase(downloadTask.result.toString())
                            } else {
                                downloadTask.exception?.let { DebugLogger.logError(it) }
                            }
                        }

                } else {
                    completeTask.exception?.let { DebugLogger.logError(it) }
                }
            }
        }
    }

    private fun displayFromFirebase(storedImage: String) {
        context?.let { activityContext ->
            Glide.with(activityContext)
                .applyDefaultRequestOptions(RequestOptions().centerCrop())
                .load(storedImage)
                .into(recipe_image)
        }

    }

    fun uploadRecipe() {

        val databaseKey: String? = firebaseReference.push().key
//        val recipe = Recipe(
//            recipe_name_edittext.text.toString(),
//            List<Ingredients>,
//            method_edittext.text.toString(),
//            recipe_image
//        )
//        if (databaseKey != null) {
//            FirebaseDatabase.getInstance().getReference("Recipe").child(databaseKey).setValue(recipe)
//        }
    }

}