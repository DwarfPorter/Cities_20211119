package ru.geekbrains.cities;

import static ru.geekbrains.cities.CoatOfArmsFragment.ARG_INDEX;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CitiesFragment extends Fragment {

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cities, container, false);
        return root;
    }

    // Этот метод вызывается, когда макет экрана создан и готов к отображению информации. Создаем список городов
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
        Log.d("Fragment Cities", "Start");
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);

        LayoutInflater ltInflater = getLayoutInflater();

        for(int i = 0; i < cities.length; i++){
            String cityName = cities[i];
            View item = ltInflater.inflate(R.layout.item, layoutView, false);
            TextView textView = item.findViewById(R.id.textView);
            textView.setText(cityName);
            layoutView.addView(item);
            final int position = i;
            textView.setOnClickListener(v -> {
                City currentCity = new City(position, cityName);
                showPortCoatOfArms(currentCity);
            });
        }
    }

    private void showPortCoatOfArms(City city) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, CoatOfArmsFragment.newInstance(city))
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment Cities", "Finish");
    }
}