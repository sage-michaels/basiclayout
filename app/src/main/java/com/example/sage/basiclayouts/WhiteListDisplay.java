package com.example.sage.basiclayouts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sage.basiclayouts.dao.Person;
import com.example.sage.basiclayouts.dao.PersonDaoImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class  WhiteListDisplay extends RecyclerView.Adapter<WhiteListDisplay.ContactViewHolder>{

    public PersonDaoImpl mDataSet;
    private Collection<Person> mContactList;


    private ListItemClickListener mOnClickListener;

    public WhiteListDisplay(ListItemClickListener listener, PersonDaoImpl memory){
        this.mOnClickListener = listener;
        this.mDataSet = memory;
        this.mContactList = mDataSet.load();

    }
    public void CreateNewContact(View view){
        //TODO use an intent to create a new element in the WhiteListDisplay once user enters contact info
    }



    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.contactlistitem;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediatly = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediatly);
        ContactViewHolder viewHolder = new ContactViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /** View Holder below**/
    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView listItemContactView;
        Button editContact;
        Button deleteContact;


        @Override
        public void onClick(View clickedView) {
            int clickedPosition = getAdapterPosition();
            if (clickedView == deleteContact){
                Person toDelete = mDataSet.get(clickedPosition);
                mDataSet.delete(toDelete);
                notifyItemRemoved(clickedPosition);
                notifyItemRangeChanged(clickedPosition, mDataSet.size());
                mContactList = mDataSet.load();
                //TODO make sure contact is removed from memory
            }else{
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }

        public ContactViewHolder(View itemView) {
            super(itemView);
            this.listItemContactView = (TextView) itemView.findViewById(R.id.tv_item_contact);
            this.editContact = (Button) itemView.findViewById(R.id.edit_contact);
            this.deleteContact = (Button) itemView.findViewById(R.id.delete);
            deleteContact.setOnClickListener(this);
            editContact.setOnClickListener(this);
        }


        void bind(int listIndex) {
            Person contact = mDataSet.get(listIndex);
            String firstName = contact.getFirstName();
            String lastName = contact.getLastName();
            listItemContactView.setText(firstName + " " + lastName);
        }
    }
}



