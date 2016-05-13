package com.kcode.wximportment.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kcode.wximportment.R;
import com.kcode.wximportment.bean.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caik on 16/5/13.
 */
public class ArticleAdapter  extends RecyclerView.Adapter<ArticleAdapter.ViewHolder>{

    private List<Article> data;
    private Context context;
    private LayoutInflater mInflater;

    public ArticleAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addData(List<Article> data){
        if(this.data == null){
            this.data = new ArrayList<>();
        }

        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(mInflater.inflate(R.layout.item_article,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(TextUtils.isEmpty(data.get(position).getFirstImg())){
            holder.image.setVisibility(View.GONE);
        }else {
            holder.image.setVisibility(View.VISIBLE);
            Glide.with(context).load(data.get(position).getFirstImg())
                    .centerCrop()
                    .into(holder.image);
        }

        holder.title.setText(data.get(position).getTitle());
        holder.source.setText(data.get(position).getSource());
    }



    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView image;
        private final TextView title;
        private final TextView source;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            source = (TextView) itemView.findViewById(R.id.source);
        }
    }
}
