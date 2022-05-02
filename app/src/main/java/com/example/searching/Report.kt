package com.example.searching
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity

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
    }


}