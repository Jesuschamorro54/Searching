package com.example.searching
import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView



class HomeFragment : Fragment(R.layout.fragment_home) {

    private var layoutManager: RecyclerView.LayoutManager? = null

    private var adapter: RecyclerView.Adapter<PerdidosAdapter.PerdidosHolder>? = null



    val perdidos = listOf(
        ListaPerdidos("julian","cachorro pequeño","perro mediano de color gris,de patita fracturada","https://www.caninoviajero.es/wp-content/uploads/2020/06/razas-de-perros-grises.jpg"),
        ListaPerdidos("julian","perrito negro,pequeño","los almendros","https://rockcontent.com/es/wp-content/uploads/sites/3/2019/02/Consejos-para-hacer-ima%CC%81genes-increi%CC%81bles-1024x538.png"),
        ListaPerdidos("julian","cachorro pequeño","perro mediano de color gris,de patita fracturada","https://ideasnuevas.net/wp-content/uploads/2016/08/Wallpapersxl-Perritos-Bonitos-Seguro-Que-Estabas-Esperando-Otro-Fondo-De-Perros-Pues-Ya-No-Tienes-276709-1440x1080.jpg"),
        ListaPerdidos("julian","perrito negro,pequeño","perro mediano de color gris,de patita fracturada","https://www.caninoviajero.es/wp-content/uploads/2020/06/razas-de-perros-grises.jpg"),
        ListaPerdidos("julian","cachorro pequeño","perro mediano de color gris,de patita fracturada","https://www.pazholandesa.pe/wp-content/uploads/2021/10/244574239_1542038259476406_6350458437200127368_n-960x500.jpg"),
        ListaPerdidos("julian","perrito negro,pequeño","perro mediano de color gris,de patita fracturada","https://ideasnuevas.net/wp-content/uploads/2016/08/Wallpapersxl-Perritos-Bonitos-Seguro-Que-Estabas-Esperando-Otro-Fondo-De-Perros-Pues-Ya-No-Tienes-276709-1440x1080.jpg"),
        ListaPerdidos("julian","cachorro pequeño","perro mediano de color gris,de patita fracturada","https://apadrinaunperro.org/wp-content/uploads/2022/02/razas-de-perros-pequenos-960x500.jpg")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)


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


        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        rvlista.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = PerdidosAdapter(perdidos)
        }

    }



}
