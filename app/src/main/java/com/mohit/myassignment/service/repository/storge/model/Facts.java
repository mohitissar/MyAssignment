package com.mohit.myassignment.service.repository.storge.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


/**
 * Immutable model class for a Facts and entity in the Room database.
 */


@Entity(tableName = "facts")
public class Facts extends BaseObservable {

    @PrimaryKey()
    @ColumnInfo(name = "id")
    @SerializedName(value = "id")
    private Integer mId;
    @ColumnInfo(name = "title")
    @SerializedName(value = "title")
    private String mTitle;
    @ColumnInfo(name = "description")
    @SerializedName(value = "description")
    private String mDescription;
    @ColumnInfo(name = "imageHref")
    @SerializedName(value = "imageHref")
    private String mImageHref;


    // use for ordering the items in view
    public static DiffUtil.ItemCallback<Facts> DIFF_CALLBACK = new DiffUtil.ItemCallback<Facts>() {
        @Override
        public boolean areItemsTheSame(@NonNull Facts oldItem, @NonNull Facts newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Facts oldItem, @NonNull Facts newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    };

    @Bindable
    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Bindable
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @Bindable
    public String getImageHref() {
        return mImageHref;
    }

    public void setImageHref(String mPosterPath) {
        this.mImageHref = mPosterPath;
    }
}
