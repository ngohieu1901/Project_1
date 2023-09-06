package com.example.project_1.FRAGMENT;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.ADAPTER.FileADAPTER;
import com.example.project_1.DTO.AllFileDTO;
import com.example.project_1.DTO.FileDTO;
import com.example.project_1.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class FragHome extends Fragment {
    RecyclerView rc_file;
    FileADAPTER adapter;
//    FileUtil fileUtil;
//    ArrayList<FileDTO> list;
//    List<AllFileDTO> list;
    SearchView search_file;
    ImageButton sortFile;
    LinearLayout layoutAZ,layoutZA;

    ArrayList<AllFileDTO> list = loadFiles();

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

//        list = new ArrayList<>();
//        if(list.size()==0){
//            list= (ArrayList<FileDTO>) doc();
//        }
//        if(list.size()==0){
//            list.add(new FileDTO(R.drawable.txt_icon,"123.txt","2022-05-06, 12:22:30 PM",0));
//            list.add(new FileDTO(R.drawable.pdf_icon,"234.pdf","2022-05-06, 12:22:30 PM",0));
//            list.add(new FileDTO(R.drawable.csv_icon,"345.csv","2022-05-06, 12:22:30 PM",0));
//            list.add(new FileDTO(R.drawable.excel_icon,"456.excel","2022-05-06, 12:22:30 PM",0));
//            list.add(new FileDTO(R.drawable.ppt_icon,"567.ppt","2022-05-06, 12:22:30 PM",0));
//            list.add(new FileDTO(R.drawable.txt_icon,"678.txt","2022-05-06, 12:22:30 PM",0));
//            list.add(new FileDTO(R.drawable.excel_icon,"789.excel","2022-05-06, 12:22:30 PM",0));
//            list.add(new FileDTO(R.drawable.csv_icon,"321.csv","2022-05-06, 12:22:30 PM",0));
//            list.add(new FileDTO(R.drawable.word_icon,"432.docx","2022-05-06, 12:22:30 PM",0));
//           luudata(list);
//        }


        adapter = new FileADAPTER(getContext(), list,1);
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
            boolean isChecked = false;
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = getLayoutInflater().inflate(R.layout.dialog_sort,null,false);builder.setView(view);
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

                        Collections.sort(list, new Comparator<AllFileDTO>() {
                            @Override
                            public int compare(AllFileDTO o1, AllFileDTO o2) {
                                return o1.getTen().compareTo(o2.getTen());
                            }
                        });
                        Toast.makeText(getContext(), getContext().getString(R.string.toast_sortAZ), Toast.LENGTH_SHORT).show();
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

                        Collections.sort(list, new NameComparator());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), getContext().getString(R.string.toast_sortZA), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public class NameComparator implements Comparator<AllFileDTO> {
        @Override
        public int compare(AllFileDTO o1, AllFileDTO o2) {
            return o2.getTen().compareTo(o1.getTen());
        }
    }

//    public void luudata( ArrayList<AllFileDTO> list) {
//
//        try {
//            FileOutputStream fileOutputStream = getActivity().openFileOutput("KEY_NAME",getActivity().MODE_PRIVATE);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(list);
//            objectOutputStream.close();
//            fileOutputStream.close();
//        }catch (Exception e){
//            Log.e(TAG, "luudata: ",e );
//        }
//    }
//    public ArrayList doc(){
//        ArrayList<AllFileDTO> list = new ArrayList<>();
//        try {
//            FileInputStream fileInputStream = getActivity().openFileInput("KEY_NAME");
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            list= (ArrayList<AllFileDTO>) objectInputStream.readObject();
//            objectInputStream.close();
//            fileInputStream.close();
//        }catch (Exception e){
//            Log.e(TAG, "doc: ",e );
//        }
//        return list;
//    }
private ArrayList<AllFileDTO> loadFiles() {

    ArrayList<AllFileDTO> list = new ArrayList<>();

    String path = Environment.getExternalStorageDirectory().toString()+"/File/";
    Log.d("Files", "Path: " + path);
    File directory = new File(path);
    File[] files = directory.listFiles();
    Log.d("Files", "Size: " + files.length);
    if (files != null) {
        for (File file : files) {
            if (file.isFile() &&
                    (file.getName().endsWith(".pptx") || file.getName().endsWith(".docx") || file.getName().endsWith(".txt"))) {
                String filePath = file.getAbsolutePath();
                String fileName = file.getName();
                String dateModified = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file.lastModified()));
                // Thêm một đối tượng FileItem vào danh sách
                if (file.getName().endsWith(".pptx")) {
                    list.add(new AllFileDTO(R.drawable.pdf_icon,fileName,dateModified,0));
                } else if (file.getName().endsWith(".docx")) {
                    list.add(new AllFileDTO(R.drawable.word_icon,fileName,dateModified,0));
                } else if (file.getName().endsWith(".txt")) {
                    list.add(new AllFileDTO(R.drawable.txt_icon, fileName, dateModified,0));
                }
            }
        }
    }
    return  list;
}

}

