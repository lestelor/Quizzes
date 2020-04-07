/* Main activity donde se pide al usuario que conteste tres preguntas */


package uoc.quizzes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        /* La variable quuestion hace referencia al número actual de pregunta que está respondiendo el usuario, de 1 a 3
        Su valor será 0 la primera vez que se entra al método OnCreate y luego se corresponde al valor que le pase la Main2Activity*/
        var question: Int
        var num_pregunta:String?=intent.getStringExtra("Pregunta")
        if (num_pregunta!=null) {
            question = num_pregunta.toInt()
        } else {
            question = 0
        }

        /* Se crea una clase que recoge todas las preguntas. Como salida se define una variable con el valor de la respuesta correcta */
        class Preguntas (val Titulo:Int , val imagen:String , respuestaok:Int, val respuestas: Array<String> = arrayOf()) {

            var respuestacorrecta: Int = respuestaok
        }
        /* Cada pregunta se define como un objeto de la clase Preguntas. Para el modo multiidioma se han definido las preguntas en strings.xml. Las 2 posibles respuestas se ponen en un array*/
        /* Luego se crea una lista con los diferentes objetos */
        val pregunta1 = Preguntas(R.string.Question1,"https://wallpapershome.com/images/pages/pic_h/5108.jpg", 1, arrayOf("Tibidabo","Sagrada Família"))
        val pregunta2 = Preguntas(R.string.Question2,"https://wallpapershome.com/images/pages/pic_h/2469.jpg", 0, arrayOf("Sponge Bob Square Pants","The Simpsons"))
        val pregunta3 = Preguntas(R.string.Question3,"https://wallpapershome.com/images/pages/pic_h/13549.jpg", 0, arrayOf("Jupiter","Saturn"))
        val setdepreguntas = listOf(pregunta1,pregunta2,pregunta3)



        /* Abro el SQL Helper */
        val dbHandler = MindOrksDBOpenHelper(this, null)
        dbHandler.isEmpty2()
        if (question==0 && dbHandler.isEmpty()) {
            /* Se define el objeto user de la clase Questions (en archivo aparte) */
            var questions = Questions(
                R.string.Question1,
                "https://wallpapershome.com/images/pages/pic_h/5108.jpg",
                1,
                "Tibidabo",
                "Sagrada Família"
            )
            /* FInalmente se añade el objeto a la BBDD */
            dbHandler.addQuestion(questions)
            /* Repito para el resto de objetos */
            questions = Questions(
                R.string.Question2,
                "https://wallpapershome.com/images/pages/pic_h/2469.jpg",
                0,
                "Sponge Bob Square Pants",
                "The Simpsons"
            )
            dbHandler.addQuestion(questions)
            questions = Questions(
                R.string.Question3,
                "https://wallpapershome.com/images/pages/pic_h/13549.jpg",
                0,
                "Jupiter",
                "Saturn"
            )
            dbHandler.addQuestion(questions)
        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Se rellena el cuestionario con el objeto de la lista correspondiente al número de pregunta
        Los botones se inicializan como unchecked*/
        radioButton1.isChecked=false
        radioButton2.isChecked=false
        tv1.text = (question+1).toString() + " / 3"
        tv2.text = getString(setdepreguntas[question].Titulo)
        radioButton1.text = setdepreguntas[question].respuestas[0]
        radioButton2.text= setdepreguntas[question].respuestas[1]
        Picasso.get().load(setdepreguntas[question].imagen).into(imageView)

        /* Cuando se aprieta el botón send:
        Si no hay selección, se lanza un toast
        Si el botón seleccionado (dentro del grupo) corresponde con la respuesta correcta, se suma 1 al número de respuesta y se ejecuta la función para cambiar de Activity*/

        button_send.setOnClickListener{
            if (!radioButton1.isChecked && !radioButton2.isChecked)
                Toast.makeText(this,R.string.SelectOption,Toast.LENGTH_SHORT).show()
             else if ((radioButton1.isChecked && setdepreguntas[question].respuestacorrecta == 0) || (radioButton2.isChecked && setdepreguntas[question].respuestacorrecta == 1)) {
                question += 1
                startActivity2(this,"true",question.toString())
            } else {
                startActivity2( this,"false",question.toString())
            }
            }

        }

    }
/* Crea la segunda activity y se le pasa como parámetros: 1) si la respuesta es correcta 2) el número de pregunta en curso */
    private fun startActivity2(estado:Context, ok: String, question:String) {
        val intent = Intent(estado, Main2Activity::class.java)
        intent.putExtra("Correcto",ok)
        intent.putExtra("Número pregunta", question)
        startActivity (estado,intent,Bundle())
    }







