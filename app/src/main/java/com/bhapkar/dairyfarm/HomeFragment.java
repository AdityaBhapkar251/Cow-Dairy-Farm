package com.bhapkar.dairyfarm;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhapkar.dairyfarm.CowAdapter;
import com.bhapkar.dairyfarm.data.model.Cow;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView cowRecyclerView;
    private CowAdapter cowAdapter;
    private List<Cow> cowList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cowRecyclerView = view.findViewById(R.id.cow_list);
        cowRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cowAdapter = new CowAdapter(cowList, cow -> openCowDetails(cow));
        cowRecyclerView.setAdapter(cowAdapter);

        view.findViewById(R.id.add_cow_box).setOnClickListener(v -> openAddCowDialog());

        return view;
    }

    private void openAddCowDialog() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_cow);

        EditText cowId = dialog.findViewById(R.id.cow_id);
        EditText cowName = dialog.findViewById(R.id.cow_name);
        Button btnAddCow = dialog.findViewById(R.id.btn_add_cow);

        btnAddCow.setOnClickListener(v -> {
            String id = cowId.getText().toString();
            String name = cowName.getText().toString();

            if (!id.isEmpty()) {
                Cow cow = new Cow(id, name);
                cowList.add(cow);
                cowAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void openCowDetails(Cow cow) {
        // Implementation to show cow details form
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_cow_details);

        EditText cowWeight = dialog.findViewById(R.id.edit_cow_weight);
        EditText cowColor = dialog.findViewById(R.id.edit_cow_color);
        Button btnSave = dialog.findViewById(R.id.btn_save_additional_details);

        btnSave.setOnClickListener(v -> {
            String weight = cowWeight.getText().toString();
            String color = cowColor.getText().toString();

            // Update the cow object with additional details
            cow.setWeight(weight);
            cow.setColor(color);

            // Notify the adapter that the dataset has changed
            cowAdapter.notifyDataSetChanged();

            // Dismiss the dialog
            dialog.dismiss();
        });

        dialog.show();
    }
}
