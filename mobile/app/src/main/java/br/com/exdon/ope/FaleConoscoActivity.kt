package br.com.exdon.ope

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.actionbar.*
import kotlinx.android.synthetic.main.activity_fale_conosco.*
import kotlinx.android.synthetic.main.activity_fale_conosco.layout_menu_lateral
import kotlinx.android.synthetic.main.activity_menu.*


class FaleConoscoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fale_conosco)

        //this.generic_layout = layout_menu_lateral

        // colocar toolbar
        setSupportActionBar(toolbar)


        // alterar título da ActionBar
        supportActionBar?.title = "FALE CONOSCO"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //configuraMenuLateral()

        card_whatsapp.setOnClickListener{
            openWhatsapp()
        }

        card_phoneNumber.setOnClickListener{
            openCall()
        }

        card_email.setOnClickListener{
            val email = "contato@bilddiagnosticos.com.br"

            sendEmail(email)
        }
    }

    private fun openWhatsapp() {
        /*val numero = number_whatsapp.text.toString()
        val message = "Olá, estou precisando de ajuda!"

        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, message)
        sendIntent.type = "text/plain"
        sendIntent.setPackage("com.whatsapp")
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }*/
        /*try {
            val headerReceiver = "Teste" // Replace with your message.
            val bodyMessageFormal = "Teste 2" // Replace with your message.
            val whatsappcontain= headerReceiver + bodyMessageFormal
            val trimToNumner = "+5511960702182" //10 digit number
            val intent = Intent(Intent.ACTION_VIEW)
            intent.putExtra(Intent
            intent.data = Uri.parse("https://wa.me/$trimToNumner/?text=")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
        val number = "+5519998005464"
        val message = "Olá, gostaria de maiores informações sobre os serviços da Bild Diagnósticos!"
        //val url = "https://api.whatsapp.com/send?phone=$number"
        val url = String.format("https://api.whatsapp.com/send?phone=%s&text=%s", number, message)
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun openCall() {
        val numberContact = "01938094343"
        val uri = Uri.parse("tel:"+numberContact)
        val intent = Intent(Intent.ACTION_DIAL, uri)
        startActivity(intent)
    }

    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        // email is put as array because you may wanna send email to multiple emails so enter comma(,) separated emails, it will be stored array
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))


        try{
            startActivity(Intent.createChooser(intent, "Escolha uma opção de Email Client..."))
        }
        catch (e: Exception) {
            Toast.makeText(this, "Não foi possível concluir sua solicitação!", Toast.LENGTH_SHORT).show()
        }

    }
}