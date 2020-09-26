package com.lroap.englishvocab

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_adding_vocab.*

class AddingVocabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_vocab)

        addNewWordAndMeaningBtn.setOnClickListener {
            val newWord = addNewWordTxt.text.toString()
            val newDescription = addMeaningWordTxt.text.toString()

            if (newWord.isEmpty() || newDescription.isEmpty()){
                Toast.makeText(this, "Please complete the fields", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, " You added a new word and its meaning!", Toast.LENGTH_SHORT).show()
                storeInfo()
                navigateNextActivity()
            }
        }
    }

    private fun navigateNextActivity() {
        val intent = Intent(this, NewWordsActivity::class.java)
        startActivity(intent)
    }


    private fun storeInfo(){
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