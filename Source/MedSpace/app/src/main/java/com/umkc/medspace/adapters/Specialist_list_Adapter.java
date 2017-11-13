package com.umkc.medspace.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umkc.medspace.R;
import com.umkc.medspace.api.response.Specialist_data;
import com.umkc.medspace.doctor.Appointments;
import com.umkc.medspace.patient.Specialist_Details;

import java.util.List;


public class Specialist_list_Adapter extends RecyclerView.Adapter<Specialist_list_Adapter.MyViewHolder> {

    Context context;
    private List<Specialist_data> Hospital_data = null;


    public Specialist_list_Adapter(Context context, List<Specialist_data> Hospital_data) {
        this.Hospital_data = Hospital_data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_specialist_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final List<Specialist_data> dataList = Hospital_data;
        final int sid = dataList.get(position).getSid();
        holder.tvSpecialist.setText(dataList.get(position).getSpecialists());

        holder.cvSpecialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Specialist_Details.class);
                intent.putExtra("specialist", dataList.get(position).getSpecialists());
                intent.putExtra("sid", String.valueOf(sid));
                context.startActivity(intent);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvSpecialist;
        CardView cvSpecialist;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvSpecialist = (TextView) itemView.findViewById(R.id.tvSpecialist);
            cvSpecialist = (CardView) itemView.findViewById(R.id.cvSpecialist);
        }
    }

    @Override
    public int getItemCount() {
        return Hospital_data.size();
    }

}
