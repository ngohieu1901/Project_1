package com.example.project_1.FRAGMENT;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.project_1.BuildConfig;
import com.example.project_1.R;

public class FragSetting extends Fragment {
    LinearLayout layout_feedback, layout_lang, layout_share;
    boolean isSelected = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_setting,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layout_feedback = view.findViewById(R.id.layout_feedback);
        layout_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = getLayoutInflater().inflate(R.layout.dialog_feedback,null,false);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                Button btn_cancel = view.findViewById(R.id.btn_cancel);
                Button btn_send = view.findViewById(R.id.btn_send);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btn_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), getContext().getString(R.string.toast_sent), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        layout_lang = view.findViewById(R.id.layout_lang);
        layout_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragLanguage fragLanguage = new FragLanguage();
                fm.beginTransaction().replace(R.id.frag_container_file,fragLanguage).commit();
            }
        });

        layout_share = view.findViewById(R.id.layout_share);
        layout_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!isSelected) {
                        isSelected = true;
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "File Manager");
                        String shareMessage = "\n\n" + "File management application\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.microsoft.office.excel&pcampaignid=web_share" + BuildConfig.APPLICATION_ID + "\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "Choose one"));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isSelected = false;
                            }
                        }, 1000);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
