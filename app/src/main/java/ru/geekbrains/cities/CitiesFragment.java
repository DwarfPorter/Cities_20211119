package ru.geekbrains.cities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);

        for(String city: cities){
            TextView tvCityName = new TextView(getContext());
            tvCityName.setText(city);
            tvCityName.setTextSize(30);
            layoutView.addView(tvCityName);
        }
    }

}