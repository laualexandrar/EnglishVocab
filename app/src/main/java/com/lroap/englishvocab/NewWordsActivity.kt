package com.lroap.englishvocab

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_words.*

class NewWordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_words)

        val sharedPref = getSharedPreferences("Adding Words and meanings", Context.MODE_PRIVATE)
        newWordToLearnTxt.text = sharedPref.getString("NEWWORD", "")
        newMeaningToLearnTxt.text =sharedPref.getString("NEWMEANING", "")
        
    }
}