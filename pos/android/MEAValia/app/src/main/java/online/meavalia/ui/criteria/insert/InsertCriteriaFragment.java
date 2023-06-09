package online.meavalia.ui.criteria.insert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import online.meavalia.MainActivity;
import online.meavalia.R;
import online.meavalia.databinding.InsertCriteriaBinding;
import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.model.CriteriaType;
import online.meavalia.domain.repository.CriteriaRepository;
import online.meavalia.infrastructure.repository.impl.CriteriaRepositoryImpl;

public class InsertCriteriaFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private InsertCriteriaBinding binding;

    private final CriteriaRepository criteriaRepository = new CriteriaRepositoryImpl();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = InsertCriteriaBinding.inflate(inflater, container, false);

        configureTitle();
        configureBackButton();
        configureSaveButton();
        configureSpinner();

        return binding.getRoot();
    }

    private void configureTitle() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setTitle("Insert criteria");
    }

    private void configureBackButton() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void configureSaveButton() {
        final Button saveButton = binding.saveButton;
        saveButton.setClickable(true);
        saveButton.setOnClickListener((v) -> saveCriteria());
    }

    private void saveCriteria() {
        if (verifyRequiredField())
            return;
        final String criteriaName = Objects.requireNonNull(binding.nameTextInputLayout.getEditText()).getText().toString();
        final String criteriaSentence = Objects.requireNonNull(binding.sentenceTextInputLayout.getEditText()).getText().toString();
        final Criteria criteria;
        if (binding.criteriaType.getSelectedItem().equals("NORMAL CRITERIA")) {
            criteria = new Criteria(criteriaName, criteriaSentence);
        } else {
            final String criteriaEmail = Objects.requireNonNull(binding.emailTextInputLayout.getEditText()).getText().toString();
            if (binding.criteriaType.getSelectedItem().equals("PHYSIC PERSON")) {
                final String criteriaCpf = Objects.requireNonNull(binding.cpfTextInputLayout.getEditText()).getText().toString();
                criteria = new Criteria(criteriaName, criteriaSentence, criteriaCpf, criteriaEmail, CriteriaType.PHYSIC_PERSON);
            } else {
                final String criteriaCnpj = Objects.requireNonNull(binding.cnpjTextInputLayout.getEditText()).getText().toString();
                criteria = new Criteria(criteriaName, criteriaSentence, criteriaCnpj, criteriaEmail, CriteriaType.JURIDIC_PERSON);
            }
        }
        criteriaRepository.save(criteria);
        navigateToList();
        Toast.makeText(getMainActivity(), "Criteria inserted!", Toast.LENGTH_LONG).show();
    }

    private boolean verifyRequiredField() {
        if (binding.criteriaType.getSelectedItem().equals("NORMAL CRITERIA")) {
            return verifyRequiredField(binding.nameTextInputLayout, binding.sentenceTextInputLayout);
        } else if (binding.criteriaType.getSelectedItem().equals("PHYSIC PERSON")) {
            return verifyRequiredField(binding.nameTextInputLayout, binding.sentenceTextInputLayout, binding.emailTextInputLayout, binding.cpfTextInputLayout);
        } else {
            return verifyRequiredField(binding.nameTextInputLayout, binding.sentenceTextInputLayout, binding.emailTextInputLayout, binding.cnpjTextInputLayout);
        }
    }

    private boolean verifyRequiredField(final TextInputLayout... textsInputsLayouts) {
        boolean notValidated = false;
        for (final TextInputLayout textsInputsLayout : textsInputsLayouts) {
            if (verifyRequiredField(textsInputsLayout))
                notValidated = true;
        }
        return notValidated;
    }

    private boolean verifyRequiredField(final TextInputLayout textInputLayout) {
        final String value = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString();
        if (Objects.isNull(value) || value.isEmpty()) {
            textInputLayout.setError("This field is required");
            return true;
        } else {
            textInputLayout.setError(null);
        }
        return false;
    }

    private void navigateToList() {
        getMainActivity().getNavController().navigate(R.id.nav_transform);
    }

    private void configureSpinner() {
        final Spinner spinner = binding.criteriaType;
        spinner.setOnItemSelectedListener(this);

        final List<String> categories = new ArrayList<>();
        categories.add("NORMAL CRITERIA");
        categories.add("PHYSIC PERSON");
        categories.add("JURIDIC PERSON");

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this.getMainActivity(), android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private MainActivity getMainActivity() {
        return ((MainActivity) getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final String item = parent.getItemAtPosition(position).toString();

        final TextInputLayout emailTextInputLayout = binding.emailTextInputLayout;
        final TextInputLayout cpfTextInputLayout = binding.cpfTextInputLayout;
        final TextInputLayout cnpjTextInputLayout = binding.cnpjTextInputLayout;

        if (item.equals("NORMAL CRITERIA")) {
            emailTextInputLayout.setVisibility(View.GONE);
            cpfTextInputLayout.setVisibility(View.GONE);
            cnpjTextInputLayout.setVisibility(View.GONE);
        } else {
            emailTextInputLayout.setVisibility(View.VISIBLE);
            if (item.equals("PHYSIC PERSON")) {
                cpfTextInputLayout.setVisibility(View.VISIBLE);
                cnpjTextInputLayout.setVisibility(View.GONE);
            } else if (item.equals("JURIDIC PERSON")) {
                cpfTextInputLayout.setVisibility(View.GONE);
                cnpjTextInputLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}