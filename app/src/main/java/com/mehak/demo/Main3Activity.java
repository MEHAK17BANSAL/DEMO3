package com.mehak.demo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Main3Activity extends AppCompatActivity {
Button btn;
TextView txt;
    String MP3Path ;
    String path;
    Uri file;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mStorage= FirebaseStorage.getInstance().getReference();
        btn=findViewById(R.id.button);
txt=findViewById(R.id.txt);
//
       mProgress=new ProgressDialog(this);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            if ((data != null) && (data.getData() != null)){
                Uri audioFileUri = data.getData();
                // Now you can use that Uri to get the file path, or upload it, ...
              MP3Path = audioFileUri.getPath();


                txt.setText(MP3Path);
                path=MP3Path;
                upload();
            }
        }
    }

    private void upload() {
        mProgress.setMessage("UPLOAD");
        mProgress.show();
        StorageReference filepath=mStorage.child("Audio").child("Mehak");
        file = Uri.fromFile(new File(MP3Path));
      Uri uri=Uri.fromFile(new File(path));
        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mProgress.dismiss();
            }
        });
    }

    public void btn(View view) {
        Intent intent;
        intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/mpeg");
        startActivityForResult(Intent.createChooser(intent, getString(R.string.app_name)), 1);
    }
}
