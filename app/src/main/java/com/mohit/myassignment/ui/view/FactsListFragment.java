package com.mohit.myassignment.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohit.myassignment.R;
import com.mohit.myassignment.service.repository.storge.model.Facts;
import com.mohit.myassignment.ui.adapter.FactsPageListAdapter;
import com.mohit.myassignment.ui.listeners.ItemClickListener;
import com.mohit.myassignment.ui.viewmodel.FactDetailsViewModel;
import com.mohit.myassignment.ui.viewmodel.FactsListViewModel;


/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class FactsListFragment extends Fragment implements ItemClickListener {

    protected FactsListViewModel viewModel;
    private FactDetailsViewModel detailsViewModel;
    protected RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facts, container, false);
        recyclerView = view.findViewById(R.id.factsRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(getActivity()).get(FactsListViewModel.class);
        observersRegisters();
        return view;
    }

    //This observers Register method is set All Ui elements get data from LiveData
    private void observersRegisters() {
        final FactsPageListAdapter pageListAdapter = new FactsPageListAdapter(this);
        viewModel.getFacts().observe(this, pageListAdapter::submitList);
        viewModel.getNetworkState().observe(this, networkState -> {
            pageListAdapter.setNetworkState(networkState);
        });
        recyclerView.setAdapter(pageListAdapter);
        detailsViewModel = ViewModelProviders.of(getActivity()).get(FactDetailsViewModel.class);
    }

    @Override
    public void OnItemClick(Facts facts) {
        detailsViewModel.getFact().postValue(facts);
        if (!detailsViewModel.getFact().hasActiveObservers()) {
            // Create fragment and give it an argument specifying the article it should show
            FactDetailsFragment detailsFragment = new FactDetailsFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragmentsContainer, detailsFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
        }
    }
}
