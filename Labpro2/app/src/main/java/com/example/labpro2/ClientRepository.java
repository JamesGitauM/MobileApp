package com.example.labpro2;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

public class ClientRepository {
    private ClientDao clientDao;
    private LiveData<List<Client>> allClients;
    public ClientRepository(Application application){
        ClientsDatabase database=ClientsDatabase.getInstance(application);
        clientDao=database.clientDao();
        allClients=clientDao.getAllClients();

    }
    public void insert(Client client){
        new InsertClientAsyncTask(clientDao).execute(client);

    }
    public void update(Client client){
        new UpdateClientAsyncTask(clientDao).execute(client);


    }
    public void delete(Client client){
        new DeleteClientAsyncTask(clientDao).execute();

    }
    public void deleteAllClients(){

    }
    public LiveData<List<Client>> getAllClients(){
        return allClients;

    }
   //creating ASync tasks
    private static class InsertClientAsyncTask extends AsyncTask<Client,Void,Void>{
      private ClientDao clientDao;
      private InsertClientAsyncTask(ClientDao clientDao){
          this.clientDao=clientDao;

      }

       @Override
       protected Void doInBackground(Client... clients) {
          clientDao.insert(clients[0]);
           return null;
       }
   }

    private static class UpdateClientAsyncTask extends AsyncTask<Client,Void,Void>{
        private ClientDao clientDao;
        private UpdateClientAsyncTask(ClientDao clientDao){
            this.clientDao=clientDao;

        }

        @Override
        protected Void doInBackground(Client... clients) {
            clientDao.update(clients[0]);
            return null;
        }
    }
    private static class DeleteClientAsyncTask extends AsyncTask<Client,Void,Void>{
        private ClientDao clientDao;
        private DeleteClientAsyncTask(ClientDao clientDao){
            this.clientDao=clientDao;

        }

        @Override
        protected Void doInBackground(Client... clients) {
            clientDao.delete(clients[0]);
            return null;
        }
    }
    private static class DeleteAllClientsAsyncTask extends AsyncTask<Void,Void,Void>{
        private ClientDao clientDao;
        private DeleteAllClientsAsyncTask(ClientDao clientDao){
            this.clientDao=clientDao;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            clientDao.deleteAllClients();
            return null;
        }
    }

}

