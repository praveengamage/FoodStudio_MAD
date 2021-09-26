package com.tech.foodstudio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.foodstudio.Database.DatabaseHelper;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private Context context;
    private ArrayList<Model> arrayList;

    //database object
    DatabaseHelper databaseHelper;

    public Adapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        //inisialize dbheler
        databaseHelper = new DatabaseHelper(context);

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_promo_details, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        Model model = arrayList.get(position);
        //get for view
        final String id = model.getId();
        final String date = model.getDate();
        final String description = model.getDescription();
        final String code = model.getPromoCode();

        //set views
        holder.PromoDate.setText(date);
        holder.PromoDescription.setText(description);
        holder.PromoCode.setText(code);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDetails(
                  ""+position,
                  ""+id,
                  ""+date,
                  ""+description,
                        ""+code
                );
            }
        });

        //when long press on item, show and alert dialog for delete
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                DeleteItem(
                        ""+id
                );

                return false;
            }
        });

    }


    private void DeleteItem(final String id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you want to delete ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.delete);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                databaseHelper.deleteInfo(id);
                ((ViewBreakfastPromo)context).onResume();
                Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        builder.create().show();
    }

    private void UpdateDetails(String position, String id, String date, String description, String code) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Are you want update ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.edit);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, EditBreakfastPromo.class );
                intent.putExtra("ID", id);
                intent.putExtra("date", date);
                intent.putExtra("description", description);
                intent.putExtra("code", code);
                intent.putExtra("EditMode", true);

                context.startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends  RecyclerView.ViewHolder{

        TextView PromoDate, PromoDescription, PromoCode;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            PromoDate = itemView.findViewById(R.id.pdate);
            PromoDescription = itemView.findViewById(R.id.pdescription);
            PromoCode = itemView.findViewById(R.id.pcode);
            imageView=(itemView).findViewById(R.id.imageView);
        }
    }

}
