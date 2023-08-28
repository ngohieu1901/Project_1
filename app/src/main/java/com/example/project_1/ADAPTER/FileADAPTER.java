package com.example.project_1.ADAPTER;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.DTO.FileDTO;
import com.example.project_1.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FileADAPTER extends RecyclerView.Adapter<FileADAPTER.ViewHolder> implements Filterable {
    Context context;
    ArrayList<FileDTO> list;
    ArrayList<FileDTO> list_file_old;
    static ArrayList<FileDTO> list_bookmark;

//    boolean isButtonSelected = false;



    public FileADAPTER(Context context, ArrayList<FileDTO> list) {
        this.context = context;
        this.list = list;
        this.list_file_old = list;
        list_bookmark = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FileDTO fileDTO = list.get(position);

        holder.img_icon_file.setImageResource(list.get(position).getHinh());
        holder.tv_ten_file.setText(list.get(position).getTen());
        holder.tv_ngay.setText(list.get(position).getNgay());

        holder.menu_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(holder.menu_custom);
            }
            private void showPopupMenu(View view){
                Context wrapper = new ContextThemeWrapper(context, R.style.PopupMenuStyle);
                PopupMenu popupMenu = new PopupMenu(wrapper,view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.rename) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                            View v  = inflater.inflate(R.layout.dialog_rename,null);
                            builder.setView(v);
                            Dialog dialog = builder.create();
                            dialog.show();

                            EditText ed_ten = v.findViewById(R.id.ed_ten);
                            Button btn_cancel = v.findViewById(R.id.btn_cancel);
                            Button btn_agree = v.findViewById(R.id.btn_agree);
                            FileDTO fileDTO = list.get(holder.getAdapterPosition());

                            ed_ten.setText(fileDTO.getTen());
                            btn_agree.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    fileDTO.setTen(ed_ten.getText().toString());
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                }
                            });

                            btn_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            return true;
                        } else if (item.getItemId() == R.id.bookmark) {
                            list_bookmark.add(fileDTO);
                            holder.bookmark_file.setBackgroundResource(R.drawable.star_gold);
                            return true;
                        } else if (item.getItemId() == R.id.delete) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                            View v  = inflater.inflate(R.layout.dialog_delete,null);
                            builder.setView(v);
                            Dialog dialog = builder.create();
                            dialog.show();

                            Button btn_delete = v.findViewById(R.id.btn_delete);
                            Button btn_cancel = v.findViewById(R.id.btn_cancel);
                            btn_delete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    list.remove(holder.getAdapterPosition());
                                    notifyDataSetChanged();
                                    Toast.makeText(wrapper, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            });

                            btn_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            return true;
                        }else {
                            return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
//bookmark
        if(list_bookmark.contains(fileDTO)){
            holder.bookmark_file.setBackgroundResource(R.drawable.star_gold);
        }else{
            holder.bookmark_file.setBackgroundResource(R.drawable.star);
        }
        holder.bookmark_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if (list_bookmark.contains(fileDTO)){
                      list_bookmark.remove(fileDTO);
                      holder.bookmark_file.setBackgroundResource(R.drawable.star);
                  }else{
                      list_bookmark.add(fileDTO);
                      holder.bookmark_file.setBackgroundResource(R.drawable.star_gold);
                  }
            }
        });
    }

    public static ArrayList<FileDTO> getList_bookmark(){
        return list_bookmark;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String strSearch = constraint.toString();
                    if (strSearch.isEmpty()){
                        list = list_file_old;
                    }else {
                        ArrayList<FileDTO> list_new = new ArrayList<>();
                        for(FileDTO file : list_file_old){
                            if (file.getTen().toLowerCase().contains(strSearch.toLowerCase())){
                                list_new.add(file);
                            }
                        }
                        list = list_new;
                    }
                    FilterResults results = new FilterResults();
                    results.values = list;
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    list = (ArrayList<FileDTO>) results.values;
                    notifyDataSetChanged();
                }
            };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_icon_file;
        TextView tv_ten_file;
        TextView tv_ngay;
        ImageButton bookmark_file;
        ImageButton menu_custom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_icon_file = itemView.findViewById(R.id.img_icon);
            tv_ten_file = itemView.findViewById(R.id.tv_ten_file);
            tv_ngay = itemView.findViewById(R.id.tv_ngay);
            bookmark_file = itemView.findViewById(R.id.bookmark_file);
            menu_custom = itemView.findViewById(R.id.menu_custom);
        }
    }
}
