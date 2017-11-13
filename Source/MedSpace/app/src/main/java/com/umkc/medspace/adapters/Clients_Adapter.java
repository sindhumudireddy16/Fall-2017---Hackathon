package com.umkc.medspace.adapters;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.umkc.medspace.R;
import com.umkc.medspace.api.objects.Details_data;
import com.umkc.medspace.patient.Chat;
import com.umkc.medspace.patient.Video_call;

import java.util.List;

import static android.support.v4.app.ActivityCompat.requestPermissions;


public class Clients_Adapter extends RecyclerView.Adapter<Clients_Adapter.MyViewHolder> {

    private final String specialist;
    Context context;
    private List<Details_data> Hospital_data = null;
    private static final int PERMISSION_CALLBACK_CONSTANT = 101;


    public Clients_Adapter(Context context, List<Details_data> Hospital_data, String specialist) {
        this.Hospital_data = Hospital_data;
        this.context = context;
        this.specialist = specialist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clients_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final List<Details_data> dataList = Hospital_data;
        final int sid = dataList.get(position).getSid();
        holder.tvDoctorName.setText(dataList.get(position).getName());
        holder.tvSpecialist.setText(specialist);
        holder.tvSpecialist.setVisibility(View.GONE);
        holder.tvHospital.setVisibility(View.GONE);
        holder.tvArea.setVisibility(View.GONE);
        holder.tvHospital.setText(dataList.get(position).getHospital());
        holder.tvArea.setText("Kansas");

        holder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String number = dataList.get(position).getMobile();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.calldialog);
                TextView tvNumber = (TextView) dialog.findViewById(R.id.number);
                Button btncancel = (Button) dialog.findViewById(R.id.btncancel);
                Button btncall = (Button) dialog.findViewById(R.id.btncall);
                tvNumber.setText(number);
                btncall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, PERMISSION_CALLBACK_CONSTANT);
                        } else {
//                            Intent callIntent = new Intent(Intent.ACTION_CALL);
//                            callIntent.setData(Uri.parse("tel:" + number));
//                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                                // TODO: Consider calling
//                                //    ActivityCompat#requestPermissions
//                                // here to request the missing permissions, and then overriding
//                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                //                                          int[] grantResults)
//                                // to handle the case where the user grants the permission. See the documentation
//                                // for ActivityCompat#requestPermissions for more details.
//                                return;
//                            }
//                            context.startActivity(callIntent);
                            Intent intent = new Intent(context, Video_call.class);
                            intent.putExtra("name", dataList.get(position).getName());
                            intent.putExtra("mobile", dataList.get(position).getMobile());
                            context.startActivity(intent);
                            dialog.dismiss();
                        }
                    }
                });
                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        holder.ivChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chat.class);
                intent.putExtra("name", dataList.get(position).getName());
                intent.putExtra("mobile", dataList.get(position).getMobile());
                context.startActivity(intent);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvDoctorName, tvSpecialist, tvHospital, tvArea;
        private final ImageView ivChat;
        private ImageView ivCall;
        CardView cvSpecialist;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDoctorName = (TextView) itemView.findViewById(R.id.tvDoctorName);
            tvSpecialist = (TextView) itemView.findViewById(R.id.tvSpecialist);
            tvHospital = (TextView) itemView.findViewById(R.id.tvHospital);
            tvArea = (TextView) itemView.findViewById(R.id.tvArea);
            ivCall = (ImageView) itemView.findViewById(R.id.ivCall);
            ivChat = (ImageView) itemView.findViewById(R.id.ivChat);
        }
    }

    @Override
    public int getItemCount() {
        return Hospital_data.size();
    }

}
