package com.example.searching

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_general_chat.*

class activity_chat_general : AppCompatActivity() {
    val listachatgeneral:List<ListaChatGeneral> = listOf(
        ListaChatGeneral(nombrePersona="Tatiana Chamorro", mensaje = "Lo encontré en el parque...",  hora = "02:30pm", imagen = "https://images.pexels.com/photos/6453920/pexels-photo-6453920.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        ListaChatGeneral(nombrePersona="Julián Gómez", mensaje = "Lo encontré en el parque...", hora = "10:00am", imagen = "https://images.pexels.com/photos/6453920/pexels-photo-6453920.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        ListaChatGeneral(nombrePersona="Andrea Casallas", mensaje = "Lo encontré en el parque...", hora = "03:05pm", imagen = "https://images.pexels.com/photos/6453920/pexels-photo-6453920.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_general_chat)
        initRecycler()
    }

    fun initRecycler(){
        rvlistaChatGeneral.layoutManager = LinearLayoutManager(this)
        val adapter = ChatGeneralAdapter(listachatgeneral)
        rvlistaChatGeneral.adapter = adapter
    }
}