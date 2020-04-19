package com.sazs.fyptest2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sazs.fyptest2.Order;
import com.sazs.fyptest2.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context mCtx;
    private List<Order> orderList;

    public OrderAdapter(Context mCtx, List<Order> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_order, null);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderViewHolder holder,final int position) {
        Order order = orderList.get(position);

        holder.tvElderlyName.setText(order.getElderly_name());
        holder.tvElderlyAge.setText((String.valueOf(order.getElderly_age())));
        holder.tvElderlyLocation.setText(order.getElderly_location());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView tvElderlyName, tvElderlyAge, tvElderlyLocation;

        public OrderViewHolder(View itemView){
            super(itemView);

            tvElderlyName = itemView.findViewById(R.id.tvElderlyName);
            tvElderlyAge = itemView.findViewById(R.id.tvElderlyAge);
            tvElderlyLocation = itemView.findViewById(R.id.tvElderlyLocation);
        }
    }
}
