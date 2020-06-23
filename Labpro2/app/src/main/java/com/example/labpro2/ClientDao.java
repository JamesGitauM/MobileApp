package com.example.labpro2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClientDao {
    @Insert
    void insert(Client client);
    @Update
    void update(Client client);
    @Delete
    void delete(Client client);
    @Query("Delete from client_table")
    void deleteAllClients();
    //method to get al clients as a list
    @Query("select * from client_table order by cid")
    LiveData<List<Client>> getAllClients();

}
