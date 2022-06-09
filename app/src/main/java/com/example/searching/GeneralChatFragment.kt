package com.example.searching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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



    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
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