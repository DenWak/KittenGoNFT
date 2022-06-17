package com.example.loginregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CatsAdapter extends RecyclerView.Adapter<CatsAdapter.CatViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Cat cat);
    }

    private Context mCtx;
    private List<Cat> catList;

    public CatsAdapter(Context mCtx, List<Cat> catList) {
        this.mCtx = mCtx;
        this.catList = catList;
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cat_list, null);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        Cat cat = catList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(cat.getImage())
                .into(holder.imageView);

        holder.textViewName.setText(cat.getName());
        holder.textViewPrice.setText(String.valueOf(cat.getPrice()));
        holder.textViewPriceBtc.setText(String.valueOf(cat.getPrice_btc()));
        holder.textViewPriceEth.setText(String.valueOf(cat.getPrice_eth()));
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    class CatViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewPrice, textViewPriceBtc, textViewPriceEth;
        ImageView imageView;

        public CatViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewPriceBtc = itemView.findViewById(R.id.textViewPriceBtc);
            textViewPriceEth = itemView.findViewById(R.id.textViewPriceEth);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
