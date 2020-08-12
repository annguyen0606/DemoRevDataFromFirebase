package com.annguyen.conekfirebase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.annguyen.conekfirebase.Model.ThongTinKhachHangTouch;
import com.annguyen.conekfirebase.R;

import java.util.List;

public class AdapterKhachHang extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ThongTinKhachHangTouch> thongTinKhachHangTouchList;

    public AdapterKhachHang(Context context, int layout, List<ThongTinKhachHangTouch> thongTinKhachHangTouchList) {
        this.context = context;
        this.layout = layout;
        this.thongTinKhachHangTouchList = thongTinKhachHangTouchList;
    }

    @Override
    public int getCount() {
        return thongTinKhachHangTouchList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView idTag;
        TextView name;
        TextView timeTouch;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AdapterKhachHang.ViewHolder holder;
        if(view == null){
            holder = new AdapterKhachHang.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.idTag = view.findViewById(R.id.tvIdCustomer);
            holder.name = view.findViewById(R.id.tvNameCustomer);
            holder.timeTouch = view.findViewById(R.id.tvTimeTouch);
            view.setTag(holder);
        }else{
            holder = (AdapterKhachHang.ViewHolder) view.getTag();
        }

        ThongTinKhachHangTouch thongTinKhachHangTouch = thongTinKhachHangTouchList.get(i);
        holder.idTag.setText(thongTinKhachHangTouch.getMaKhachHang());
        holder.name.setText(thongTinKhachHangTouch.getNameKhachHang());
        holder.timeTouch.setText(thongTinKhachHangTouch.getThoiGianTouch());

        return view;
    }
}
