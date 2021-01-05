package com.example.biblioparislocal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioparislocal.models.ApiRecords;

import java.util.List;

public class BiblioAdapter extends ArrayAdapter<ApiRecords> {
    private int resId;


        public BiblioAdapter(@NonNull Context context, int resource, @NonNull List<ApiRecords> objects) {
            super(context, resource, objects);

            resId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            // déclaration du ViewHolder
            ViewHolder myViewHolder;

            if(convertView == null) {
                myViewHolder = new ViewHolder(); // instance
                // 1) chargement du layout R.layout.item_restaurant
                convertView = LayoutInflater.from(getContext()).inflate(resId, null);

                // 2) récupération des vues (élements graphiques)
                myViewHolder.textViewTitle = convertView.findViewById(R.id.textViewTitle);
                myViewHolder.textViewVoie = convertView.findViewById(R.id.textViewVoie);
                myViewHolder.textViewCp = convertView.findViewById(R.id.textViewCp);



                convertView.setTag(myViewHolder); // enregistrement du ViewHolder (qui contient le titre et la catégorie)
            } else {
                myViewHolder = (ViewHolder) convertView.getTag();
            }

            // 3) données (Bibliothèques)
            ApiRecords item = getItem(position);

            // 4) affichage (setText)
            myViewHolder.textViewTitle.setText(item.getFields().getLibelle1());
            myViewHolder.textViewVoie.setText(item.getFields().getVoie());
            myViewHolder.textViewCp.setText(item.getFields().getCp());


            return convertView;
        }

        class ViewHolder {
            TextView textViewTitle;
            TextView textViewVoie;
            TextView textViewCp;
        }

    }