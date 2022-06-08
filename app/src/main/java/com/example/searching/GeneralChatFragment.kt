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
import kotlinx.android.synthetic.main.fragment_home.*

class GeneralChatFragment : Fragment(R.layout.fragment_general_chat) {
    // TODO: Rename and change types of parameters
    private val db = FirebaseFirestore.getInstance()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ChatGeneralAdapter.ChatHolder>? = null
    private lateinit var chatlist: ArrayList<ListaChatGeneral>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_general_chat, container, false)
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
        rvlistaChatGeneral.apply {
            layoutManager= LinearLayoutManager(activity)
            adapter=ChatGeneralAdapter(chatlist)
        }
    }


}