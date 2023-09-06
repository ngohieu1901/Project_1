package com.example.project_1.ADAPTER;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
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

import com.example.project_1.DAO.AllFileDAO;
import com.example.project_1.DATABASE.FileDATABASE;
import com.example.project_1.DTO.AllFileDTO;
import com.example.project_1.DTO.FileDTO;
import com.example.project_1.FRAGMENT.FragHome;
import com.example.project_1.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileADAPTER extends RecyclerView.Adapter<FileADAPTER.ViewHolder> implements Filterable {
    Context context;
//    ArrayList<FileDTO> list_file_old;
    ArrayList<AllFileDTO> list, list_file_old;
    public int trang = 0;
    FragHome fragHome;


    public FileADAPTER(Context context, ArrayList<AllFileDTO> list, int trang) {
        this.context = context;
        this.list = list;
        this.list_file_old = list;
        this.trang = trang;
        fragHome = new FragHome();
    }

    public void setData(ArrayList<AllFileDTO> listFile){
        this.list = listFile;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        final FileDTO[] fileDTO = {list.get(position)};
//        holder.img_icon_file.setImageResource(fileDTO[0].getHinh());
//        holder.tv_ten_file.setText(fileDTO[0].getTen());
//        holder.tv_ngay.setText(fileDTO[0].getNgay());
            final AllFileDTO allFileDTO = list.get(position);
            holder.img_icon_file.setImageResource(allFileDTO.getHinh());
            holder.tv_ten_file.setText(allFileDTO.getTen());
            holder.tv_ngay.setText(allFileDTO.getNgay());

            if (allFileDTO.getBookmark() == 0) {
                holder.bookmark_file.setImageResource(R.drawable.star);
            } else {
                holder.bookmark_file.setImageResource(R.drawable.star_gold);
            }

        holder.menu_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(holder.menu_custom);
            }

            private void showPopupMenu(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.rename) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                            View v = inflater.inflate(R.layout.dialog_rename, null);
                            builder.setView(v);
                            Dialog dialog = builder.create();
                            dialog.show();

                            EditText ed_ten = v.findViewById(R.id.ed_ten);
                            Button btn_cancel = v.findViewById(R.id.btn_cancel);
                            Button btn_agree = v.findViewById(R.id.btn_agree);
//                          final FileDTO[] fileDTO = {list.get(holder.getAdapterPosition())};
//                          ed_ten.setText(fileDTO[0].getTen());
                            ed_ten.setText(allFileDTO.getTen());
                            btn_agree.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(trang==1){
                                        suaHome();
                                    }else {
                                        suaBookmark();
                                    }
                                }
                                public void updateFile(){
                                    FileDATABASE.getInstance(context).fileDAO().updateFile(allFileDTO);
                                }

                                private void suaHome(){
                                    allFileDTO.setTen(ed_ten.getText().toString());
//                                    fileDTO[0].setTen(ed_ten.getText().toString());
//                                     luudata(list);
                                    list.set(position,allFileDTO);
                                    notifyItemChanged(position);
                                    updateFile();
                                    loadFile();
                                    dialog.dismiss();
                                    Toast.makeText(context, context.getString(R.string.toast_rename), Toast.LENGTH_SHORT).show();
                                }

                                private void suaBookmark(){
//                                    ArrayList<FileDTO> listMain = doc();
//                                    fileDTO[0] = list.get(position);
//                                    String ten = fileDTO[0].getTen();
//                                    FileDTO fileDTO2 = new FileDTO();
//                                    fileDTO2.setTen(ten);
//                                    fileDTO[0].setTen(ed_ten.getText().toString());
//                                    listMain.set(check(listMain,fileDTO2),fileDTO[0]);
//                                    luudata(listMain);
                                    allFileDTO.setTen(ed_ten.getText().toString());
                                    updateFile();
                                    loadFile();
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                    Toast.makeText(context, context.getString(R.string.toast_rename), Toast.LENGTH_SHORT).show();
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
//                            fileDTO[0].setBookMarl(1);
                            allFileDTO.setBookmark(1);
                            holder.bookmark_file.setImageResource(R.drawable.star_gold);
                            notifyDataSetChanged();
//                          luudata(list);
                            return true;
                        } else if (item.getItemId() == R.id.delete) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                            View v = inflater.inflate(R.layout.dialog_delete, null);
                            builder.setView(v);
                            Dialog dialog = builder.create();
                            dialog.show();

                            Button btn_delete = v.findViewById(R.id.btn_delete);
                            Button btn_cancel = v.findViewById(R.id.btn_cancel);
                            btn_delete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(trang==1){
                                        xoaHome();
                                    }else {
                                        xoaBookmark();
                                    }
                                }
                                public void deleteFile(){
                                    FileDATABASE.getInstance(context).fileDAO().deleteFile(allFileDTO);
                                }

                                private void xoaHome() {
                                    list.remove(holder.getAdapterPosition());
                                    notifyDataSetChanged();
//                                  luudata(list);
                                    Toast.makeText(context, context.getString(R.string.toast_delete), Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }

                                private void xoaBookmark() {
//                                    ArrayList<FileDTO> listMain = doc();
//                                    fileDTO[0] = list.get(position);
//                                    list.remove(position);
//                                    listMain.remove(check(listMain,fileDTO[0]));
//                                    luudata(listMain);
                                    deleteFile();
                                    loadFile();
                                    notifyDataSetChanged();
                                    Toast.makeText(context, context.getString(R.string.toast_delete), Toast.LENGTH_SHORT).show();
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
                        } else {
                            return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        holder.bookmark_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fileDTO[0] = list.get(position);
//                int so = fileDTO[0].getBookMarl();
                int so = allFileDTO.getBookmark();
                if (trang == 1) {
                    xuLyChonHOME(allFileDTO, so);
//                    xuLyChonHOME(fileDTO[0], so);
                    Toast.makeText(context, context.getString(R.string.toast_bookmark), Toast.LENGTH_SHORT).show();
                } else {
//                    xuLyChonBOOKMARK(fileDTO[0], so);
                    xuLyChonBOOKMARK(allFileDTO,so);
                    Toast.makeText(context, context.getString(R.string.toast_bookmark), Toast.LENGTH_SHORT).show();
                }
                Log.e("OK", "Check " + position);
            }

            public void addFile(){
                int hinh = list.get(position).getHinh();
                String ten = list.get(position).getTen();
                String ngay = list.get(position).getNgay();
                int bookmark = list.get(position).getBookmark();

                AllFileDTO fileDTO = new AllFileDTO(hinh,ten,ngay,bookmark);
                FileDATABASE.getInstance(context).fileDAO().insertFile(fileDTO);
            }

            public void xuLyChonHOME(AllFileDTO dto, int so) {
//                if (so == 0) {
//                    dto.setBookMarl(1);
//                    list.set(position, dto);
//                    holder.bookmark_file.setImageResource(R.drawable.star_gold);
//                    so = 1;
//                    luudata(list);
//                } else {
//                    dto.setBookMarl(0);
//                    list.set(position, dto);
//                    holder.bookmark_file.setImageResource(R.drawable.star);
//                    so = 0;
//                    luudata(list);
//                }
                if(so == 0){
                    dto.setBookmark(1);
                    list.set(position,dto);
                    addFile();
                    loadFile();
                    holder.bookmark_file.setImageResource(R.drawable.star_gold);
                    notifyDataSetChanged();
                }else{
                    dto.setBookmark(0);
                    list.set(position,dto);
                    FileDATABASE.getInstance(context).fileDAO().deleteFile(allFileDTO);
                    loadFile();
                    holder.bookmark_file.setImageResource(R.drawable.star);
                    notifyDataSetChanged();
                }
            }
            public void xuLyChonBOOKMARK(AllFileDTO dto, int so) {
//                ArrayList<FileDTO> list1 = doc();
//                if (so == 0) {
//                    dto.setBookMarl(1);
//                    list1.set(check(list1, dto), dto);
//                    holder.bookmark_file.setImageResource(R.drawable.star_gold);
//                    so = 1;
//                    luudata(list1);
//                } else {
//                    dto.setBookMarl(0);
//                    list1.set(check(list1, dto), dto);
//                    list.remove(position);
//                    notifyDataSetChanged();
//                    holder.bookmark_file.setImageResource(R.drawable.star);
//                    so = 0;
//                    luudata(list1);
//                }
                dto.setBookmark(0);
                list.set(position,dto);
                FileDATABASE.getInstance(context).fileDAO().deleteFile(allFileDTO);
                loadFile();
                notifyDataSetChanged();
            }
        });
    }

    public void loadFile(){
        FileDATABASE.getInstance(context).fileDAO().getListFile();
        setData(list);
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
                if (strSearch.isEmpty()) {
                    list = list_file_old;
                } else {
                    ArrayList<AllFileDTO> list_new = new ArrayList<>();
                    for (AllFileDTO file : list_file_old) {
                        if (file.getTen().toLowerCase().contains(strSearch.toLowerCase())) {
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
                list = (ArrayList<AllFileDTO>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_icon_file;
        TextView tv_ten_file;
        TextView tv_ngay;
        ImageView bookmark_file;
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

//    public void luudata(ArrayList<AllFileDTO> list) {
//        try {
//            FileOutputStream fileOutputStream = ((Activity) context).openFileOutput("KEY_NAME", ((Activity) context).MODE_PRIVATE);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(list);
//            objectOutputStream.close();
//            fileOutputStream.close();
//        } catch (Exception e) {
//            Log.e(TAG, "luudata: ", e);
//        }
//    }
//
//    public ArrayList doc() {
//        ArrayList<FileDTO> list = new ArrayList<>();
//        try {
//            FileInputStream fileInputStream = ((Activity) context).openFileInput("KEY_NAME");
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            list = (ArrayList<FileDTO>) objectInputStream.readObject();
//            objectInputStream.close();
//            fileInputStream.close();
//        } catch (Exception e) {
//            Log.e(TAG, "doc: ", e);
//        }
//        return list;
//    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
    }

//    public static ArrayList<FileDTO> guidata() {
//        ArrayList<FileDTO> list_bookmark = new ArrayList<>();
//        for (FileDTO dto : list) {
//            if (dto.getBookMarl() == 1) {
//                list_bookmark.add(dto);
//            }
//        }
//        return list_bookmark;
//    }

//    private int check(ArrayList<FileDTO> list, FileDTO dto) {
//        int a = 0;
//        for (FileDTO d : list) {
//            if (dto.getTen().equals(d.getTen())) {
//                break;
//            }
//            Log.e(TAG, "check: d "+d.getTen() );
//            Log.e(TAG, "check: dto "+dto.getTen() );
//            a++;
//        }
//        return a;
//    }
}
