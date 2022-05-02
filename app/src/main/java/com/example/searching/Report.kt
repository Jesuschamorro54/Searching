package com.example.searching

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.textfield.TextInputEditText

class Report : AppCompatActivity() {

    companion object{
        private const val DIRECCION_REQUEST_CODE = 1
        private const val TAG = "Error en Report"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)

        // SELECT
        val select = findViewById<AutoCompleteTextView>(R.id.autoTextView);
        val publication_type = resources.getStringArray(R.array.publication_type);
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, publication_type);
        select.setAdapter(adapter)

        // GOOGLE MAPS CON SDK
        Places.initialize(getApplicationContext(), getString(R.string.android_sdk_places_api_key))

        val input_location = findViewById<EditText>(R.id.input_location);
        input_location.setOnClickListener{
            autocompleteFrom(DIRECCION_REQUEST_CODE)
        }
    }

    private fun autocompleteFrom(requestCode: Int){
        val fields = listOf(Place.Field.ID, Place.Field.NAME)
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .build(this)
        startActivityForResult(intent, requestCode)
    }

}