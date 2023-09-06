package com.example.project_1.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_1.DTO.AllFileDTO;

import java.util.List;

@Dao
public interface AllFileDAO {
    @Insert
    void insertFile(AllFileDTO dto);

    @Query("SELECT * FROM tb_file")
    List<AllFileDTO> getListFile();

    @Update
    void updateFile(AllFileDTO dto);

    @Delete
    void deleteFile(AllFileDTO dto);

}
