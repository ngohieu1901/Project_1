package com.example.project_1.FRAGMENT;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.ADAPTER.FileADAPTER;
import com.example.project_1.DTO.FileDTO;
import com.example.project_1.MainActivity;
import com.example.project_1.MainManageFile;
import com.example.project_1.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragHome extends Fragment {
    RecyclerView rc_file;
    FileADAPTER adapter;
    ArrayList<FileDTO> list;
    SearchView search_file;
    ImageButton sortFile;
    LinearLayout layoutAZ,layoutZA;

    String TAG="ok";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc_file = view.findViewById(R.id.rc_file);

        list = new ArrayList<>();
        if(list.size()==0){
            list= (ArrayList<FileDTO>) doc();
        }
        if(list.size()==0){
            list.add(new FileDTO(R.drawable.txt_icon,"555.txt","2022-05-06, 12:22:30 PM",0));
            list.add(new FileDTO(R.drawable.pdf_icon,"996.pdf","2022-05-06, 12:22:30 PM",0));
            list.add(new FileDTO(R.drawable.csv_icon,"452.csv","2022-05-06, 12:22:30 PM",0));
            list.add(new FileDTO(R.drawable.excel_icon,"345.excel","2022-05-06, 12:22:30 PM",0));
            list.add(new FileDTO(R.drawable.ppt_icon,"123.ppt","2022-05-06, 12:22:30 PM",0));
            list.add(new FileDTO(R.drawable.txt_icon,"365.txt","2022-05-06, 12:22:30 PM",0));
            list.add(new FileDTO(R.drawable.excel_icon,"678.excel","2022-05-06, 12:22:30 PM",0));
            list.add(new FileDTO(R.drawable.csv_icon,"891.csv","2022-05-06, 12:22:30 PM",1));
            list.add(new FileDTO(R.drawable.word_icon,"773.docx","2022-05-06, 12:22:30 PM",0));
            list.add(new FileDTO(R.drawable.excel_icon,"678.excel","2022-05-06, 12:22:30 PM",0));
            list.add(new FileDTO(R.drawable.csv_icon,"891.csv","2022-05-06, 12:22:30 PM",1));
            list.add(new FileDTO(R.drawable.word_icon,"773.docx","2022-05-06, 12:22:30 PM",0));
            list.add(new FileDTO(R.drawable.excel_icon,"678.excel","2022-05-06, 12:22:30 PM",0));
            list.add(new FileDTO(R.drawable.csv_icon,"891.csv","2022-05-06, 12:22:30 PM",1));
            list.add(new FileDTO(R.drawable.word_icon,"773.docx","2022-05-06, 12:22:30 PM",0));
           luudata(list);
        }


        adapter = new FileADAPTER(getContext(),list,1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_file.setLayoutManager(linearLayoutManager);
        rc_file.setAdapter(adapter);

        search_file = view.findViewById(R.id.search_file_home);
        search_file.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        sortFile = view.findViewById(R.id.sort_file_home);
        sortFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = getLayoutInflater().inflate(R.layout.dialog_sort,null,false);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                 layoutAZ = view.findViewById(R.id.layout_az);
                 layoutZA = view.findViewById(R.id.layout_za);
                ImageView check_az= view.findViewById(R.id.check_az);
                ImageView check_za = view.findViewById(R.id.check_za);
                ImageView iv_az = view.findViewById(R.id.iv_az);
                ImageView iv_za = view.findViewById(R.id.iv_za);
                TextView tv_az = view.findViewById(R.id.tv_az);
                TextView tv_za = view.findViewById(R.id.tv_za);



                layoutAZ.setOnClickListener(new View.OnClickListener() {
                    private  boolean isChecked = false;
                    @Override
                    public void onClick(View v) {
                        if (isChecked){
                            tv_az.setTextColor(Color.parseColor("#217346"));
                            iv_az.setColorFilter(Color.parseColor("#217346"));
                        }else{
                            tv_az.setTextColor(Color.parseColor("#676767"));
                            iv_az.setColorFilter(Color.parseColor("#676767"));
                        }
                        isChecked =! isChecked;

                        Collections.sort(list, new Comparator<FileDTO>() {
                            @Override
                            public int compare(FileDTO o1, FileDTO o2) {
                                return o1.getTen().compareTo(o2.getTen());
                            }
                        });
                        Toast.makeText(getContext(), "Sort AZ", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                layoutZA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Collections.sort(list, new NameComparator());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Sort ZA", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }

        });
    }




    public class NameComparator implements Comparator<FileDTO> {
        @Override
        public int compare(FileDTO o1, FileDTO o2) {
            return o2.getTen().compareTo(o1.getTen());
        }
    }

    public void luudata( ArrayList<FileDTO> list) {

        try {
            FileOutputStream fileOutputStream = getActivity().openFileOutput("KEY_NAME",getActivity().MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        }catch (Exception e){
            Log.e(TAG, "luudata: ",e );
        }
    }
    public ArrayList doc(){
        ArrayList<FileDTO> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = getActivity().openFileInput("KEY_NAME");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list= (ArrayList<FileDTO>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }catch (Exception e){
            Log.e(TAG, "doc: ",e );
        }
        return list;
    }
}
