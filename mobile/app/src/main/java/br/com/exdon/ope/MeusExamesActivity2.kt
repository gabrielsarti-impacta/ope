package br.com.exdon.ope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.actionbar.*
import kotlinx.android.synthetic.main.activity_meus_exames.*
import kotlinx.android.synthetic.main.activity_meus_exames2.*
import kotlinx.android.synthetic.main.activity_meus_exames2.layout_menu_lateral

class MeusExamesActivity2 : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meus_exames2)

        this.generic_layout = layout_menu_lateral

        // colocar toolbar
        setSupportActionBar(toolbar)


        // alterar título da ActionBar
        supportActionBar?.title = "Meus Exames"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        configuraMenuLateral()

        val arrayList = ArrayList<Model>()

        arrayList.add(Model("RAIO-X", "Data do exame: 25/09/2020", R.drawable.raio_x))
        arrayList.add(Model("ULTRASSONOGRAFIA", "Data do exame: 12/04/2020", R.drawable.ultrassonografia))
        arrayList.add(Model("TOMOGRAFIA MULTISLICE", "Data do exame: 03/08/2019", R.drawable.tomografia_multislice))

        val myAdapter = MyAdapter(arrayList, this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter
    }
}