package com.saifi369.searchviewdemo.data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saifi369.searchviewdemo.R;
import com.saifi369.searchviewdemo.model.CityDataItem;
import com.saifi369.searchviewdemo.ui.DetailsActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyViewHolder> implements Filterable {

    public static final String ITEM_KEY = "item_key";
    private List<CityDataItem> mDataList;
    private List<CityDataItem> mDataListFull;
    private Context mContext;

    public MyDataAdapter(List<CityDataItem> mDataList, Context mContext) {
        this.mDataList = mDataList;
        this.mContext = mContext;
        mDataListFull=new ArrayList<>();
        mDataListFull.addAll(mDataList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.list_item_layout,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final CityDataItem cityDataItem=mDataList.get(position);

        holder.textView.setText(cityDataItem.getCityName());

        InputStream inputStream=null;
        try{
            inputStream = mContext.getAssets().open(cityDataItem.getImage());
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            holder.imageView.setImageBitmap(bitmap);
            Log.d("meratag", "getView: Image Downloaded: "+cityDataItem.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream == null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext,DetailsActivity.class);

                intent.putExtra(ITEM_KEY,cityDataItem.getCityId());

                mContext.startActivity(intent);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "You have long clicked "+cityDataItem.getCityName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        ImageView imageView;
        View mView;
        public MyViewHolder(View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textView);
            imageView=itemView.findViewById(R.id.imageView);
            mView=itemView;
        }

    }

    @Override
    public Filter getFilter() {
        return cityDataFilter;
    }

    private Filter cityDataFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CityDataItem> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(mDataListFull);
            }else {

                String filter=constraint.toString().toLowerCase().trim();

                for(CityDataItem dataItem:mDataListFull){
                    if(dataItem.getCityName().toLowerCase().contains(filter)){
                        filteredList.add(dataItem);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDataList.clear();
            mDataList.addAll((Collection<? extends CityDataItem>) results.values);
            notifyDataSetChanged();
        }
    };

}
