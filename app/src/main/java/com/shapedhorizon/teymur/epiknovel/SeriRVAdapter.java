package com.shapedhorizon.teymur.epiknovel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Teymur on 13.10.2017.
 */

public class SeriRVAdapter extends RecyclerView.Adapter<SeriRVAdapter.ViewHolder> {


    private List<Seri> mSeris;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public SeriRVAdapter(Context context, List<Seri> seris) {
        mSeris = seris;
        mContext = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView serititle;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            serititle = (TextView) itemView.findViewById(R.id.basliq);
        }



        // Easy access to the context object in the recyclerview
        private Context getContext() {
            return mContext;
        }
    }
    @Override
    public SeriRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.seri_view, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SeriRVAdapter.ViewHolder holder, int position) {

        Seri contact = mSeris.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.serititle;
        textView.setText(contact.getTitle());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
