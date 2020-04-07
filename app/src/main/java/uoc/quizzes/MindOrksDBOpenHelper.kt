package uoc.quizzes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MindOrksDBOpenHelper(var context: Context,
                           factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {

        /* Borro la tabla si existe para que no se acumulen los valores*/

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)

        /* Creo la nueva tabla*/

        val query = ( "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITULO + " INTEGER," +
                COLUMN_IMAGEN + " STRING," +
                COLUMN_RESPUESTAOK + " INTEGER," +
                COLUMN_RESPUESTA1 + " STRING," +
                COLUMN_RESPUESTA2 + " STRING" + ")")

        db.execSQL(query)

    }
 override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        /* onCreate(db) */
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun addQuestion(name: Questions) {
        val values = ContentValues()
        values.put(COLUMN_TITULO, name.Titulo)
        values.put(COLUMN_IMAGEN, name.Imagen)
        values.put(COLUMN_RESPUESTAOK, name.Respuestaok)
        values.put(COLUMN_RESPUESTA1, name.Respuesta1)
        values.put(COLUMN_RESPUESTA2,name.Respuesta2)
        val db = this.writableDatabase
        val result = db.insert(TABLE_NAME, null, values)

        /*if (result == (-1).toLong())
            Toast.makeText(context,"FAILED",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"SUCCESS",Toast.LENGTH_SHORT).show() */
        db.close()

    }
    fun getAllQuestions(): MutableList<Questions> {
        var list: MutableList<Questions> = ArrayList()
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

        if (result.moveToFirst()) {
            for (i in 0..2) {
                var questions = Questions()
                questions.id = result.getString(result.getColumnIndex(COLUMN_ID)).toInt()
                questions.Titulo = result.getString(result.getColumnIndex(COLUMN_TITULO)).toInt()
                questions.Imagen = result.getString(result.getColumnIndex(COLUMN_IMAGEN))
                questions.Respuestaok = result.getString(result.getColumnIndex(COLUMN_RESPUESTAOK)).toInt()
                questions.Respuesta1 = result.getString(result.getColumnIndex(COLUMN_RESPUESTA1))
                questions.Respuesta2 = result.getString(result.getColumnIndex(COLUMN_RESPUESTA2))
                list.add(i,questions)
                result.moveToNext()
            }
        }

        result.close()
        db.close()
        return  list
    }

    fun deletedata() {
        val db = this.writableDatabase

        for (i in 0..1000) {
            db.delete(TABLE_NAME, COLUMN_ID+"=?", arrayOf(i.toString()))

        }
        db.close()
    }

    fun isEmpty(): Boolean {
        val db = this.readableDatabase
        val NoRows:Int = DatabaseUtils.queryNumEntries(db,"Questions").toInt()
        db.close()
        return NoRows==0
    }

    fun isEmpty2(): Boolean {
        val db = this.readableDatabase
        val count = "SELECT count(*) FROM " + TABLE_NAME
        val result = db.rawQuery(count, null)
        return result.moveToFirst()
    }
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "mindorksName.db"
        val TABLE_NAME = "questions"
        val COLUMN_ID = "_id"
        val COLUMN_TITULO = "titulo"
        val COLUMN_IMAGEN= "imagen"
        val COLUMN_RESPUESTAOK= "respuestaok"
        val COLUMN_RESPUESTA1 = "respuesta1"
        val COLUMN_RESPUESTA2 = "respuesta2"
    }
}