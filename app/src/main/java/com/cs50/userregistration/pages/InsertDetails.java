package com.cs50.userregistration.pages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cs50.userregistration.R;
import com.cs50.userregistration.model.DataHolder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.UUID;

public class InsertDetails extends AppCompatActivity {

    EditText Productname,Productprice,Productdesc,Producttype;
    DataHolder productHolder;
    Button productInsert;
    Bitmap bmap;
    ImageView productimage;
    Uri filepath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_details);

        Productname=findViewById(R.id.prodname);
        Productprice=findViewById(R.id.prodprice);
        Producttype=findViewById(R.id.prodtype);
        Productdesc=findViewById(R.id.prodesc);
        productimage=findViewById(R.id.prodimg);
        productInsert=findViewById(R.id.datasubmit);
//        productimage=findViewById(R.id.prodimg);

        productimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent= new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent,"Choose the image"),1);



                    }
        });

        productInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductUpload();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==1 && data.getData() !=null){
            filepath=data.getData();

            try{

                InputStream istream=getContentResolver().openInputStream(filepath);
                bmap= BitmapFactory.decodeStream(istream);
                productimage.setImageBitmap(bmap);
            }catch (Exception e){
                e.printStackTrace();
            }


        }else{
            Toast.makeText(InsertDetails.this, "Issue uploading image", Toast.LENGTH_SHORT).show();
        }
    }

    private void ProductUpload() {


        String name= Productname.getText().toString().trim();
        String price=Productprice.getText().toString().trim();
        String type=Producttype.getText().toString().trim();
        String desc=Productdesc.getText().toString().trim();

        int price1=Integer.parseInt(price);

        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setTitle("UPLOADING....");
        dialog.show();

        String random= UUID.randomUUID().toString();

        FirebaseStorage productStore= FirebaseStorage.getInstance();

        StorageReference productRef= productStore.getReference("image"+random);

     productRef.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                dialog.dismiss();

                productRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        productHolder= new DataHolder(name,price1,type,desc,uri.toString());
                   FirebaseDatabase productdata=FirebaseDatabase.getInstance();
                   DatabaseReference firebaseref=productdata.getReference("Food");

                   firebaseref.push().setValue(productHolder);
//                   firebaseref.child(type).setValue(productHolder);
                   Productname.setText("");
                   Producttype.setText("");
                   Productprice.setText("");
                   Productdesc.setText("");
                   productimage.setImageResource(R.drawable.two);
                        Toast.makeText(InsertDetails.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(InsertDetails.this, "Failed to upload image to database", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double percentage=(double)(100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                dialog.setMessage("uploading "+ (int)percentage + "%");
            }
        });



    }
}