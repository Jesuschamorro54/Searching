package com.example.searching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_general_chat.*
class GeneralChatFragment : Fragment(R.layout.fragment_general_chat) {
    // TODO: Rename and change types of parameters

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ChatGeneralAdapter.ChatHolder>? = null

    val listachatgeneral:List<ListaChatGeneral> = listOf(
        ListaChatGeneral(nombrePersona="Tatiana Chamorro", mensaje = "Lo encontré en el parque...",  hora = "02:30pm", imagen = "https://images.pexels.com/photos/6453920/pexels-photo-6453920.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        ListaChatGeneral(nombrePersona="Julián Gómez", mensaje = "Lo encontré en el parque...", hora = "10:00am", imagen = "https://www.blogdelfotografo.com/wp-content/uploads/2020/02/apoyado6-2-scaled.jpg"),
        ListaChatGeneral(nombrePersona="Andrea Casallas", mensaje = "Lo encontré en el parque...", hora = "03:05pm", imagen = "https://ichef.bbci.co.uk/news/300/cpsprodpb/AB7F/production/_115330934_evelina_cabrera.jpg"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_general_chat, container, false)

        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        rvlistaChatGeneral.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = ChatGeneralAdapter(listachatgeneral)
        }
    }
}