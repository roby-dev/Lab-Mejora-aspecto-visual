package com.example.recyclerview;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.text.LineBreaker;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonaViewHolder> {

    List<Persona> personas;
    Context context;







    public RVAdapter(List<Persona> personas,Context context){
        this.personas = personas;
        this.context= context;

    }


    @NonNull
    @Override
    public RVAdapter.PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.persona,parent,false);
        PersonaViewHolder personaViewHolder = new PersonaViewHolder(view,personas);
        return personaViewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull RVAdapter.PersonaViewHolder holder, int position) {
        holder.nombrePersona.setText(personas.get(position).nombre);
        holder.edadPersona.setText(personas.get(position).edad);
        holder.botonDetalle.setId(personas.get(position).botonId);
        holder.descripcion.setText(personas.get(position).descripcion);
        holder.link.setText(personas.get(position).link);
        Drawable original = context.getResources().getDrawable(personas.get(position).foto,null);

        Bitmap originalBitmap = ((BitmapDrawable)original).getBitmap();
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(),originalBitmap);
        roundedBitmapDrawable.setCornerRadius(originalBitmap.getHeight());

        holder.fotoPersona.setImageDrawable(roundedBitmapDrawable);

        holder.setOnClickListeners();


    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cardView;
        TextView nombrePersona;
        TextView edadPersona;
        ImageView fotoPersona;
        Button botonDetalle;
        Dialog dialog;
        TextView descripcion;
        TextView link;


        public PersonaViewHolder(@NonNull final View itemView, final List<Persona> personas) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cb);
            nombrePersona = (TextView) itemView.findViewById(R.id.nombrePersona);
            edadPersona = (TextView) itemView.findViewById(R.id.edadPersona);
            fotoPersona = (ImageView) itemView.findViewById(R.id.fotoPersona);
            botonDetalle = (Button) itemView.findViewById(R.id.btnDetalle);
            descripcion = (TextView) itemView.findViewById(R.id.txtdetalle);
            link = (TextView) itemView.findViewById(R.id.txtl);

        }

        public void setOnClickListeners() {
            botonDetalle.setOnClickListener(this);
        }

        public void onClick(View v) {
            crearDialog(v,nombrePersona,descripcion,link);
        }

        private void crearDialog(final View itemView, TextView nombrePersona, TextView descripcion, TextView link) {
            final String url = link.getText().toString();
            int width = (int)(itemView.getResources().getDisplayMetrics().widthPixels*0.90);
            int height = (int)(itemView.getResources().getDisplayMetrics().heightPixels*0.50);
            dialog = new Dialog(itemView.getContext());
            dialog.setContentView(R.layout.popup);
            dialog.setTitle(nombrePersona.getText().toString());
            TextView text = (TextView) dialog.findViewById(R.id.txtTitulo);
            TextView txtDescripcion = (TextView) dialog.findViewById(R.id.txtDescripcion);
            txtDescripcion.setText(descripcion.getText().toString());
            text.setText(nombrePersona.getText().toString());
            Button regresar = (Button)dialog.findViewById(R.id.btnRegresar);
            regresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    itemView.getContext().startActivity(intent);
                }
            });
            //dialog.getWindow().setLayout(width,);
            txtDescripcion.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            dialog.show();
        }
    }
}
