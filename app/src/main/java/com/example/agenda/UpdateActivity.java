package com.example.agenda;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nome_input, email_input, celular_input;
    Button update_button, delete_button;

    String contato, nome, email, celular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nome_input = findViewById(R.id.nome_input2);
        email_input = findViewById(R.id.email_input2);
        celular_input = findViewById(R.id.celular_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        processarDadosDaIntent();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nome);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                nome = nome_input.getText().toString().trim();
                email = email_input.getText().toString().trim();
                celular = celular_input.getText().toString().trim();
                myDB.atualizarDados(contato, nome, email, celular);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caixaDeConfirmacao();
            }
        });

    }

    void processarDadosDaIntent(){
        if(getIntent().hasExtra("contato") && getIntent().hasExtra("nome") &&
                getIntent().hasExtra("email") && getIntent().hasExtra("celular")){

            contato = getIntent().getStringExtra("contato");
            nome = getIntent().getStringExtra("nome");
            email = getIntent().getStringExtra("email");
            celular = getIntent().getStringExtra("celular");

            nome_input.setText(nome);
            email_input.setText(email);
            celular_input.setText(celular);
            Log.d("stev", nome+" "+email+" "+celular);
        }else{
            Toast.makeText(this, "Sem dados.", Toast.LENGTH_SHORT).show();
        }
    }

    void caixaDeConfirmacao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apagar " + nome + " ?");
        builder.setMessage("Você tem certeza de que deseja excluir? " + nome + " ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.excluirLinha(contato);
                finish();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}