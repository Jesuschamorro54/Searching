package com.example.searching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_general_chat.*
import android.content.Intent
import java.util.*

class GeneralChatFragment : Fragment(R.layout.fragment_general_chat) {
    // TODO: Rename and change types of parameters
    private val db = FirebaseFirestore.getInstance()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ChatGeneralAdapter.ChatViewHolder>? = null
    private lateinit var chatlist: ArrayList<ListaChatGeneral>
    private var user=""



    private var user = ""

    val listachatgeneral:List<ListaChatGeneral> = listOf(
        ListaChatGeneral(nombrePersona="Tatiana Chamorro", mensaje = "Lo encontré en el parque...",  hora = "02:30pm", imagen = "https://images.pexels.com/photos/6453920/pexels-photo-6453920.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        ListaChatGeneral(nombrePersona="Julián Gómez", mensaje = "Lo encontré en el parque...", hora = "10:00am", imagen = "https://www.blogdelfotografo.com/wp-content/uploads/2020/02/apoyado6-2-scaled.jpg"),
        ListaChatGeneral(nombrePersona="Andrea Casallas", mensaje = "Lo encontré en el parque...", hora = "03:05pm", imagen = "https://ichef.bbci.co.uk/news/300/cpsprodpb/AB7F/production/_115330934_evelina_cabrera.jpg"),
    )


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        val args = arguments
        if (args != null) {
            user = args.getString("user", user)
            println(user)
        }


        val root = inflater.inflate(R.layout.fragment_general_chat, container, false)
        val args = arguments

        if (args != null) {
            user = args.getString("user", user)


        }
        chatlist= arrayListOf<ListaChatGeneral>()
        getChatData()
        return root


    }

    private fun getChatData() {
        db.collection("chat").get().addOnSuccessListener { documentos->
            for(documento in documentos){

                var chat=ListaChatGeneral (
                    documento.get("nombrePersona") as String,
                    documento.get("mensaje")as String,
                    documento.get("hora") as String,
                    documento.get("imagen") as String)

                chatlist.add(chat)
            }


            printChatHome()

        }


    }


    private fun printChatHome() {
        layoutManager= LinearLayoutManager(activity)
        adapter=ChatGeneralAdapter {chat->
            chatSelected(chat)

        }
        val userloged=db.collection("users").document(user)
        println("0------------------")
        println(userloged.get().result)

        userloged.collection("chats").get().addOnSuccessListener { chats ->
                println("1------------------")
                 println(chats)
                //val listChats = chats.toObjects(ListaChatGeneral::class.java)

               // (adapter as ChatGeneralAdapter).setData(listChats)
            }

        userloged.collection("chats").addSnapshotListener { chats, error ->
                if(error == null){
                    chats?.let {
                        println("2------------------")
                        println(chats)
                        //val listChats = it.toObjects(ListaChatGeneral::class.java)

                        //(adapter as ChatGeneralAdapter).setData(listChats)
                    }
                }
            }
        /*rvlistaChatGeneral.apply {
            layoutManager= LinearLayoutManager(activity)
            adapter=ChatGeneralAdapter(chatlist)
        }*/


    }

    private fun chatSelected(chat: ListaChatGeneral){
        val intent= Intent(activity,ChatPersonal::class.java)
        intent.putExtra("nombre",chat.nombrePersona)
        intent.putExtra("imagen",chat.imagen)
        startActivity(intent)
    }



}