package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserInformation extends AppCompatActivity {
 private EditText editName , editPhone , editWeight ,editAge;
    private RadioGroup genderRadioGroup;
 private Button btn;
 private FirebaseDatabase database =FirebaseDatabase.getInstance();
 private DatabaseReference rootRef=database.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        editName=findViewById(R.id.userName);
        editPhone=findViewById(R.id.userPhone);
        editWeight=findViewById(R.id.userWeight);
        editAge=findViewById(R.id.userAge);
        btn=findViewById(R.id.infoSave);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editName.getText().toString();
               String phone=editPhone.getText().toString();
               String weight=editWeight.getText().toString();
                String age = editAge.getText().toString();
                int selectedId = genderRadioGroup.getCheckedRadioButtonId();
                String gender;
                if (selectedId == R.id.femaleRadioButton) {
                    gender = "Kadın";
                } else if (selectedId == R.id.maleRadioButton) {
                    gender = "Erkek";
                } else {
                    gender = null;
                }
                String userId=rootRef.push().getKey();
                if(userId!=null) {
                    DatabaseReference userRef = rootRef.child(userId);

                    HashMap<String, Object> userMap = new HashMap<>();
                    userMap.put("username", name);
                    userMap.put("userphone", phone);
                    userMap.put("userWeight", weight);
                    userMap.put("userage", age);
                    userMap.put("usergender", gender);
                    userRef.setValue(userMap);
                    Toast.makeText(UserInformation.this,"Kayıt işlemi başarılı.",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(UserInformation.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}



