package com.mohit.myassignment.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mohit.myassignment.R;
import com.mohit.myassignment.databinding.FragmentDetailsBinding;
import com.mohit.myassignment.ui.viewmodel.FactDetailsViewModel;
import com.squareup.picasso.Picasso;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class FactDetailsFragment extends Fragment {

    private FactDetailsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate view and obtain an instance of the binding class.
        FragmentDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        viewModel = ViewModelProviders.of(getActivity()).get(FactDetailsViewModel.class);
        View view = binding.getRoot();
        viewModel.getFact().observe(this, binding::setFacts);
        return view;
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        if(url != null) { // check the image url not equal to null
            Picasso.get().load(url).into(view);
        }
    }
}
