package com.example.searching

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items_chat.view.*

class ChatGeneralAdapter(val listachatgeneral:List<ListaChatGeneral>):RecyclerView.Adapter<ChatGeneralAdapter.ChatHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChatHolder(layoutInflater.inflate(R.layout.items_chat, parent, false))
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.render(listachatgeneral[position])
    }

    override fun getItemCount(): Int = listachatgeneral.size

    class ChatHolder(val view:View):RecyclerView.ViewHolder(view){
        fun render(listachatgeneral:ListaChatGeneral){
            view.tvName.text = listachatgeneral.nombrePersona
            view.tvMensaje.text = listachatgeneral.mensaje
            view.tvHora.text = listachatgeneral.hora
            Picasso.get().load(listachatgeneral.imagen).into(view.ivPerson)
        }
    }
}