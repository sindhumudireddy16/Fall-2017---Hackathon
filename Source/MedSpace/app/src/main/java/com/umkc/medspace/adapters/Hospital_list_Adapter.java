package com.umkc.medspace.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.umkc.medspace.R;
import com.umkc.medspace.api.response.Hospital_data;
import com.umkc.medspace.doctor.Appointments;

import java.util.List;


public class Hospital_list_Adapter extends RecyclerView.Adapter<Hospital_list_Adapter.MyViewHolder> {

    Context context;
    private List<Hospital_data> Hospital_data = null;


    public Hospital_list_Adapter(Context context, List<Hospital_data> Hospital_data) {
        this.Hospital_data = Hospital_data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hospital_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final List<Hospital_data> dataList = Hospital_data;
        final int store_id = dataList.get(position).getHid();
        holder.name.setText(dataList.get(position).getName());
        holder.address.setText(dataList.get(position).getAddress());
        holder.rating.setText("ratings: " + dataList.get(position).getRanking());
        holder.rating.setVisibility(View.GONE);
        holder.rBRating.setNumStars(Integer.valueOf(dataList.get(position).getRanking()));

        holder.cvHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Appointments.class);
                intent.putExtra("hospital_name", dataList.get(position).getName());
                intent.putExtra("hid", String.valueOf(dataList.get(position).getHid()));
                context.startActivity(intent);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, address, rating;
        private final RatingBar rBRating;
        CardView cvHospitals;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvHospitalname);
            address = (TextView) itemView.findViewById(R.id.tvaddress);
            rating = (TextView) itemView.findViewById(R.id.tvRating);
            rBRating = (RatingBar) itemView.findViewById(R.id.rBRating);
            cvHospitals = (CardView) itemView.findViewById(R.id.cvHospitals);
        }
    }

    @Override
    public int getItemCount() {
        return Hospital_data.size();
    }

}
