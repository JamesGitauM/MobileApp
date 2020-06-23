package com.example.labpro2;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter <ClientAdapter.ClientHolder>{
    private List<Client>clients=new ArrayList<>();
    @NonNull
    @Override
    public ClientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_item,parent,false);
        return new ClientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientHolder holder, int position) {
        Client currentClient=clients.get(position);
        holder.textViewClientName.setText( currentClient.getCname());
        holder.textViewClientCode.setText(currentClient.getCcode());
        holder.textViewEmail.setText(currentClient.getEmailaddress());
        holder.textViewPhysicalAddress.setText(currentClient.getPhysicaladdress());

    }

    @Override
    public int getItemCount() {
        //number of clients to show
        return clients.size();
    }
public void setClients(List<Client>clients){
        this.clients=clients;
        notifyDataSetChanged();

}
    class ClientHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
       private TextView textViewClientName;
       private TextView textViewClientCode;
       private TextView textViewEmail;
       private TextView textViewPhysicalAddress;
       private CardView cdview;

        public ClientHolder(@NonNull View itemView) {
            super(itemView);
            textViewClientCode=itemView.findViewById(R.id.clcode);
            textViewClientName=itemView.findViewById(R.id.clientname);
            textViewEmail=itemView.findViewById(R.id.emailadd);
            textViewPhysicalAddress=itemView.findViewById(R.id.localaddress);
            cdview=itemView.findViewById(R.id.client_Cardview);
            cdview.setOnCreateContextMenuListener(this::onCreateContextMenu);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
         contextMenu.setHeaderTitle("Client Options")  ;
            contextMenu.add(0,view.getId(),0,"View Details");
            contextMenu.add(0, (int) getItemId(),0,"Delete");
        }
    }

}
