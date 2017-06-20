package com.example.sage.basiclayouts;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class  WhiteListDisplay extends RecyclerView.Adapter<WhiteListDisplay.ContactViewHolder> {

    private int mNumberItems;

    public interface ListItemClickListener{
        void onListItemClick (int clickedItemIndex);
    }

    final private ListItemClickListener mOnClickListener;

    public WhiteListDisplay(int numberOfItems, ListItemClickListener listener){
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
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
        return mNumberItems;
    }

    /** View Holder below**/
    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView listItemContactView;
        Button editContact;


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

        public ContactViewHolder(View itemView) {
            super(itemView);
            this.listItemContactView = (TextView) itemView.findViewById(R.id.tv_item_contact);
            this.editContact = (Button) itemView.findViewById(R.id.edit_contact);
            editContact.setOnClickListener(this);
        }


        void bind(int listIndex) {
            listItemContactView.setText(String.valueOf(listIndex));
        }


    }
}



