package com.kippz.jenny.firstgen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jenny on 07/10/2016.
 */

public class SetFragment extends Fragment {

    public SetFragment() {
        //required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //Set up an ArrayList to hold the set names
        final ArrayList<CardSet> set = new ArrayList<>();

        set.add(new CardSet("Base", "base1", "set"));
        set.add(new CardSet("Jungle", "base2", "set"));
        set.add(new CardSet("Fossil", "base3", "set"));
        set.add(new CardSet("Team Rocket", "base5", "set"));
        set.add(new CardSet("Gym Heroes", "gym1", "set"));
        set.add(new CardSet("Gym Challenge", "gym2", "set"));

        CardSetAdapter adapter = new CardSetAdapter(getActivity(), set);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CardSet cardSet = set.get(position);
                int i = (int) id;
                String setName = set.get(i).getCode();
                String setSort = set.get(i).getSort();
                Intent intent = new Intent(getActivity(), PokemonActivity.class);
                intent.putExtra("name", setName);
                intent.putExtra("sort", setSort);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
