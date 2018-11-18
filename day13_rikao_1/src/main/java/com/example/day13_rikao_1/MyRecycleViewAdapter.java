package com.example.day13_rikao_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 择木 on 2018/11/15.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mDatas;

    public MyRecycleViewAdapter(Context context, ArrayList<String> datas) {
        mContext = context;
        mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //把XML布局变成了VIew对象
        View itemView = View.inflate(mContext, R.layout.item_show, null);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    //绑定ViewHolder   更新UI   给控件设置数据
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //从集合里根据我们的条目位置Position拿到对应的数据
        String data = mDatas.get(position);
        //通过holder拿到控件对象设置数据
        holder.tv_title.setText(data);
    }

    //规定条目总数量
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_title = itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, getLayoutPosition() + "");
                }
            });
        }
    }

    /////////////自定义接口回调////////////
    //1.定义接口
    public interface OnItemClickListener {
        void onItemClick(View view, String data);
    }

    //定义成员变量
    private OnItemClickListener mOnItemClickListener;

    //设置方法暴露给外界
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    ////////////添加数据和删除数据///////////////
    public void addData(int position, String data) {
        mDatas.add(position, data);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
}
