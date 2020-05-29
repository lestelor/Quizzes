package uoc.quizzes.full

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main2.*
import uoc.quizzes.MindOrksDBOpenHelper
import uoc.quizzes.R

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        /* Recoge los inputs de la primera actividad */
        val Correcto: String = intent.getStringExtra("Correcto")
        var pregunta: String = intent.getStringExtra("Número pregunta")

        /*Si es ya se ha respondido correctamente la preguntas 0 a 2 se muestra el mensaje y se reinicia el contador
        En caso contrario se  le comunica si la respuesta es correcta o incorrecta y configura los textos del botón y textview consecuentemente*/
        if (pregunta.toInt() == 3) {
            pregunta = "0"
            button_a2.setText(R.string.Felicidades_bt)
            tv_2.setText(R.string.Felicidades_txt)
            consultaBBDD()
        } else if (Correcto == "true") {
            button_a2.setText(R.string.Next)
            tv_2.setText(R.string.Texto_resultado)
        } else {
            button_a2.setText(R.string.Try_again)
        }
        /* Se vuelve a la actividad 1 al presionar el botón devolviendo el número de pregunta */
        button_a2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Pregunta", pregunta)
            ContextCompat.startActivity(this, intent, Bundle())
        }

    }
    /* Para comprobar si se ha guardado la BBDD, se consulta y se devuelve la respuesta correcta de las 3 preguntas */
    private fun consultaBBDD() {
        val dbHandler = MindOrksDBOpenHelper(this, null)

        tv_22.text = getString(R.string.tv_22_txt)
        /*getString(R.string.tv_22_txt)*/

        var data = dbHandler.getAllQuestions()
        for (i in 0..data.size-1) {

            if (data.get(i).Respuestaok == 0) {
                tv_22.append(data.get(i).Respuesta1)
                tv_22.append("\n")
            } else {
                tv_22.append(data.get(i).Respuesta2)
                tv_22.append("\n")
            }


        }
    }
}
