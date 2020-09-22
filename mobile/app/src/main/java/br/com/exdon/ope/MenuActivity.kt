package br.com.exdon.ope

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.actionbar.*
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // acessar parametros da intnet
        // intent é um atributo herdado de Activity
        val args = intent.extras
        // recuperar o parâmetro do tipo String
       val nome = args?.getString("nome")

        // recuperar parâmetro simplificado
        //val numero = intent.getIntExtra("nome", 0)

        //Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        //Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()

        mensagemInicial.text = "Bem vindo !"

        var txtBtnExame = botaoExame.text.toString()
        var txtBtnAgendamento = botaoAgendamento.text.toString()
        var txtBtnContact = botaoContact.text.toString()


        botaoSair.setOnClickListener {cliqueSair()}
        botaoExame.setOnClickListener{
            var intent = Intent(this, TerceiraActivity::class.java)
            var titleOpcoes = Bundle()
            titleOpcoes.putString("title", "EXAMES")
            intent.putExtras(titleOpcoes)
            startActivity(intent)
        }
        botaoAgendamento.setOnClickListener{
            var intent = Intent(this, TerceiraActivity::class.java)
            var titleOpcoes = Bundle()
            titleOpcoes.putString("title", "AGENDAMENTOS")
            intent.putExtras(titleOpcoes)
            startActivity(intent)
        }
        botaoContact.setOnClickListener{
            var intent = Intent(this, TerceiraActivity::class.java)
            var titleOpcoes = Bundle()
            titleOpcoes.putString("title", "FALE CONOSCO")
            intent.putExtras(titleOpcoes)
            startActivity(intent)
        }


        // colocar toolbar
        setSupportActionBar(toolbar)


        // alterar título da ActionBar
        supportActionBar?.title = "Buscar"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun openNextActivity() {
        val intent = Intent(this, SegundaActivity::class.java)
        startActivity(intent)
    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result", "Saída do BrewerApp");
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {
                    // ação enquanto está digitando
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    // ação  quando terminou de buscar e enviou
                    return false
                }

            })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_adicionar) {
            openNextActivity()
            Toast.makeText(context, "Botão de adicionar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            openNextActivity()
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}