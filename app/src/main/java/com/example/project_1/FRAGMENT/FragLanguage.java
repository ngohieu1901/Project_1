package com.example.project_1.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.ADAPTER.LanguageFragADAPTER;
import com.example.project_1.DTO.LanguageFragDTO;
import com.example.project_1.R;

import java.util.ArrayList;
import java.util.List;

public class FragLanguage extends Fragment {
    RecyclerView rc_lang;
    LanguageFragADAPTER adapter;
    List<LanguageFragDTO> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_language,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        list.add(new LanguageFragDTO("English"));
        list.add(new LanguageFragDTO("Korean"));
        list.add(new LanguageFragDTO("Japanese"));
        list.add(new LanguageFragDTO("French"));
        list.add(new LanguageFragDTO("Hindi"));
        list.add(new LanguageFragDTO("Portuguese"));
        list.add(new LanguageFragDTO("Spanish"));
        list.add(new LanguageFragDTO("Vietnamese"));
        list.add(new LanguageFragDTO("Phillipinese"));
        list.add(new LanguageFragDTO("German"));

        rc_lang = view.findViewById(R.id.rc_lang);
        adapter = new LanguageFragADAPTER(getContext(),list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        rc_lang.setLayoutManager(linearLayoutManager);
        rc_lang.setAdapter(adapter);

        ImageView iv_back = view .findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragSetting fragSetting = new FragSetting();
                fm.beginTransaction().replace(R.id.frag_container_file,fragSetting).commit();
            }
        });
    }
}
