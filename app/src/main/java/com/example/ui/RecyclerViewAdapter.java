package com.example.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//继承泛型类
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ArticleViewHolder> {
    List<Article> mArticles;
    Activity mActivity;

    RecyclerViewAdapter(List<Article> articleList, Activity activity){
        this.mArticles = articleList;
        this.mActivity = activity;
        notifyDataSetChanged();
    }
    //根据item_article的xml创建视图
    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article,parent,false);
        return new ArticleViewHolder(v);
    }
    //通过postion将articale显示到holder上
    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = mArticles.get(position);
        holder.tv_title.setText(article.title);
        holder.tv_date.setText(article.date);
        holder.tv_user_name.setText(article.userName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra("url", mArticles.get(position).link);
                intent.putExtra("title", mArticles.get(position).title);
                mActivity.startActivity(intent);
            }
        });
    }
    //返回显示数据数目
    @Override
    public int getItemCount() {
        return mArticles.size();
    }
    //继承ViewHolder
    //ViewHolder 自定义holder 减少加载时间
    static class ArticleViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        TextView tv_date;
        TextView tv_user_name;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title =  itemView.findViewById(R.id.tv_title);
            tv_date =  itemView.findViewById(R.id.tv_date);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
        }

    }
}
