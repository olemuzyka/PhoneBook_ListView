package com.gmail.olemuzyka.phonebook_listview;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddInfoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE_ADD_INFO_ACTIVITY = 1;
    public static final String CONTACT_FOR_ADD_SECOND_MBN = "CONTACT_FOR_ADD_SECOND_MBN";
    private TextView vContactInfo;
    private EditText vEditNumber;
    private Button vDoneButton;
    private Contact contact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        vContactInfo = (TextView) findViewById(R.id.contact_info);
        vEditNumber = (EditText) findViewById(R.id.edit_number);
        vDoneButton = (Button) findViewById(R.id.button_done);
        vDoneButton.setOnClickListener(this);
        contact = (Contact) getIntent().getExtras().getSerializable(CONTACT_FOR_ADD_SECOND_MBN);
        vContactInfo.setText(contact.getName());
    }

    @Override
    public void onClick(View v) {
        String newNumber = vEditNumber.getText().toString();
        if (!TextUtils.isEmpty(newNumber)) {
            Intent intent = new Intent();
            contact.setPhoneNumber2(newNumber);
            Toast.makeText(AddInfoActivity.this, "Done!!", Toast.LENGTH_SHORT).show();
            intent.putExtra(MainActivity.NEW_MBN_FOR_CONTACT_RECEIVED, contact);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
