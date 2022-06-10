package com.example.searching

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_perdidos.view.*

class PerdidosAdapter(val listaPerdidos: ArrayList<ListaPerdidos>): RecyclerView.Adapter<PerdidosAdapter.PerdidosHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerdidosHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return PerdidosHolder(layoutInflater.inflate(R.layout.item_perdidos,parent,false))
    }
    override fun getItemCount(): Int =listaPerdidos.size

    override fun onBindViewHolder(holder: PerdidosHolder, position: Int) {
        holder.render(listaPerdidos[position])
    }


    class PerdidosHolder(val view: View): RecyclerView.ViewHolder(view){
        fun render(listaPerdidos:ListaPerdidos){
            //view.btn_chat.tag = listaPerdidos.email
            view.tvNombre.text= listaPerdidos.nomnbrePersona
            view.tvDescripciontt.text=listaPerdidos.descripciontt
            view.tvDescripcion.text=listaPerdidos.descripcion
            if(!listaPerdidos.imagen.isEmpty() || listaPerdidos.imagen != ""){
                Picasso.get().load(listaPerdidos.imagen).into(view.ivPerdidos)
            }

        }
    }
}