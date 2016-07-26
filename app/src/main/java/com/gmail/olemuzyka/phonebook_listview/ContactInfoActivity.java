package com.gmail.olemuzyka.phonebook_listview;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactInfoActivity extends AppCompatActivity {

    public static final String POS_NO = "posNo";
    private ImageView vBigContactPhoto;
    private TextView vPhoneNumberInfo1;
    private TextView vPhoneNumberInfo2;
    private Button vBackButton;
    private TextView vNameInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);
        vBigContactPhoto = (ImageView) findViewById(R.id.image_info);
        vPhoneNumberInfo1 = (TextView) findViewById(R.id.phone_umber_info_1);
        vPhoneNumberInfo2 = (TextView) findViewById(R.id.phone_umber_info_2);
        vNameInfo = (TextView) findViewById(R.id.name_info);
        Contact contact = (Contact) getIntent().getExtras().getSerializable(POS_NO);
        vBigContactPhoto.setImageResource(contact.getPhoto());
        vNameInfo.setText(contact.getName());
        vPhoneNumberInfo1.setText(contact.getPhoneNumber());
        vPhoneNumberInfo2.setText(contact.getPhoneNumber2());
    }


}
