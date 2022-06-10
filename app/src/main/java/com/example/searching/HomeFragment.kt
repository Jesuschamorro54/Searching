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
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore



class HomeFragment : Fragment(R.layout.fragment_home) {

    private val db = FirebaseFirestore.getInstance()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<PerdidosAdapter.PerdidosHolder>? = null
    private lateinit var reportlist:ArrayList<ListaPerdidos>


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

        reportlist= arrayListOf<ListaPerdidos>()
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

}
