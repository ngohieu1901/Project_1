package com.example.project_1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_1.DTO.AllFileDTO;
import com.example.project_1.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.File;

public class PdfViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_pdf));
        }
        PDFView pdfView = findViewById(R.id.pdf_view);
        ImageButton btn_back = findViewById(R.id.btn_back);
        TextView tv_name = findViewById(R.id.tv_name);

        String name = getIntent().getStringExtra("name");
        tv_name.setText(name);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PdfViewActivity.this,MainManageFile.class));
                finish();
            }
        });

        String path = getIntent().getStringExtra("path");
        try {
            File file = new File(path);
            if (file.exists()){
                pdfView.fromFile(file)
                        .swipeHorizontal(false)
                        .pageFitPolicy(FitPolicy.WIDTH)
                        .enableAntialiasing(true)
                        .spacing(15)
                        .load();

            }else {
                Toast.makeText(this, this.getString(R.string.toast_exists), Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}