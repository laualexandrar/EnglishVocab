package com.lroap.englishvocab

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_adding_vocab.*

class AddingVocabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_vocab)

        // BUTTON IMAGE CLICK
        ImageButton.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    //Permission Denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request  runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    //permission already granted
                    pickImageFromGallery()
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
        }

        // ADDING WORDS
        addNewWordAndMeaningBtn.setOnClickListener {
            val newWord = addNewWordTxt.text.toString()
            val newDescription = addMeaningWordTxt.text.toString()

            if (newWord.isEmpty() || newDescription.isEmpty()) {
                Toast.makeText(this, "Please complete the fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, " You added a new word and its meaning!", Toast.LENGTH_SHORT)
                    .show()
                storeInfo()
                navigateNextActivity()
            }
        }
    }

    private fun pickImageFromGallery() {
        //intent to pick up image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;

        //permission code
        private val PERMISSION_CODE = 10001;
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permitions from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(this, "permition denied", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            wordImage.setImageURI(data?.data)
        }

    }


    private fun navigateNextActivity() {
        val intent = Intent(this, NewWordsActivity::class.java)
        startActivity(intent)
    }


    private fun storeInfo() {
        val sharedPref = getSharedPreferences("Adding Words and meanings", Context.MODE_PRIVATE)
        val sharedPrefEditor = sharedPref.edit()
        sharedPrefEditor.putString("NEWWORD", addNewWordTxt.text.toString())
        sharedPrefEditor.putString("NEWMEANING", addMeaningWordTxt.text.toString())

        sharedPrefEditor.apply()
    }
}


//        nextButton.setOnClickListener {
//            val nextButtonIntent = Intent(this, NewWordsActivity::class.java)
//            startActivity(nextButtonIntent)
//        }