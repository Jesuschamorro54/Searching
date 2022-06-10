package com.example.searching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_report.*
import java.util.*


class ReportFragment : Fragment(R.layout.fragment_report) {
    private val db = FirebaseFirestore.getInstance()
    private var user = "ofelia@hotmail.com"



    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_report, container, false)
        val args = arguments

        args?.getString("user", user)?.also {user = it}



        // SELECT
        val spinner= root.findViewById<Spinner>(R.id.spinner_options)

        activity?.let {
            ArrayAdapter.createFromResource(
                it,R.array.options_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        }
        var button_publicar = root.findViewById<Button>(R.id.button_post)

        button_publicar.setOnClickListener { guardar() }




        return root
    }



    private fun guardar(){
        val Id = UUID.randomUUID().toString()
        button_post.setOnClickListener{
            db.collection("report").document(Id).set(
                hashMapOf("email" to user,
                    "nomnbrePersona" to "Juan Quesada",
                    "tipo" to spinner_options.selectedItem.toString(),
                    "direccion" to input_location.text.toString(),
                    "descripciontt" to features.text.toString(),
                    "descripcion" to description.text.toString(),
                     "imagen" to "https://wp.es.aleteia.org/wp-content/uploads/sites/7/2018/01/web3-crying-child-boy-tears-sad-upset-tantrum-shutterstock.jpg?w=620&h=348&crop=1")
            )

        }
    }
}