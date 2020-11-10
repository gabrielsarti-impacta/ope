package br.com.exdon.ope

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "Bild_DB"
val TABLE_NAME = "tb_agendamento"
val COL_NOMEPACIENTE = "nome_paciente"
val COL_CONVENIO = "convenio"
val COL_EXAME = "exame"
val COL_UNIDADE = "unidade"
val COL_DATAEXAME = "data_exame"
val COL_HORAEXAME = "hora_exame"
val COL_OBSERVACAO = "observacao"
val COL_ID = "id_agendamento"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) { //context, DataBase Name, CursorFactory and DataBase Version
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE" + " " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NOMEPACIENTE + " VARCHAR(256)," +
                COL_CONVENIO + " VARCHAR(100)," +
                COL_EXAME + " VARCHAR(100)," +
                COL_UNIDADE + " VARCHAR(100)," +
                COL_DATAEXAME + " DATE," +
                COL_HORAEXAME + " TIME," +
                COL_OBSERVACAO + " VARCHAR(256) )"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(dadosAgendamento : DadosAgendamento) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NOMEPACIENTE, dadosAgendamento.nome_paciente)
        cv.put(COL_CONVENIO, dadosAgendamento.convenio)
        cv.put(COL_EXAME, dadosAgendamento.exame)
        cv.put(COL_DATAEXAME, dadosAgendamento.data_exame)
        cv.put(COL_HORAEXAME, dadosAgendamento.hora_exame)
        cv.put(COL_OBSERVACAO, dadosAgendamento.observacao)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Falha no Processo!", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Carregando Dados...", Toast.LENGTH_SHORT).show()
    }

}