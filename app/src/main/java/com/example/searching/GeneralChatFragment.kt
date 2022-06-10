package com.example.searching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_chat_general.view.*
import kotlinx.android.synthetic.main.fragment_general_chat.rvlistaChatGeneral
import java.util.*


class GeneralChatFragment : Fragment(R.layout.fragment_general_chat) {
    // TODO: Rename and change types of parameters

    private var layoutManager: RecyclerView.LayoutManager? = null
    private val db = FirebaseFirestore.getInstance()
    private var adapter: RecyclerView.Adapter<ChatGeneralAdapter.ChatViewHolder>? = null

    private var user = ""

    private lateinit var chatlist: ArrayList<ListaChatGeneral>
    private var user=""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val root = inflater.inflate(R.layout.fragment_general_chat, container, false)
        val args = arguments

        args?.getString("user", user)?.also { user = it }

        if (user.isNotEmpty()) initViews(root)

        return root
    }

    private fun initViews(root: View) {
        var rv = root.findViewById<RecyclerView>(R.id.rvlistaChatGeneral)

        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = ChatGeneralAdapter { chat-> chatSelected(chat) }

        val userloged = db.collection("users").document(user)

        userloged.collection("chats").get().addOnSuccessListener { chats ->
            val listChats = chats.toObjects(ListaChatGeneral::class.java)
            (rv.adapter as ChatGeneralAdapter).setData(listChats)
        }

        userloged.collection("chats").addSnapshotListener { chats, error ->
            if(error == null){ chats?.let {
                val listChats = it.toObjects(ListaChatGeneral::class.java)
                (rv.adapter as ChatGeneralAdapter).setData(listChats)
                }
            }
        }
    }


    private fun chatSelected(chat: ListaChatGeneral){
        val intent= Intent(activity,ChatPersonal::class.java)
        intent.putExtra("chatId", chat.id)
        intent.putExtra("user",user)
        startActivity(intent)
    }
}