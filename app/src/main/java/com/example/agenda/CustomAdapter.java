package com.example.agenda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList contato, nome, email, celular;

    CustomAdapter(Activity activity, Context context, ArrayList contato, ArrayList nome, ArrayList email, ArrayList celular){
        this.activity = activity;
        this.context = context;
        this.contato = contato;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.contato_txt.setText(String.valueOf(contato.get(position)));
        holder.nome_txt.setText(String.valueOf(nome.get(position)));
        holder.email_txt.setText(String.valueOf(email.get(position)));
        holder.celular_txt.setText(String.valueOf(celular.get(position)));

        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("contato", String.valueOf(contato.get(position)));
            intent.putExtra("nome", String.valueOf(nome.get(position)));
            intent.putExtra("email", String.valueOf(email.get(position)));
            intent.putExtra("celular", String.valueOf(celular.get(position)));
            activity.startActivityForResult(intent, 1);
        });


    }

    @Override
    public int getItemCount() {
        return contato.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contato_txt, nome_txt, email_txt, celular_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contato_txt = itemView.findViewById(R.id.contato_txt);
            nome_txt = itemView.findViewById(R.id.nome_txt);
            email_txt = itemView.findViewById(R.id.email_txt);
            celular_txt = itemView.findViewById(R.id.celular_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}