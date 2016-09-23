package com.riverview.hackthon.mixandmatch.activities;

import android.annotation.TargetApi;
import android.content.DialogInterface;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.riverview.hackthon.mixandmatch.DbUtil.DatabaseHandler;
import com.riverview.hackthon.mixandmatch.R;
import com.riverview.hackthon.mixandmatch.Utils.AppConst;
import com.riverview.hackthon.mixandmatch.Utils.AppUtil;
import com.riverview.hackthon.mixandmatch.model.BeanItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener{

    private static AlertDialog alertDialog = null;

    private static final int STORAGE_REQUEST_CODE = 102;

    private ImageView imgVAddImage;

    private EditText etBrand;

    private Spinner spCategory,spColor,spBrand;

    private ImageView imgColorPicker;

    private String imagePath = null;

    private String pathI = null;

    private String strColor;
    private Button btnSubmit;

    private  HashMap<String,Integer> categoryList =null;

    private HashMap<String, String> attachments = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgVAddImage = (ImageView) findViewById(R.id.imgVAddImage);
        imgVAddImage.setOnClickListener(this);

        imgColorPicker = (ImageView) findViewById(R.id.imgColorPicker);
        imgColorPicker.setOnClickListener(this);

        etBrand = (EditText) findViewById(R.id.etBrand);

        spCategory = (Spinner) findViewById(R.id.spCategory);

        HashMap<String, Integer> categoryList = getCategories();
        if(categoryList != null){
            ArrayList<String> categoryNameList = getCategoryNameList(categoryList);
           /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.item_category_array, android.R.layout.simple_spinner_item);*/
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, categoryNameList);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spCategory.setAdapter(adapter);
        }





        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItemDetails();
            }
        });



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
            case R.id.imgColorPicker:
                showColorPicker();
                break;
            default:
                break;

        }
    }

    private  void showColorPicker(){
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(R.color.green)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                       // toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        //changeBackgroundColor(selectedColor);
                        imgColorPicker.setBackgroundColor(selectedColor);
                        strColor = String.valueOf(selectedColor);
                        Toast.makeText(AddItemActivity.this,""+selectedColor,Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
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

    private  void saveItemDetails(){
        DatabaseHandler db = new DatabaseHandler(this);
        BeanItem clothItem = new BeanItem();
        String category = spCategory.getSelectedItem().toString();
        clothItem.setCategoryId(categoryList.get(category));
        clothItem.setBrand(etBrand.getText().toString());
        clothItem.setColor(strColor);
        clothItem.setImage(imagePath);

       long returnValue =  db.addClothItem(clothItem);

        if(returnValue > 0){
            Toast.makeText(this,"Data are saved sucessfully",Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public HashMap<String,Integer> getCategories(){
        DatabaseHandler db = new DatabaseHandler(this);
        categoryList  = db.getAllCategory();

        return categoryList;
    }

    private ArrayList<String> getCategoryNameList(HashMap<String,Integer> list){
        ArrayList<String> categoryNameList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : list.entrySet()) {
            categoryNameList.add(entry.getKey());
        }
        return categoryNameList;
    }
}
