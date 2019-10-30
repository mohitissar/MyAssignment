package com.mohit.myassignment.ui.view;


import android.view.View;

import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.mohit.myassignment.R;
import com.mohit.myassignment.ui.adapter.FactsPageListAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FactsActivityTest {

    @Rule
    public ActivityTestRule<FactsActivity> mActivityTestRule = new ActivityTestRule<>(FactsActivity.class);

    @Test
    public void ensureRecyclerViewIsPresent() throws Exception {
        FactsActivity activity = mActivityTestRule.getActivity();
        View viewById = activity.findViewById(R.id.factsRecyclerView);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(RecyclerView.class));
        RecyclerView recyclerView = (RecyclerView) viewById;
        FactsPageListAdapter adapter = (FactsPageListAdapter) recyclerView.getAdapter();
        assertThat(adapter, instanceOf(PagedListAdapter.class));
    }
}
