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

public class TypeFragment extends Fragment{

    public TypeFragment() {
        //required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //Set up an ArrayList to hold the set names
        final ArrayList<CardSet> type = new ArrayList<>();

        type.add(new CardSet("Grass", "Grass", "types"));
        type.add(new CardSet("Fire", "Fire", "types"));
        type.add(new CardSet("Water", "Water", "types"));
        type.add(new CardSet("Lightning", "Lightning", "types"));
        type.add(new CardSet("Psychic", "Psychic", "types"));
        type.add(new CardSet("Fighting", "Fighting", "types"));
        type.add(new CardSet("Colorless", "Colorless", "types"));
        type.add(new CardSet("Trainer", "Trainer", "supertype"));
        type.add(new CardSet("Energy", "Energy", "supertype"));

        CardSetAdapter adapter = new CardSetAdapter(getActivity(), type);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CardSet cardType = type.get(position);
                int i = (int) id;
                String typeName = type.get(i).getCode();
                String typeSort = type.get(i).getSort();
                Intent intent = new Intent(getActivity(), PokemonActivity.class);
                intent.putExtra("name", typeName);
                intent.putExtra("sort", typeSort);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
