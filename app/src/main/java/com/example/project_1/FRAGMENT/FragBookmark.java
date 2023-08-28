package com.example.project_1.FRAGMENT;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.ADAPTER.FileADAPTER;
import com.example.project_1.DTO.FileDTO;
import com.example.project_1.R;

import java.util.ArrayList;
import java.util.List;

public class FragBookmark extends Fragment {
    LinearLayout layoutAZ,layoutZA;
    RecyclerView rc_bookmark;
    FileADAPTER adapter;
    ArrayList<FileDTO> list;
    ImageButton sortFile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_bookmark,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = FileADAPTER.getList_bookmark();
        adapter = new FileADAPTER(getContext(),list);

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

                layoutAZ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Sort AZ", Toast.LENGTH_SHORT).show();
                    }
                });

                layoutZA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Sort ZA", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
