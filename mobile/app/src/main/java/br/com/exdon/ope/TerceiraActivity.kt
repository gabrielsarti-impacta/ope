package br.com.exdon.ope

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.actionbar.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu.view.*


class TerceiraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terceira)

        /*
        botaoExame.setOnClickListener{getTextExame()}
        botaoAgendamento.setOnClickListener{getTextAgendamento()}
        botaoContact.setOnClickListener{getTextContact()}*/

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val args = intent.extras
        val opcoes = args?.getString("title")

        supportActionBar?.title = opcoes


    }
}