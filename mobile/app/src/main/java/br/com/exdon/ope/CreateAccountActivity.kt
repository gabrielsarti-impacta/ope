package br.com.exdon.ope

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.ProgressBar
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateAccountActivity : AppCompatActivity() {

    //Elementos da Interface do usuario

    private var getFirstName: EditText? = null
    private var getLastName: EditText? = null
    private var getEmail: EditText? = null
    private var getPasswordRegister: EditText? = null
    private var btnRegister: Button? = null
    private var mProgressBar: ProgressDialog? = null

    //Referencias ao Banco de dados

    private var mDataBaseReference: DatabaseReference? = null
    private var mDataBase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private val TAG = "CreateAccountActivity"

    //Variaveis Globais

    private var firstName: String? = null
    private var lastName: String? = null
    private var email: String? = null
    private var passwordRegister: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initialise()
    }
    private fun initialise() {
        getFirstName = findViewById(R.id.first_name) as EditText
        getLastName = findViewById(R.id.last_name) as EditText
        getEmail = findViewById(R.id.email) as EditText
        getPasswordRegister = findViewById(R.id.password_register) as EditText
        btnRegister = findViewById(R.id.btn_register) as Button
        mProgressBar = ProgressDialog(this)

        mDataBase = FirebaseDatabase.getInstance()
        mDataBaseReference = mDataBase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()

        btnRegister!!.setOnClickListener { createNewAccount()}
    }

    private fun createNewAccount() {
        firstName = getFirstName?.text.toString()
        lastName = getLastName?.text.toString()
        email = getEmail?.text.toString()
        passwordRegister = getPasswordRegister?.text.toString()

        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(passwordRegister)) {
            Toast.makeText(this, "Informações preenchidas corretamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Entre com mais Detalhes", Toast.LENGTH_SHORT).show()
        }
        mProgressBar!!.setMessage("Registrando Usuário...")
        mProgressBar!!.show()

        mAuth!!.createUserWithEmailAndPassword(email!!, passwordRegister!!).addOnCompleteListener(this) { task ->
            mProgressBar!!.hide()

            if (task.isSuccessful) {
                Log.d(TAG, "CreateUserWithEmail:Success")

                val userId = mAuth!!.currentUser!!.uid

                //verifica se o usuario confirmou o email
                verifyEmail();

                val currentUserDb = mDataBaseReference!!.child(userId)
                currentUserDb.child("firstName").setValue(firstName)
                currentUserDb.child("lastName").setValue(lastName)

                //atualiza as informações no banco de dados
                updateUserInfoandUi()
            } else {
                Log.w(TAG, "CreateUserWithEmail:Failure", task.exception)
                Toast.makeText(this@CreateAccountActivity, "A autenticação falhou", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUserInfoandUi() {
        //inicia uma nova atividade
        val intent = Intent(this@CreateAccountActivity, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser;
        mUser!!.sendEmailVerification().addOnCompleteListener(this) {
            task ->
            if (task.isSuccessful) {
                Toast.makeText(this@CreateAccountActivity, "E-mail de verificação enviado para" + mUser.getEmail(),
                    Toast.LENGTH_SHORT).show()
            }else {
                Log.e(TAG, "SendEmailVerification", task.exception)
                Toast.makeText(this@CreateAccountActivity, "Falha no envio de e-mail de verificação!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}