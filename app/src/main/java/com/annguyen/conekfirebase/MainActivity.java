package com.annguyen.conekfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.annguyen.conekfirebase.Adapter.AdapterKhachHang;
import com.annguyen.conekfirebase.Model.ThongTinKhachHang;
import com.annguyen.conekfirebase.Model.ThongTinKhachHangTouch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static DatabaseReference mData;
    ListView listCustomers;
    TextView ngayThangCheck;
    SwipeRefreshLayout refreshLayout;
    Spinner spinKhachHang;
    String duLieuTraCuu;
    public static AdapterKhachHang adapterKhachHang;
    public static ArrayList<ThongTinKhachHangTouch> arrayCustomersTouch;
    public static ArrayList<ThongTinKhachHang> arrayCustomers;
    ArrayList<String> danhSachThes;
    private Intent intent = null;
    SimpleDateFormat dateFormat;
    String dateCurrent;
    ArrayAdapter<String> adapter;
    DatePickerDialog.OnDateSetListener dateSetListener;
    int year = 1996;
    int month = 6;
    int day = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = FirebaseDatabase.getInstance().getReference();
        AnhXa();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateCurrent = dateFormat.format(new Date());
        ngayThangCheck.setText(dateCurrent);

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String dateTimePick = null;
                if (month + 1 < 10){
                    if(dayOfMonth < 10){
                        dateTimePick = year+"-0"+(month+1)+"-0"+dayOfMonth;
                    }
                    else {
                        dateTimePick = year+"-0"+(month+1)+"-"+dayOfMonth;
                    }
                }
                if(month + 1 >= 10){
                    if (dayOfMonth < 10){
                        dateTimePick = year+"-"+(month+1)+"-0"+dayOfMonth;
                    }else {
                        dateTimePick = year+"-"+(month+1)+"-"+dayOfMonth;
                    }
                }
                ngayThangCheck.setText(dateTimePick.toString());
            }
        };

        mData.child("AccountDuLieuTest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                danhSachThes.clear();
                arrayCustomers.clear();
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        //Toast.makeText(MainActivity.this,snapshot1.child("name").getValue().toString(),Toast.LENGTH_SHORT).show();
                        String keyTag = snapshot1.getKey();
                        ThongTinKhachHang thongTinKhachHang = new ThongTinKhachHang("","","");
                        thongTinKhachHang.setMaKhachHang(snapshot1.child("uid").getValue().toString());
                        thongTinKhachHang.setNameKhachHang(snapshot1.child("name").getValue().toString());
                        thongTinKhachHang.setBirth(snapshot1.child("birth").getValue().toString());
                        arrayCustomers.add(thongTinKhachHang);
                        danhSachThes.add(keyTag);
                        adapter.notifyDataSetChanged();
                        //spinKhachHang.setAdapter(adapter);
                    }
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData.child("DulieuTest").child("123456").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayCustomersTouch.clear();
                //snapshot.child(duLieuTraCuu);
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        Toast.makeText(MainActivity.this,snapshot1.getKey(),Toast.LENGTH_SHORT).show();
//                        ThongTinKhachHangTouch thongTinKhachHangTouch = new ThongTinKhachHangTouch("","","");
//                        thongTinKhachHangTouch.setMaKhachHang(snapshot1.child(duLieuTraCuu).getValue().toString());
                    }
                }else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void AnhXa() {
        listCustomers = findViewById(R.id.lvDuLieuChamThe);
        ngayThangCheck = findViewById(R.id.tvNgayThangKiemTra);
        spinKhachHang = findViewById(R.id.spinDanhSachTag);
        arrayCustomers = new ArrayList<>();
        arrayCustomersTouch = new ArrayList<>();

       // adapterKhachHang = new AdapterKhachHang(MainActivity.this,R.layout.dong_du_lieu,arrayCustomers);
//        listCustomers.setAdapter(adapterKhachHang);
        danhSachThes = new ArrayList<>();
        adapter = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_dropdown_item,danhSachThes);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinKhachHang.setAdapter(adapterKhachHang);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,danhSachThes);
        spinKhachHang.setAdapter(adapter);

        findViewById(R.id.tvNgayThangKiemTra).setOnClickListener(this);

        spinKhachHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                duLieuTraCuu = danhSachThes.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvNgayThangKiemTra:
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener,
                        year,
                        month,
                        day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                break;
        }
    }
}