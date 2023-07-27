package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText editEmail , editSifre;
    private  String txtEmail,txtSifre;
    private FirebaseAuth mAuth;
    Button KayıtOlBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail=findViewById(R.id.kayit_ol_editEmail);
        editSifre=findViewById(R.id.kayit_ol_editSifre);
        mAuth=FirebaseAuth.getInstance();
        KayıtOlBtn=findViewById(R.id.kayıt_ol_btn);
        KayıtOlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kayitOl(view);
            }
        });
    }
    public void kayitOl(View v){
        txtSifre=editSifre.getText().toString();
        txtEmail=editEmail.getText().toString();
        if(!TextUtils.isEmpty(txtEmail)&& (!TextUtils.isEmpty(txtSifre))){
          mAuth.createUserWithEmailAndPassword(txtEmail,txtSifre)
                  .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful()) {
                              Toast.makeText(MainActivity.this, "Kayıt İşlemi Başarılı", Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(MainActivity.this, HomeActivity.class));
                          } else
                              Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                      }
                  });
        } else
            Toast.makeText(this, "Email ve şifre boş olamaz", Toast.LENGTH_SHORT).show();

    }
}