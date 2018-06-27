package com.jyz.childrenwatch;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 2018/1/5.
 */

public class BabyInformationActivity extends AppCompatActivity {

    private TextView mNameText;
    private TextView mBirthText;
    private TextView mGrandeText;
    private TextView mWatchText;

    private ImageButton iv_head;
    private Button birthdayBtn;
    private Button gradeBtn;
    private Button WatchNumBtn;
    private Button carryOutBtn;
    private Button nickNameBtn;

    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_PHOTO = 2;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babyinformation );

        mNameText = (TextView) findViewById(R.id.nameText);
        mBirthText = (TextView) findViewById(R.id.birthText);
        mGrandeText = (TextView) findViewById(R.id.gradeText);
        mWatchText = (TextView) findViewById(R.id.watchText);
        final PopUpBoxActivity box = new PopUpBoxActivity();

        iv_head = (ImageButton) findViewById(R.id.iv_head);
        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BabyInformationActivity.this);

                View view = View
                        .inflate(BabyInformationActivity.this, R.layout.photo_choose_dialog, null);
                builder.setView(view);
                Button btn_picture = (Button)view
                        .findViewById(R.id.btn_picture);
                Button btn_photo = (Button)view
                        .findViewById(R.id.btn_photo);
                Button btn_cancel=(Button)view
                        .findViewById(R.id.btn_cancel);

                final AlertDialog dialog = builder.show();
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);

                btn_picture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        select_photo();
                    }
                });

                btn_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        take_photo();
                    }
                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });


        nickNameBtn = (Button) findViewById(R.id.nickname_button);
        nickNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                box.create(BabyInformationActivity.this,"宝贝昵称", "内容不超过8个字", "取消", "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        box.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (box.isEditBoxNull() == true && box.isEditBoxBa() == false) {
                            Toast.makeText(BabyInformationActivity.this, R.string.please_enter, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mNameText.setText(box.transfer());
                            box.dismiss();
                        }
                    }
                });
                box.show();
            }
        });

        birthdayBtn = (Button) findViewById(R.id.birthday_button);
        birthdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                box.create(BabyInformationActivity.this,"生日", "请输入年月日", "取消", "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        box.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (box.isEditBoxNull() == true && box.isEditBoxBa() == false) {
                            Toast.makeText(BabyInformationActivity.this, R.string.please_enter, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mBirthText.setText(box.transfer());
                            box.dismiss();
                        }
                    }
                });
                box.show();
            }
        });

        gradeBtn = (Button) findViewById(R.id.grade_button);
        gradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                box.create(BabyInformationActivity.this,"年级", "内容不超过8个字", "取消", "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        box.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (box.isEditBoxNull() == true && box.isEditBoxBa() == false) {
                            Toast.makeText(BabyInformationActivity.this, R.string.please_enter, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mGrandeText.setText(box.transfer());
                            box.dismiss();
                        }
                    }
                });
                box.show();
            }
        });

        WatchNumBtn = (Button) findViewById(R.id.Watch_number_button);
        WatchNumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                box.create(BabyInformationActivity.this,"手表号码", "请输入11位手机号码", "取消", "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        box.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (box.isEditBoxNull() == true && box.isEditBoxShiYi() == false) {
                            Toast.makeText(BabyInformationActivity.this, R.string.please_enter, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mWatchText.setText(box.transfer());
                            box.dismiss();
                        }
                    }
                });
                box.show();
            }
        });

        carryOutBtn = (Button) findViewById(R.id.carry_out);
        carryOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BabyInformationActivity.this,PositioningActivity.class);
                startActivity(intent);
            }
        });
    }

    public void take_photo() {
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO);

        //创建File对象，用于存储拍照后的图片
        File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUri = FileProvider.getUriForFile(this,"com.jyz.childrenwatch.fileprovider", outputImage);
    }
    /**
     * 从相册中获取图片
     * */
    public void select_photo() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            openAlbum();
        }
    }
    /**
     * 打开相册的方法
     * */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO :
                if (true/*resultCode == RESULT_OK*/) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        iv_head.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case SELECT_PHOTO :
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > 19) {
                        //4.4及以上系统使用这个方法处理图片
                        handleImgeOnKitKat(data);
                    }else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
    /**
     *4.4以下系统处理图片的方法
     * */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }


    /**
     * 4.4及以上系统处理图片的方法
     * */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImgeOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //解析出数字格式的id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }else if ("content".equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的uri，则使用普通方式处理
                imagePath = getImagePath(uri,null);
            }else if ("file".equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
            //根据图片路径显示图片
            displayImage(imagePath);
        }
    }
    /**
     * 根据图片路径显示图片的方法
     * */
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            iv_head.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this, "failed to get image",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 通过uri和selection来获取真实的图片路径
     * */
    private String getImagePath(Uri uri,String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1 :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                }else {
                    Toast.makeText(this,"you need the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

