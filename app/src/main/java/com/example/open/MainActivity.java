package com.example.open;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ChipGroup chipGroupFilter;
    private RecyclerView recyclerView;
    private PersonAdapter adapter;
    private List<Person> allPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        chipGroupFilter = findViewById(R.id.chip_group_filter);
        recyclerView = findViewById(R.id.recyclerView);

        allPeople = Arrays.asList(
                new Person("Ali","boy"),
                new Person("Omar","boy"),
                new Person("Sara", "girl"),
                new Person("Laila", "girl")
        );

        adapter = new PersonAdapter(new ArrayList<>(allPeople));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        chipGroupFilter.setOnCheckedStateChangeListener((group, checkedIds) -> {
            List<Person> filtered = new ArrayList<>();

            if (checkedIds.isEmpty()) {
                filtered.addAll(allPeople);
            } else {
                int checkedId = checkedIds.get(0);
                String filterGender = "";

                if (checkedId == R.id.chip_boys) {
                    filterGender = "boy";
                } else if (checkedId == R.id.chip_girls) {
                    filterGender = "girl";
                }

                for (Person p : allPeople) {
                    if (p.getGender().equals(filterGender)) {
                        filtered.add(p);

                    }
                }
            }

            adapter.updateList(filtered);
        });
    }


}