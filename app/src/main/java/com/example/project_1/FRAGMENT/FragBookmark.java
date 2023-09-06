package com.example.project_1.FRAGMENT;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
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
import com.example.project_1.DATABASE.FileDATABASE;
import com.example.project_1.DTO.AllFileDTO;
import com.example.project_1.DTO.FileDTO;
import com.example.project_1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragBookmark extends Fragment {
    LinearLayout layoutAZ,layoutZA;
    RecyclerView rc_bookmark;
    FileADAPTER adapter;
    ArrayList<AllFileDTO> mlistFile;
    ImageButton sortFile;
    SearchView search_file;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_bookmark,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        list = FileADAPTER.guidata();
//        adapter = new FileADAPTER(getContext(),list,2);
        mlistFile = (ArrayList<AllFileDTO>) FileDATABASE.getInstance(getContext()).fileDAO().getListFile();
        adapter = new FileADAPTER(getContext(),mlistFile,2);
        adapter.setData(mlistFile);

        rc_bookmark = view.findViewById(R.id.rc_bookmark);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_bookmark.setLayoutManager(linearLayoutManager);
        rc_bookmark.setAdapter(adapter);

        sortFile = view.findViewById(R.id.sort_file_bm);
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

                check_az.setVisibility(View.INVISIBLE);
                check_za.setVisibility(View.INVISIBLE);

                tv_az.setTextColor(Color.parseColor("#FF000000"));
                tv_za.setTextColor(Color.parseColor("#FF000000"));

                layoutAZ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check_az.setVisibility(View.VISIBLE);
                        check_za.setVisibility(View.INVISIBLE);

                        iv_az.setImageResource(R.drawable.sort_az);
                        tv_az.setTextColor(Color.parseColor("#217346"));

                        iv_za.setImageResource(R.drawable.sort_az_white);
                        tv_za.setTextColor(Color.parseColor("#FF000000"));

                        Collections.sort(mlistFile, new Comparator<AllFileDTO>() {
                            @Override
                            public int compare(AllFileDTO o1, AllFileDTO o2) {
                                return o1.getTen().compareTo(o2.getTen());
                            }
                        });
                        Toast.makeText(getContext(), "@string/toast_sortAZ", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                });

                layoutZA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check_az.setVisibility(View.INVISIBLE);
                        check_za.setVisibility(View.VISIBLE);

                        iv_za.setImageResource(R.drawable.sort_az);
                        tv_za.setTextColor(Color.parseColor("#217346"));

                        iv_az.setImageResource(R.drawable.sort_az_white);
                        tv_az.setTextColor(Color.parseColor("#FF000000"));

                        Collections.sort(mlistFile, new NameComparator());
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getContext(), "@string/toast_sortZA", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        search_file = view.findViewById(R.id.search_file_bm);
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
    }
    public class NameComparator implements Comparator<AllFileDTO> {
        @Override
        public int compare(AllFileDTO o1, AllFileDTO o2) {
            return o2.getTen().compareTo(o1.getTen());
        }
    }
}
