package com.gmail.olemuzyka.phonebook_listview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ContactsArrayAdapter extends ArrayAdapter {
    private int layoutResourceId;
    private Context context;
    private ArrayList<Contact> contactList;
    private LayoutInflater inflater;
    private MyClickListener myClickListener;

    public ContactsArrayAdapter(Context context, ArrayList<Contact> contactList, MyClickListener closeListener) {
        super(context, R.layout.my_list_item, contactList);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.contactList = contactList;
        this.myClickListener = closeListener;
    }

    View.OnClickListener MyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Contact contact = (Contact) view.getTag();
           switch (view.getId()){
               case R.id.button_add:
                   myClickListener.addPhoneNumber(contact);
                   break;
               case R.id.button_close:
                   myClickListener.clickOnCloseButton(contact);
                   break;
               case R.id.button_remove:
                   myClickListener.removePhoneNumber(contact);
                   break;
           }
        }
    };

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = (Contact) getItem(position);
        convertView = inflater.inflate(R.layout.my_list_item, parent, false);
        ImageView photo = (ImageView) convertView.findViewById(R.id.image);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView phone = (TextView) convertView.findViewById(R.id.phone);
        Button buttonClose = (Button) convertView.findViewById(R.id.button_close);
        TextView phone2 = (TextView) convertView.findViewById(R.id.phone2);
        buttonClose.setOnClickListener(MyClickListener);
        buttonClose.setTag(contact);
        Button addButton = (Button) convertView.findViewById(R.id.button_add);
        addButton.setOnClickListener(MyClickListener);
        addButton.setTag(contact);
        Button removeButton = (Button) convertView.findViewById(R.id.button_remove);
        removeButton.setOnClickListener(MyClickListener);
        removeButton.setTag(contact);

        if (contact.getPhoneNumber2() != null) {
            addButton.setEnabled(false);
            removeButton.setEnabled(true);
        } else {
            addButton.setEnabled(true);
            removeButton.setEnabled(true);
        }
        Glide.with(context)
                .load(contact.getPhoto())
                .into(photo);
        name.setText(contact.getName());
        phone.setText(contact.getPhoneNumber());
        phone2.setText(contact.getPhoneNumber2());
        return convertView;
    }

    public interface MyClickListener {
        void clickOnCloseButton(Contact contact);

        void addPhoneNumber(Contact contact);

        void removePhoneNumber(Contact contact);
    }
}
