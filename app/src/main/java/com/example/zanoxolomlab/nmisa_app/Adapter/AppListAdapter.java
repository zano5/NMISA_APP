package com.example.zanoxolomlab.nmisa_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zanoxolomlab.nmisa_app.Constants.DataConstants;
import com.example.zanoxolomlab.nmisa_app.DataChartActivity;
import com.example.zanoxolomlab.nmisa_app.R;
import com.example.zanoxolomlab.nmisa_app.classFile.AppListData;
import com.example.zanoxolomlab.nmisa_app.classFile.ContentItem;

import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {

    List<AppListData> dataList;
    Context context;
    private final ContentItem.OnItemClickListener listener;






    public AppListAdapter(List<AppListData> list, Context context){

        this.context = context;
        this.dataList = list;

        listener = null;
    }



    @NonNull
    @Override
    public AppListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.data_view, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AppListAdapter.ViewHolder viewHolder, int i) {



        if(this.dataList.size() > 0) {
            final AppListData data = this.dataList.get(i);

            viewHolder.app_name.setText(data.name);
            viewHolder.app_image.setImageDrawable(data.icon);
            viewHolder.app_usage.setText("" + data.totalMB);

            viewHolder.app_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.v("Zain", "work");
                }
            });


            viewHolder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(context,DataChartActivity.class);
                    intent.putExtra("data_usage",data);
                    context.startActivity(new Intent(context,DataChartActivity.class));
                }
            });




        }



        Log.v("Sydney", ""+ viewHolder.card.isClickable());

//       viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//
//               Log.v("Zain", "work");
//
//               context.startActivity(new Intent(context,DataChartActivity.class));
//
//
//
//
//
//
//           }
//       });






    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView app_name, app_usage;
        private ImageView app_image;
        private CardView card;
        private  View view;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            this.view = itemView;

            this.app_name = (TextView) view.findViewById(R.id.app_name);
            this.app_usage = (TextView) view.findViewById(R.id.app_usage);
            this.app_image = (ImageView) view.findViewById(R.id.app_image);
            this.card = (CardView) view.findViewById(R.id.card);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handel yout event here

                    Log.v("Zain", "work");
                }
            });

        }



    }



}
