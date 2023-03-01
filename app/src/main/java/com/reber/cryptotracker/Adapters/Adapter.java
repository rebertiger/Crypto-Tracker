package com.reber.cryptotracker.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reber.cryptotracker.Models.Model;
import com.reber.cryptotracker.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<Model> cryptoList;
    TextView nameRow;
    TextView priceRow;
    TextView symbolRow;
    TextView perRow;
    ImageView chartStatus;
    private final String[] colors = {"#E1BEE7","#9FA8DA","#42A5F5","#80D8FF","#B2DFDB","#C8E6C9","#69F0AE"};

    public Adapter(ArrayList<Model> cryptoList) {
        this.cryptoList = cryptoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 7]));
        nameRow = holder.itemView.findViewById(R.id.nameRow);
        priceRow = holder.itemView.findViewById(R.id.priceRow);
        symbolRow = holder.itemView.findViewById(R.id.symbolRow);
        perRow = holder.itemView.findViewById(R.id.perRow);
        chartStatus = holder.itemView.findViewById(R.id.chartStatus);
        nameRow.setText(cryptoList.get(position).name);
        priceRow.setText(cryptoList.get(position).price);
        symbolRow.setText(cryptoList.get(position).symbol);
        try{
            double perCheck = Double.parseDouble(cryptoList.get(position).per);
            if(perCheck >0){
                chartStatus.setImageResource(R.drawable.profits);

            }else if (perCheck <0){
                chartStatus.setImageResource(R.drawable.loss);
            }else if (perCheck==0){
                chartStatus.setImageResource(R.drawable.minus);
            }
            perRow.setText("%"+cryptoList.get(position).per);
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

    }
}
