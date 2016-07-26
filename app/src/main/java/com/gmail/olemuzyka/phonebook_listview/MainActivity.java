package com.gmail.olemuzyka.phonebook_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactsArrayAdapter.MyClickListener {
    public static final String NEW_MBN_FOR_CONTACT_RECEIVED = "NEW_MBN_FOR_CONTACT_RECEIVED";
    ArrayList<Contact> contactList = new ArrayList<Contact>();
    private ListView vListView;
    private ArrayAdapter<Contact> adapter;
    private final static String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vListView = (ListView) findViewById(R.id.listView);
        addContact();

        adapter = new ContactsArrayAdapter(this, contactList, this);
        View header = getLayoutInflater().inflate(R.layout.header, null);
        vListView.addHeaderView(header);
        vListView.setAdapter(adapter);
//       vListView.setClickable(true);
        vListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(LOG_TAG, "itemClick: position = " + i + ", id = "
                        + l);
                Contact contact = (Contact) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(MainActivity.this, ContactInfoActivity.class);
                intent.putExtra(ContactInfoActivity.POS_NO, contact);
                startActivity(intent);
            }
        });


    }

    public void addContact() {
        for (int i = 0; i < 15; i++) {
            contactList.add(new Contact());
        }
    }

    @Override
    public void clickOnCloseButton(Contact contact) {
        contactList.remove(contact);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addPhoneNumber(Contact contact) {
        if (TextUtils.isEmpty(contact.getPhoneNumber2())) {
            Intent intent = new Intent(this, AddInfoActivity.class);
            intent.putExtra(AddInfoActivity.CONTACT_FOR_ADD_SECOND_MBN, contact);
            startActivityForResult(intent, AddInfoActivity.REQUEST_CODE_ADD_INFO_ACTIVITY);
        }
    }

    @Override
    public void removePhoneNumber(Contact contact) {
        if (contact.getPhoneNumber2() != null) {
            contact.setPhoneNumber2(null);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AddInfoActivity.REQUEST_CODE_ADD_INFO_ACTIVITY && resultCode == RESULT_OK) {
            Contact contact= (Contact) data.getSerializableExtra(NEW_MBN_FOR_CONTACT_RECEIVED);
            int index = contactList.indexOf(contact);
            contactList.add(index,contact);
            adapter.notifyDataSetChanged();
        }
    }
}


