package com.example.searching
import android.app.Activity
import android.os.Bundle
import android.view.InflateException
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val db = FirebaseFirestore.getInstance()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<PerdidosAdapter.PerdidosHolder>? = null
    private lateinit var reportlist:ArrayList<ListaPerdidos>
    private var user=""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val args = arguments

        args?.getString("user", user)?.also {user = it}


        // SELECT
        val spinner= root.findViewById<Spinner>(R.id.spinner_list_options)


        activity?.let{
            ArrayAdapter.createFromResource(

                it,R.array.options_list,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        }

        reportlist= arrayListOf<ListaPerdidos>()
        //var btn_chat = root.findViewById<Button>(R.id.btn_chat)

        //btn_chat.setOnClickListener { newChat(btn_chat.tag.toString()) }
        getReportData()


        return root
    }



    private fun getReportData() {
        db.collection("report").get().addOnSuccessListener { documentos->
            for(documento in documentos){

                var report=ListaPerdidos (
                    documento.get("nomnbrePersona") as String,
                    documento.get("descripciontt")as String,
                    documento.get("descripcion") as String,
                    documento.get("imagen") as String)

                reportlist.add(report)
            }


            printViewHome()

        }


    }

    private fun printViewHome(){
        rvlista.apply {
            layoutManager= LinearLayoutManager(activity)
            adapter=PerdidosAdapter(reportlist)
        }
    }

    private fun newChat(otherMail:String){
        var chatId = UUID.randomUUID().toString()
        var otherPerson = otherMail

        val users = listOf(user, otherPerson)

        val chat = ListaChatGeneral(
            id = chatId,
            nombrePersona = "",
            mensaje = "",
            hora = "",
            imagen = "",
            users = users

        )

        db.collection("chats").document(chatId).set(chat)
        db.collection("users").document(user).collection("chats").document(chatId).set(chat)
        db.collection("users").document(otherPerson).collection("chats").document(chatId).set(chat)

        val intent = Intent(activity, ChatPersonal::class.java)
        intent.putExtra("chatId", chatId)
        intent.putExtra("user", user)
        startActivity(intent)
    }

}
