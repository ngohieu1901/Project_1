package com.example.project_1.ADAPTER;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.DTO.LanguageDTO;
import com.example.project_1.R;

import java.util.ArrayList;

public class LanguageADAPTER extends RecyclerView.Adapter<LanguageADAPTER.ViewHolder> {
    Context context;
    ArrayList<LanguageDTO> list_lang;
    int selectedPosition = -1;
    int row_index = -1;

    public LanguageADAPTER(Context context, ArrayList<LanguageDTO> list_lang) {
        this.context = context;
        this.list_lang = list_lang;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.img_hinh.setImageResource(list_lang.get(position).getHinh());
        holder.tv_ten.setText(list_lang.get(position).getTen());

        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
        holder.radioButton.setChecked(position == selectedPosition);
        if(row_index == position){
            holder.frameLayout.setBackgroundColor(Color.parseColor("#217346"));
            holder.tv_ten.setTextColor(Color.parseColor("#FFFFFF"));
        }else{
            holder.frameLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tv_ten.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return list_lang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;
        TextView tv_ten;
        ImageView img_hinh;
        CardView cardView;
        FrameLayout frameLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.rb_selected);
            tv_ten = itemView.findViewById(R.id.tv_ten);
            img_hinh = itemView.findViewById(R.id.img_hinh);
            cardView =itemView.findViewById(R.id.row_linear);
            frameLayout = itemView.findViewById(R.id.frame_1);
        }
    }
}
