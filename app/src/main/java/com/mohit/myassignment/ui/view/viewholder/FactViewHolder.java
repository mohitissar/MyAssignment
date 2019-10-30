package com.mohit.myassignment.ui.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mohit.myassignment.R;
import com.mohit.myassignment.service.repository.storge.model.Facts;
import com.mohit.myassignment.ui.listeners.ItemClickListener;
import com.squareup.picasso.Picasso;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class FactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    private Facts facts;
    private TextView titleTV;
    private TextView descriptionTV;
    private ImageView thumbnailIV;
    private ItemClickListener itemClickListener;

    public FactViewHolder(View view, ItemClickListener itemClickListener) {
        super(view);
        this.titleTV = view.findViewById(R.id.title_tv);
        this.descriptionTV = view.findViewById(R.id.description_tv);
        this.thumbnailIV = view.findViewById(R.id.thumbnail_iv);
        this.itemClickListener = itemClickListener;
        view.setOnClickListener(this);
    }

    /**
     * This method is used for bind Facts data to list
     *
     * @param facts - facts
     */
    public void bindTo(Facts facts) {
        this.facts = facts;
        titleTV.setText(facts.getTitle());
        descriptionTV.setText(facts.getDescription());
        if(facts.getImageHref() != null) { // check the image url not equal to null
            String imageUrl =  facts.getImageHref();
            Picasso.get().load(imageUrl).into(thumbnailIV);
        }
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) { // check the image url not equal to null
            itemClickListener.OnItemClick(facts); // call the onClick in the OnItemClickListener
        }
    }
}
