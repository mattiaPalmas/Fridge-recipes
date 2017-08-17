package com.exerciseapp.mattiapalmas.fridgerecipes;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by mattia palmas on 2017-08-11.
 */

public class AdaptorRecycleView extends RecyclerView.Adapter<AdaptorRecycleView.ViewHolder> {

    private List<ListItemModel> listItems;
    private Context context;

    /**
     * Constructor that initialize list items and context.
     * @param listItems
     * @param context
     */
    public AdaptorRecycleView(List<ListItemModel> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    /**
     * this method is called when an instance of the ViewHolder is created.
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recycle_view,parent,false);
        return new ViewHolder(view);
    }

    /**
     * after created the onCreateViewHolder this method is called for binding the data.
     * @param holder is the group of views.
     * @param position is the current position.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ListItemModel listItem = listItems.get(position);

        holder.movieTitle.setText(listItem.getMovieTitle());
        holder.description.setText(listItem.getDesc());
        Picasso.with(context).load(listItem.getMovieImage()).into(holder.movieImageView);

    }

    /**
     * @return return how many items have the recyclerView.
     */
    @Override
    public int getItemCount() {
        return listItems.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView movieTitle;
        public TextView description;
        public ImageView movieImageView;


        public ViewHolder(View itemView) {
            super(itemView);

            movieTitle = (TextView) itemView.findViewById(R.id.textViewMovieTitle);
            description = (TextView) itemView.findViewById(R.id.textViewDesc);
            movieImageView = (ImageView) itemView.findViewById(R.id.imageMovie);
        }
    }
}
