package com.mohit.myassignment.ui.view;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.mohit.myassignment.R;

import static com.mohit.myassignment.R.*;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class FactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_facts);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(id.fragmentsContainer) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(getString(string.fact_title));
            // Create a new Fragment to be placed in the activity layout
            FactsListFragment factsListFragment = new FactsListFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            factsListFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragmentsContainer' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(id.fragmentsContainer, factsListFragment).commit();
        }
    }
}

