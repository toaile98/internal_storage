package com.example.internal_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_save,btn_read;
    private TextView lbl_1;
    private final String fileName="toailedata";
    private final String content="Chia se kiem thuc lap trinh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_save=findViewById(R.id.btn_savedata);
        btn_read=findViewById(R.id.btn_readdata);
        lbl_1=findViewById(R.id.lbl_1);

        btn_save.setOnClickListener(this);
        btn_read.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_savedata:
                saveDataByCache();
                break;
            case R.id.btn_readdata:
                readData2();
                break;
            default:
                break;
        }
    }

    public void saveData(){
        FileOutputStream fileOutputStream=null;

        try {
            fileOutputStream =openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            Toast.makeText(this,"Save data successfully",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveDataByCache(){
        FileOutputStream fileOutputStream=null;
        File file =null;

        try {
            file=new File(getCacheDir(),fileName);
            fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            Toast.makeText(this,"Save data successfully",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readData() {
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = openFileInput(fileName);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuffer stringBuffer=new StringBuffer();
            String line =null;
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line).append("\n");
            }
            lbl_1.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readData2() {
        FileInputStream fileInputStream= null;
        try {
            File file=new File(getCacheDir(),fileName);
            fileInputStream = openFileInput(fileName);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            StringBuffer stringBuffer=new StringBuffer();
            String line =null;
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line).append("\n");
            }
            lbl_1.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}