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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CitiesFragment extends Fragment {

    private static final String CURRENT_CITY = "CurrentCity";
    // Текущая позиция (выбранный город)
    private City currentCity = null;

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

        if (savedInstanceState != null){
            currentCity = (City) savedInstanceState.getParcelable(CURRENT_CITY);
        }

        initList(view);

        if (Utils.isLandscape(getResources())){
            showLandCoatOfArms(currentCity);
        }
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);

        for(int i = 0; i < cities.length; i++){
            String cityName = cities[i];
            TextView tvCityName = new TextView(getContext());
            tvCityName.setText(cityName);
            tvCityName.setTextSize(30);
            layoutView.addView(tvCityName);
            final int position = i;
            tvCityName.setOnClickListener(v -> {
                currentCity = new City(position, cityName);
                showCoatOfArms(currentCity);
            });
        }
    }

    private void showCoatOfArms(City city) {
        if (Utils.isLandscape(getResources())) {
            showLandCoatOfArms(city);
        } else {
            showPortCoatOfArms(city);
        }
    }

    private void showLandCoatOfArms(City city) {
        CoatOfArmsFragment coatOfArmsFragment = CoatOfArmsFragment.newInstance(city);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.coat_of_arms_container, coatOfArmsFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    private void showPortCoatOfArms(City city) {
        Activity activity = requireActivity();
        Intent intent = new Intent(activity, CoatOfArmsActivity.class);
        intent.putExtra(ARG_INDEX, city);
        activity.startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_CITY, currentCity);
        super.onSaveInstanceState(outState);
    }
}