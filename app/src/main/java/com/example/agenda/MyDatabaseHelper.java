package com.example.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Agenda.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_agenda";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "nome";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_CELULAR = "celular";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_CELULAR + " INTEGER);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void adicionarContato(String nome, String email, long celular){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, nome);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_CELULAR, celular);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Falha!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor lerDados(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void atualizarDados(String row_id, String nome, String email, String celular){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, nome);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_CELULAR, celular);

        long resultado = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(resultado == -1){
            Toast.makeText(context, "Falha!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Modificado com sucesso!", Toast.LENGTH_SHORT).show();
        }

    }

    void excluirLinha(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Falha ao deletar.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Sucesso ao deletar.", Toast.LENGTH_SHORT).show();
        }
    }

    void deletarTudo(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}