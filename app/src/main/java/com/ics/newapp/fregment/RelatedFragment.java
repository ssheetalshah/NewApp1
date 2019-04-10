package com.ics.newapp.fregment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ics.newapp.R;
import com.ics.newapp.adapter.RelatedAdapter;


public class RelatedFragment extends Fragment {
    private RecyclerView related_deals;
    private RelatedAdapter relatedAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] myDataset;

    public RelatedFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_related, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        related_deals = (RecyclerView)view.findViewById(R.id.related_deals);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        related_deals.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        related_deals.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
     //   relatedAdapter = new RelatedAdapter(myDataset);
        related_deals.setAdapter(relatedAdapter);

    }
}
