package com.example.labpro2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ClientViewModel extends AndroidViewModel {
    private ClientRepository repository;
    private LiveData<List<Client>>allClients;

    public ClientViewModel(@NonNull Application application) {
        super(application);
        repository=new ClientRepository(application);
        allClients=repository.getAllClients();

    }
    public void insert(Client client){
        repository.insert(client);
    }
    public void update(Client client){
        repository.update(client);
    }
    public void delete(Client client){
        repository.delete(client);
    }
   public void deleteAllClients (){
        repository.deleteAllClients();
   }
public LiveData<List<Client>>getAllClients(){
        return allClients;

}
}
