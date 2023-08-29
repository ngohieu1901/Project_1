package com.example.project_1.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.DTO.LanguageFragDTO;
import com.example.project_1.R;

import java.util.ArrayList;
import java.util.List;

public class LanguageFragADAPTER extends RecyclerView.Adapter<LanguageFragADAPTER.ViewHolder>{
    Context context;
    List<LanguageFragDTO> list;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_ten.setText(list.get(position).getTen());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_ten;
        ImageView iv_check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ten = itemView.findViewById(R.id.tv_ten);
            iv_check = itemView.findViewById(R.id.iv_check);
        }
    }
}
