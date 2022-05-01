package com.example.searching

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.list_perdidos.*

class MainActivityPerdidos : AppCompatActivity() {
    val perdidos = listOf(
        ListaPerdidos("julian","cachorro pequeño","perro mediano de color gris,de patita fracturada","https://www.caninoviajero.es/wp-content/uploads/2020/06/razas-de-perros-grises.jpg"),
        ListaPerdidos("julian","perrito negro,pequeño","los almendros","https://rockcontent.com/es/wp-content/uploads/sites/3/2019/02/Consejos-para-hacer-ima%CC%81genes-increi%CC%81bles-1024x538.png"),
        ListaPerdidos("julian","cachorro pequeño","perro mediano de color gris,de patita fracturada","https://ideasnuevas.net/wp-content/uploads/2016/08/Wallpapersxl-Perritos-Bonitos-Seguro-Que-Estabas-Esperando-Otro-Fondo-De-Perros-Pues-Ya-No-Tienes-276709-1440x1080.jpg"),
        ListaPerdidos("julian","perrito negro,pequeño","perro mediano de color gris,de patita fracturada","https://www.caninoviajero.es/wp-content/uploads/2020/06/razas-de-perros-grises.jpg"),
        ListaPerdidos("julian","cachorro pequeño","perro mediano de color gris,de patita fracturada","https://www.pazholandesa.pe/wp-content/uploads/2021/10/244574239_1542038259476406_6350458437200127368_n-960x500.jpg"),
        ListaPerdidos("julian","perrito negro,pequeño","perro mediano de color gris,de patita fracturada","https://ideasnuevas.net/wp-content/uploads/2016/08/Wallpapersxl-Perritos-Bonitos-Seguro-Que-Estabas-Esperando-Otro-Fondo-De-Perros-Pues-Ya-No-Tienes-276709-1440x1080.jpg"),
        ListaPerdidos("julian","cachorro pequeño","perro mediano de color gris,de patita fracturada","https://apadrinaunperro.org/wp-content/uploads/2022/02/razas-de-perros-pequenos-960x500.jpg")
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_perdidos)
        initRecycler()
    }

    fun initRecycler(){
        rvlista.layoutManager= LinearLayoutManager(this)
        val adapter=PerdidosAdapter(perdidos)
        rvlista.adapter=adapter
    }
}