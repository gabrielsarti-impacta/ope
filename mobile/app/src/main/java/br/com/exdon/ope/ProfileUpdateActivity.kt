package br.com.exdon.ope

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.util.EventLogTags
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_profile_update.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.regex.Matcher


class ProfileUpdateActivity : DebugActivity() {


    class User(
        val firstName: String = "",
        val lastName: String = "",
        val email: String = ""
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_update)


        // calendar
        var c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONDAY)
        val day = c.get(Calendar.DAY_OF_MONTH)



        data_nasc_update.setOnClickListener {
            val dataEscolhida = DatePickerDialog(
                this,
                R.style.CalendarCustom,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // set to textView
                    data_nasc_update.setText("" + dayOfMonth + "/" + "${monthOfYear + 1}" + "/" + year)

                },
                year,
                month,
                day
            )
            // show dialog
            dataEscolhida.show()
        }

        // cep
        val cepText = findViewById<View>(R.id.cep_update) as EditText
        val cepTextString = cepText.text.toString()

        consulta_cep_icon.setOnClickListener{
            if(cep_update.text.toString().length == 8) {
                getCep(cepTextString)
            }else {
                Toast.makeText(this, "O CEP contém 8 digitos", Toast.LENGTH_SHORT).show()
            }
        }


        // msg Saudação com nome do usuario atual
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val rootRef = FirebaseDatabase.getInstance().reference
        val uidRef = rootRef.child("Users").child(uid)
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                Log.d(TAG, "HELLO" + user!!.firstName)
                first_name_update.text = Editable.Factory.getInstance().newEditable(user.firstName)
                last_name_update.text = Editable.Factory.getInstance().newEditable(user.lastName)
                email_update.text = Editable.Factory.getInstance().newEditable(user.email)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, databaseError.message) //Don't ignore errors!
            }
        }
        uidRef.addListenerForSingleValueEvent(valueEventListener)
    }

    // cep
    private fun getCep(cep: String){
        val url = "https://viacep.com.br/ws/$cep/json/"
        doAsync {
            val url = URL(url)
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = 7000
            val content = urlConnection.inputStream.bufferedReader().use(BufferedReader::readText)
            var json = JSONObject(content)
            uiThread {
                if(json.has("erro")){
                    Toast.makeText(this@ProfileUpdateActivity, "Erro no CEP", Toast.LENGTH_SHORT).show()
                }
                else{
                    val cep = json.getString("cep")
                    val logradouro = json.getString("logradouro")
                    val bairro = json.getString("bairro")
                    val cidade = json.getString("localidade")
                    val estado = json.getString("uf")
                    cep_update.text = Editable.Factory.getInstance().newEditable(cep)
                    logradouro_update.text = Editable.Factory.getInstance().newEditable(logradouro)
                    bairro_update.text = Editable.Factory.getInstance().newEditable(bairro)
                    cidade_update.text = Editable.Factory.getInstance().newEditable(cidade)
                    estado_update.text = Editable.Factory.getInstance().newEditable(estado)
                }
            }
        }
    }
}