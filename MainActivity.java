package com.example.shobhitm23.barcodescanner;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;


public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private static final int PICK_IMAGE = 100;
    ImageView result;
    private ImageView imgPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.Image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
                openGallery();

            }
        });


       BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.DATA_MATRIX)
                .build();

        BitmapDrawable drawable = (BitmapDrawable) imgPicture.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Barcode> barcodeSparseArray = barcodeDetector.detect(frame);

    }

   public void openGallery() {
        Intent galley = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galley, PICK_IMAGE);
    }

   protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            Uri imageUri = data.getData();
            imgPicture.setImageURI(imageUri);

        }
    }


    @Override
    public <T extends View> T findViewById(int id) {

        return super.findViewById(id);
    }

}






