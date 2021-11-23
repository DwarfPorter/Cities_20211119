package ru.geekbrains.cities;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoatOfArmsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoatOfArmsFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    static final String ARG_INDEX = "index";

    // TODO: Rename and change types of parameters
    private City city;

    public CoatOfArmsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param city Index of city.
     * @return A new instance of fragment CoatOfArmsFragment.
     */
    public static CoatOfArmsFragment newInstance(City city) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = (City) getArguments().getParcelable(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (city == null) {
            return;
        }

        ImageView imageCoatOfArms = view.findViewById(R.id.coat_of_arms_image_view);
        TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
        imageCoatOfArms.setImageResource(images.getResourceId(city.getImageIndex(), 0));
        images.recycle();

        TextView textView = view.findViewById(R.id.coat_of_arms_text_view);
        textView.setText(city.getCityName());

        Log.d("Fragment CoatOfArms", "Start");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment CoatOfArms", "Finish");
    }
}