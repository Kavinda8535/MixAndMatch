package com.riverview.hackthon.mixandmatch.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.riverview.hackthon.mixandmatch.R;
import com.riverview.hackthon.mixandmatch.Utils.AppConst;
import com.riverview.hackthon.mixandmatch.Utils.AppUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener{

    private static AlertDialog alertDialog = null;

    private static final int STORAGE_REQUEST_CODE = 102;

    private ImageView imgVAddImage;

    private Spinner spCategory,spColor,spBrand;

    private String imagePath = null;

    private String pathI = null;

    private HashMap<String, String> attachments = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgVAddImage = (ImageView) findViewById(R.id.imgVAddImage);
        imgVAddImage.setOnClickListener(this);

        spCategory = (Spinner) findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);

        spColor = (Spinner) findViewById(R.id.spColor);
        spBrand = (Spinner) findViewById(R.id.spBrand);


    }

    @TargetApi(Build.VERSION_CODES.M)
    public void addImage() {
        alertDialog = null;
        int permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE);
        } else {
            pickImage();
        }

    }

    private void pickImage() {

        AlertDialog.Builder bld = AppUtil.buildAlert(this,"Select image source", null);
        bld.setItems(R.array.imageSource,
                (dialog1, which) -> {
                    switch (which) {
                        case 0:
                            //Gallery
                            Intent i = AppUtil.pickFromGallery();
                            startActivityForResult(i, AppConst.REQUEST_GET_IMAGE_CODE);
                            break;
                        case 1:
                            // camera
                            PackageManager pm = getPackageManager();
                            if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
//
                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//
                                startActivityForResult(cameraIntent, AppConst.REQUEST_GET_IMAGE_CODE);
                            } else {
                                AppUtil.showAlert(getApplicationContext(), null, "You dont have a camera", null);
                            }
                            break;
                        default:
                            break;
                    }
                });
        bld.setNeutralButton(android.R.string.cancel, null);
        bld.setCancelable(true);
        bld.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgVAddImage:
                addImage();
                break;
            default:
                break;

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Log.i("Data Value",""+data.getData().getPath()+"   ,  Result Code"+resultCode);

        if (requestCode == AppConst.REQUEST_GET_IMAGE_CODE) {
            if (data != null && data.getData() != null) {
                //Image From Galarry
                Log.i("Image from Galerry", "Image from Galerry");
                Uri uriIM = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(uriIM, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String pathI = cursor.getString(columnIndex);
                imagePath = pathI;
                Log.i("Image -->", imagePath);

                //checking file size
                File imgFile = new File(imagePath);
                long length = imgFile.length();
                length = length / (1024 * 1024);
                if (length < 10) {
                    //attachments.put("I", imagePath);
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                    imgVAddImage.setImageBitmap(bitmap);
                } else {
                    AppUtil.showAlert(this, getString(R.string.app_name), "Image size must be less than 10"+"mb", null);
                }
//                btn_addImage.setTextColor(Color.GREEN);
            } else {
                //Image from Camera
                if (requestCode == AppConst.REQUEST_GET_IMAGE_CODE && resultCode == -1) {
                    Log.i("Image from Camera", "Image from Camera");
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    saveScaledImage(imageBitmap);
                }
            }

        }
    }


    private void saveScaledImage(Bitmap bmp) {
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mix_and_match";
        String name = "MAM" + System.currentTimeMillis() + ".jpeg";

        File dir = new File(file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, name);
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 85, fOut);

            imagePath = file_path + "/" + name;
            Log.i("Image -->", imagePath);


            //checking file size
            File imgFile = new File(imagePath);
            long length = imgFile.length();
            length = length / (1024 * 1024);
            if (length < 10) {
                attachments.put("I", imagePath);
            } else {
                AppUtil.showAlert(this, getString(R.string.app_name), "Image size must be less than 10"+"mb", null);
            }

//            btn_addImage.setTextColor(Color.GREEN);

            fOut.flush();
            fOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
