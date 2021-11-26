package ru.geekbrains.cities;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoatOfArmsChildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoatOfArmsChildFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_INDEX_CHILD = "index";
    private ImageView imageCoatOfArms;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param city Parameter 1.
     * @return A new instance of fragment CoatOfArmsChildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoatOfArmsChildFragment newInstance(City city) {
        CoatOfArmsChildFragment fragment = new CoatOfArmsChildFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX_CHILD, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coat_of_arms_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null){
            City city = arguments.getParcelable(ARG_INDEX_CHILD);
            imageCoatOfArms = view.findViewById(R.id.coat_of_arms_image_view);
            TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
            imageCoatOfArms.setImageResource(images.getResourceId(city.getImageIndex(), 0));
            images.recycle();
            initPopupMenu();
        }

        view.findViewById(R.id.coat_of_arms_child_button_back)
                .setOnClickListener(v -> {
                    getParentFragmentManager().popBackStack();
                    requireActivity().getSupportFragmentManager().popBackStack();
                });

        Log.d("Fragment CoatOfArmsChild", "Start");
    }

    private void initPopupMenu() {
        imageCoatOfArms.setOnLongClickListener(v -> {
            Activity activity = requireActivity();
            PopupMenu popupMenu = new PopupMenu(activity, v);
            activity.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch(item.getItemId()) {
                        case R.id.action_popup_clear:
                            imageCoatOfArms.setImageAlpha(0);
                            return true;
                        case R.id.action_popup_exit:
                            activity.finish();
                            return true;
                    }
                    return false;
                }
            });
            popupMenu.show();
            return true;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment CoatOfArmsChild", "Finish");
    }
}