package ru.geekbrains.cities;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoatOfArmsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoatOfArmsFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    static final String ARG_INDEX = "index";
    private TextView textView;

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
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (city == null) {
            return;
        }

        getChildFragmentManager().beginTransaction()
                .replace(R.id.coat_of_arms_child_container, CoatOfArmsChildFragment.newInstance(city))
                .addToBackStack("")
                .commit();

        textView = view.findViewById(R.id.coat_of_arms_text_view);
        textView.setText(city.getCityName());

        Button buttonBack = view.findViewById(R.id.coat_of_arms_button_back);
        buttonBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        view.findViewById(R.id.coat_of_arms_button_remove).setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            List<Fragment> fragments = fragmentManager.getFragments();
            for (Fragment fragment : fragments){
                if (fragment instanceof CoatOfArmsFragment && fragment.isVisible()){
                    fragmentManager.beginTransaction().remove(fragment).commit();
                }
            }
        });

        Log.d("Fragment CoatOfArms", "Start");
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);
        MenuItem item = menu.findItem(R.id.action_about);
        if (item != null){
            item.setVisible(false);
        }

        menu.add(Menu.NONE, 20, Menu.NONE, "Item menu");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_change){
            textView.setText(R.string.changed_text);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment CoatOfArms", "Finish");
    }
}