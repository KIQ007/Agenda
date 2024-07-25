package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText nome_input, email_input, celular_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nome_input = findViewById(R.id.nome_input);
        email_input = findViewById(R.id.email_input);
        celular_input = findViewById(R.id.celular_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.adicionarContato(nome_input.getText().toString().trim(),
                        email_input.getText().toString().trim(),
                        Integer.parseInt(celular_input.getText().toString().trim()));
            }
        });
    }
}