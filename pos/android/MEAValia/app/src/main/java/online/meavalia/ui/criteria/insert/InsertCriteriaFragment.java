package online.meavalia.ui.criteria.insert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import online.meavalia.R;
import online.meavalia.databinding.InsertCriteriaBinding;

public class InsertCriteriaFragment extends Fragment {

    private InsertCriteriaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CriteriaViewModel criteriaViewModel =
                new ViewModelProvider(this).get(CriteriaViewModel.class);

        configureTitle("Insert criteria");
        configureBackButton();

        binding = InsertCriteriaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSettings;
        criteriaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void configureTitle(final String title) {
        getMainActivity().getSupportActionBar().setTitle(Objects.isNull(title) ? "Title" : title);
    }

    private void configureBackButton() {
        showBackButton(true);
    }

    private void showBackButton(final boolean showButton){
        getMainActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(showButton);
    }

    private AppCompatActivity getMainActivity(){
        return ((AppCompatActivity) getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}