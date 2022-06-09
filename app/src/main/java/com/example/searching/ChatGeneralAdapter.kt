package com.example.searching

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items_chat.view.*
import android.content.Intent

class ChatGeneralAdapter(val listachatgeneral: (ListaChatGeneral) -> Unit):RecyclerView.Adapter<ChatGeneralAdapter.ChatViewHolder>() {


    var chats: List<ListaChatGeneral> = emptyList()

    fun setData(list: List<ListaChatGeneral>) {
        chats = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items_chat,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.itemView.tvName.text = chats[position].nombrePersona
        holder.itemView.tvMensaje.text = chats[position].mensaje
        holder.itemView.tvHora.text = chats[position].hora
        Picasso.get().load(chats[position].imagen).into(holder.itemView.ivPerson)



        holder.itemView.setOnClickListener {
            listachatgeneral(chats[position])
        }
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChatHolder(layoutInflater.inflate(R.layout.items_chat, parent, false))
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.render(listachatgeneral[position])

       /* holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val activity=v!!.context as AppCompatActivity
                //val chatPersonal= activity_chat_personal()

                var intent= Intent(activity,activity_chat_personal::class.java)
                activity.startActivity(intent)


             
                //activity.supportFragmentManager.beginTransaction().replace(R.id.id_chat, chatPersonal).addToBackStack(null).commit()
            }
            
        })*/
    }

    override fun getItemCount(): Int = listachatgeneral.size

    class ChatHolder(val view:View):RecyclerView.ViewHolder(view){
        fun render(listachatgeneral:ListaChatGeneral){
            view.tvName.text = listachatgeneral.nombrePersona
            view.tvMensaje.text = listachatgeneral.mensaje
            view.tvHora.text = listachatgeneral.hora
            Picasso.get().load(listachatgeneral.imagen).into(view.ivPerson)
        }
    }*/
