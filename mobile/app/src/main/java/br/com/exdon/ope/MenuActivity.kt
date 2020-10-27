package br.com.exdon.ope

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.GREEN
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.actionbar.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.menu_lateral_cabecalho.*
import kotlinx.android.synthetic.main.navigation_menu.*
import kotlinx.android.synthetic.main.progress_bar.*


class MenuActivity : DebugActivity() {

    var refUser: DatabaseReference? = null
    var firebaseUser: FirebaseUser? = null


    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        this.generic_layout = layout_menu_lateral

        // acessar parametros da intnet
        // intent é um atributo herdado de Activity
        val args = intent.extras
        // recuperar o parâmetro do tipo String
       val nome = args?.getString("nome")

        // recuperar parâmetro simplificado
        //val numero = intent.getIntExtra("nome", 0)

        //Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        //Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()

        //mensagemInicial.text = "Bem vindo !"

        //var txtBtnExame = botaoExame.text.toString()
        //var txtBtnAgendamento = botaoAgendamento.text.toString()
        //var txtBtnContact = botaoContact.text.toString()

        firebaseUser = FirebaseAuth.getInstance().currentUser
        refUser = FirebaseDatabase.getInstance().reference.child("User").child(firebaseUser!!.uid)
        


        cardview_agendamento.setOnClickListener{
            var intent = Intent(this, NovoExameActivity::class.java)
            var titleOpcoes = Bundle()
            titleOpcoes.putString("title", "AGENDAMENTO")
            intent.putExtras(titleOpcoes)
            startActivity(intent)
        }
        cardview_unidades.setOnClickListener{
            var intent = Intent(this, UnidadeActivity::class.java)
            var titleOpcoes = Bundle()
            titleOpcoes.putString("title", "UNIDADES")
            intent.putExtras(titleOpcoes)
            startActivity(intent)
        }
        cardview_exames.setOnClickListener{
            var intent = Intent(this, ExamesActivity::class.java)
            var titleOpcoes = Bundle()
            titleOpcoes.putString("title", "EXAMES")
            intent.putExtras(titleOpcoes)
            startActivity(intent)
        }
        cardview_faleConosco.setOnClickListener{
            var intent = Intent(this, FaleConoscoActivity::class.java)
            var titleOpcoes = Bundle()
            titleOpcoes.putString("title", "FALE CONOSCO")
            intent.putExtras(titleOpcoes)
            startActivity(intent)
        }

        // Display username e profile picture
        /*refUser!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })*/
        //botaoSair.setOnClickListener {cliqueSair()}
        //botaoExame.setOnClickListener{
       //     var intent = Intent(this, TerceiraActivity::class.java)
        //    var titleOpcoes = Bundle()
       //     titleOpcoes.putString("title", "EXAMES")
        //    intent.putExtras(titleOpcoes)
       //     startActivity(intent)
       // }
        //botaoAgendamento.setOnClickListener{
           // var intent = Intent(this, TerceiraActivity::class.java)
        //    var titleOpcoes = Bundle()
        //    titleOpcoes.putString("title", "AGENDAMENTOS")
        //    intent.putExtras(titleOpcoes)
        //    startActivity(intent)
       // }
        //botaoContact.setOnClickListener{
        //    var intent = Intent(this, TerceiraActivity::class.java)
       //     var titleOpcoes = Bundle()
         //   titleOpcoes.putString("title", "FALE CONOSCO")
        //    intent.putExtras(titleOpcoes)
       //     startActivity(intent)
       // }


        // colocar toolbar
        setSupportActionBar(toolbar)


        // alterar título da ActionBar
        supportActionBar?.title = "Buscar"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        configuraMenuLateral()
    }


   class NetworkTask(var activity: MenuActivity) : AsyncTask<Void, Void, Void>() {

       var dialog = Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar)

       override fun onPreExecute() {
           val view = activity.layoutInflater.inflate(R.layout.progress_bar, null)
           dialog.setContentView(view)
           dialog.setCancelable(false)
           dialog.show()
           super.onPreExecute()
       }
       override fun doInBackground(vararg params: Void?): Void? {
           Thread.sleep(10000)
           return null
       }

       override fun onPostExecute(result: Void?) {
           super.onPostExecute(result)
           dialog.dismiss()
       }

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
            NetworkTask(this).execute()
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_adicionar) {
            openNextActivity()
            Toast.makeText(context, "Botão de adicionar", Toast.LENGTH_LONG).show()
            // botão up navigation
        } else if (id == android.R.id.home) {
            finish()
        } else if (id == R.id.action_novo) {
            val it = Intent(this, NovoExameActivity::class.java)
            startActivity(it)
        }
        return super.onOptionsItemSelected(item)
    }


}