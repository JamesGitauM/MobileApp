package com.example.labpro2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// database can contain several entitties ie tables
@Database(entities ={Client.class},version = 1,exportSchema = false)
public abstract class ClientsDatabase extends RoomDatabase {
    //creating a instance of the db
    private static ClientsDatabase instance;
    // method to acess dao
    public abstract ClientDao clientDao();
    //synchronized only one thread access the instance of db
    public static synchronized  ClientsDatabase getInstance(Context context){
if (instance==null){
    instance= Room.databaseBuilder(context.getApplicationContext(),
            ClientsDatabase.class,"clients_database")
            .fallbackToDestructiveMigration()
            .addCallback(roomCallback)
            .build();
}
return instance;
    }
    private static RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
      private ClientDao clientDao;
      private PopulateDbAsyncTask(ClientsDatabase db){
          clientDao=db.clientDao();


      }

        @Override
        protected Void doInBackground(Void... voids) {
          clientDao.insert(new Client("CJG001","James Gitau","james.gitau@cropnuts.com","Tibia House"));
            clientDao.insert(new Client("CJG002","Mburu Gitau","james.gitau@hurricane.com","Tibia House"));
            clientDao.insert(new Client("CJG003","Moste","jammiex@hotmail.com","Tibia House"));
            return null;
        }
    }

}
