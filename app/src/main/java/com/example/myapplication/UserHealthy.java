package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserHealthy extends AppCompatActivity {
    private EditText editBlood , editAllergy , editIlness ;
    private Button btn;
    private FirebaseDatabase database =FirebaseDatabase.getInstance();
    private DatabaseReference rootRef=database.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_healthy);
        editBlood=findViewById(R.id.userBloodGroup);
        editAllergy=findViewById(R.id.userAllergy);
        editIlness=findViewById(R.id.userIllness);
        btn=findViewById(R.id.saveInfo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String blood=editBlood.getText().toString();
                String allergy=editAllergy.getText().toString();
                String illnes=editIlness.getText().toString();
                String userId=rootRef.push().getKey();
                if(userId!=null) {
                    DatabaseReference userRef = rootRef.child(userId);

                    HashMap<String, Object> userMap = new HashMap<>();
                    userMap.put("userblood", blood);
                    userMap.put("userallergy", allergy);
                    userMap.put("userillnes", illnes);
                    userRef.setValue(userMap);
                }
            }
        });

    }
}