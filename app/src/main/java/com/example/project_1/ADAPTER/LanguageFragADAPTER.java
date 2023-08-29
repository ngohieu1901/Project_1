package com.example.project_1.ADAPTER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.DTO.LanguageFragDTO;
import com.example.project_1.R;

import java.util.ArrayList;
import java.util.List;

public class LanguageFragADAPTER extends RecyclerView.Adapter<LanguageFragADAPTER.ViewHolder>{
    Context context;
    List<LanguageFragDTO> list;
    int selectedItem = -1;

    public LanguageFragADAPTER(Context context, List<LanguageFragDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frag_lang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_ten.setText(list.get(position).getTen());
        if (position == selectedItem){
            holder.iv_check.setVisibility(View.VISIBLE);
        }else {
            holder.iv_check.setVisibility(View.GONE);
        }
        holder.card_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int preSelected = selectedItem;
                selectedItem = position;
                notifyItemChanged(preSelected);
                notifyItemChanged(selectedItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_ten;
        ImageView iv_check;
        CardView card_lang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ten = itemView.findViewById(R.id.tv_ten);
            iv_check = itemView.findViewById(R.id.iv_check);
            card_lang = itemView.findViewById(R.id.card_lang);

        }

    }
}
