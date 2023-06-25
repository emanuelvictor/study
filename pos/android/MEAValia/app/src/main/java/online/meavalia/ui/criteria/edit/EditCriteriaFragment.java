package online.meavalia.ui.criteria.edit;

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

import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import online.meavalia.R;
import online.meavalia.databinding.EditCriteriaBinding;
import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.model.Priority;
import online.meavalia.domain.repository.CriteriaRepository;
import online.meavalia.infrastructure.repository.impl.CriteriaRepositoryImpl;
import online.meavalia.ui.criteria.CriteriaActivity;
import online.meavalia.ui.generic.AbstractCustomFragmentImpl;

public class EditCriteriaFragment extends AbstractCustomFragmentImpl implements AdapterView.OnItemSelectedListener {

    private Criteria criteria;
    private Priority priority;
    private EditCriteriaBinding binding;
    private CriteriaRepository criteriaRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        criteriaRepository = new CriteriaRepositoryImpl(getContext());
        binding = EditCriteriaBinding.inflate(inflater, container, false);

        assert getArguments() != null;
        criteria = (Criteria) getArguments().get("criteria");

        initialConfigurationsFromFields();
        configureEditTexts();
        configureTitle();
        configureCheckBox();
        configureRadioButton();
        configureBackButton();
        configureSaveButton();
        configureClearButton();
        configureSpinner();

        return binding.getRoot();
    }

    private void initialConfigurationsFromFields() {
        binding.emailTextInputLayout.setVisibility(View.GONE);
        binding.radioGroupTypeOfPerson.setVisibility(View.GONE);
        binding.cpfTextInputLayout.setVisibility(View.GONE);
        binding.cnpjTextInputLayout.setVisibility(View.GONE);
    }

    private void configureEditTexts() {
        if (criteria.getLegalPerson() != null) {
            binding.emailTextInputLayout.setVisibility(View.VISIBLE);
            if (criteria.getLegalPerson()) {
                binding.radioButtonLegalPerson.setChecked(true);
                binding.cpfTextInputLayout.setVisibility(View.GONE);
                binding.cnpjTextInputLayout.setVisibility(View.VISIBLE);
            } else {
                binding.radioButtonPhysicPerson.setChecked(true);
                binding.cpfTextInputLayout.setVisibility(View.VISIBLE);
                binding.cnpjTextInputLayout.setVisibility(View.GONE);
            }
        }
        Objects.requireNonNull(binding.cpfTextInputLayout.getEditText())
                .setText(criteria.getDocument());
        Objects.requireNonNull(binding.cnpjTextInputLayout.getEditText())
                .setText(criteria.getDocument());
        Objects.requireNonNull(binding.nameTextInputLayout.getEditText())
                .setText(criteria.getName());
        Objects.requireNonNull(binding.sentenceTextInputLayout.getEditText())
                .setText(criteria.getSentence());
        Objects.requireNonNull(binding.emailTextInputLayout.getEditText())
                .setText(criteria.getEmail());
    }

    private void configureCheckBox() {
        binding.checkboxPerson.setChecked(criteria.getLegalPerson() != null);
        binding.checkboxPerson.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.emailTextInputLayout.setVisibility(View.VISIBLE);
                binding.radioGroupTypeOfPerson.setVisibility(View.VISIBLE);

                if (criteria.getLegalPerson() != null && criteria.getLegalPerson()) {
                    binding.radioButtonLegalPerson.setChecked(true);
                    binding.cpfTextInputLayout.setVisibility(View.GONE);
                    binding.cnpjTextInputLayout.setVisibility(View.VISIBLE);
                } else {
                    binding.radioButtonPhysicPerson.setChecked(true);
                    binding.cpfTextInputLayout.setVisibility(View.VISIBLE);
                    binding.cnpjTextInputLayout.setVisibility(View.GONE);
                }

            } else {
                binding.emailTextInputLayout.setVisibility(View.GONE);
                binding.radioGroupTypeOfPerson.setVisibility(View.GONE);
                binding.cpfTextInputLayout.setVisibility(View.GONE);
                binding.cnpjTextInputLayout.setVisibility(View.GONE);
            }
        });
    }

    private void configureRadioButton() {
        if (criteria.getLegalPerson() != null)
            binding.radioGroupTypeOfPerson.setVisibility(View.VISIBLE);
        else
            binding.radioGroupTypeOfPerson.setVisibility(View.GONE);
        binding.radioButtonLegalPerson.setOnClickListener(view -> {
            criteria.setLegalPerson(true);
            binding.cpfTextInputLayout.setVisibility(View.GONE);
            binding.cnpjTextInputLayout.setVisibility(View.VISIBLE);
        });
        binding.radioButtonPhysicPerson.setOnClickListener(view -> {
            criteria.setLegalPerson(false);
            binding.cpfTextInputLayout.setVisibility(View.VISIBLE);
            binding.cnpjTextInputLayout.setVisibility(View.GONE);
        });
    }

    private void configureTitle() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setTitle(R.string.edit_criteria);
    }

    private void configureBackButton() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void configureSaveButton() {
        final Button saveButton = binding.editButton;
        saveButton.setClickable(true);
        saveButton.setOnClickListener((v) -> updateCriteria());
    }

    private void configureClearButton() {
        binding.clearButton.setClickable(true);
        binding.clearButton.setOnClickListener(v -> clearData());
    }

    private void configureSpinner() {
        final Spinner spinner = binding.priorityCriteria;

        final List<String> categories = Arrays.asList(
                getString(R.string.HIGH_PRIORITY_DESCRIPTION),
                getString(R.string.MEDIUM_PRIORITY_DESCRIPTION),
                getString(R.string.LOW_PRIORITY_DESCRIPTION)
        );

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this.getMainActivity(), android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(criteria.getPriority());
        spinner.setOnItemSelectedListener(this);
    }

    private void updateCriteria() {
        if (hasInvalidField()) {
            Toast.makeText(getMainActivity(), getString(R.string.invalid_fields), Toast.LENGTH_SHORT).show();
            return;
        }
        final String criteriaName = Objects.requireNonNull(binding.nameTextInputLayout.getEditText()).getText().toString();
        final String criteriaSentence = Objects.requireNonNull(binding.sentenceTextInputLayout.getEditText()).getText().toString();
        final Criteria criteria;
        if (!binding.checkboxPerson.isChecked()) {
            criteria = new Criteria(criteriaName, criteriaSentence, priority.getValue());
        } else {
            final String criteriaEmail = Objects.requireNonNull(binding.emailTextInputLayout.getEditText()).getText().toString();
            if (binding.radioButtonPhysicPerson.isChecked()) {
                final String criteriaCpf = Objects.requireNonNull(binding.cpfTextInputLayout.getEditText()).getText().toString();
                criteria = new Criteria(criteriaName, criteriaSentence, criteriaCpf, criteriaEmail, priority.getValue(), false);
            } else {
                final String criteriaCnpj = Objects.requireNonNull(binding.cnpjTextInputLayout.getEditText()).getText().toString();
                criteria = new Criteria(criteriaName, criteriaSentence, criteriaCnpj, criteriaEmail, priority.getValue(), true);
            }
        }
        updateCriteria(criteria);
        navigateToList();
        Toast.makeText(getMainActivity(), getString(R.string.criteria_inserted), Toast.LENGTH_SHORT).show();
    }

    void updateCriteria(final Criteria criteria) {
        criteria.setId(this.criteria.getId());
        criteria.setAvg(this.criteria.getAvg());
        criteriaRepository.update(criteria);
    }

    private boolean hasInvalidField() {
        if (!binding.checkboxPerson.isChecked()) {
            return hasInvalidField(binding.nameTextInputLayout, binding.sentenceTextInputLayout);
        } else if (binding.radioButtonPhysicPerson.isChecked()) {
            return hasInvalidField(binding.nameTextInputLayout, binding.sentenceTextInputLayout, binding.emailTextInputLayout, binding.cpfTextInputLayout);
        } else {
            return hasInvalidField(binding.nameTextInputLayout, binding.sentenceTextInputLayout, binding.emailTextInputLayout, binding.cnpjTextInputLayout);
        }
    }

    private boolean hasInvalidField(final TextInputLayout... textsInputsLayouts) {
        boolean hasInvalidField = false;
        for (final TextInputLayout textsInputsLayout : textsInputsLayouts) {
            if (hasInvalidField(textsInputsLayout))
                hasInvalidField = true;
        }
        return hasInvalidField;
    }

    private boolean hasInvalidField(final TextInputLayout textInputLayout) {
        final String value = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString();
        if (Objects.isNull(value) || value.isEmpty()) {
            textInputLayout.setError("This field is required"); // TODO translate
            return true;
        } else {
            textInputLayout.setError(null);
        }
        return false;
    }

    @Override
    public int getNavHostFragmentId() {
        return R.id.nav_host_fragment_content_main;
    }

    private void navigateToList() {
        getNavController().navigate(R.id.nav_transform);
    }

    private CriteriaActivity getMainActivity() {
        return ((CriteriaActivity) getActivity());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0)
            priority = Priority.HIGH_PRIORITY;
        else if (position == 1)
            priority = Priority.MEDIUM_PRIORITY;
        else
            priority = Priority.LOW_PRIORITY;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void clearData() {
        Toast.makeText(getMainActivity(), getString(R.string.fields_cleared), Toast.LENGTH_SHORT).show();
        Objects.requireNonNull(binding.nameTextInputLayout.getEditText()).setText(null);
        criteria.setName(null);
        Objects.requireNonNull(binding.sentenceTextInputLayout.getEditText()).setText(null);
        criteria.setSentence(null);
        binding.checkboxPerson.setChecked(false);
        criteria.setLegalPerson(null);
        Objects.requireNonNull(binding.emailTextInputLayout.getEditText()).setText(null);
        criteria.setEmail(null);
        binding.radioButtonPhysicPerson.setChecked(false);
        binding.radioButtonLegalPerson.setChecked(false);
        Objects.requireNonNull(binding.cpfTextInputLayout.getEditText()).setText(null);
        Objects.requireNonNull(binding.cnpjTextInputLayout.getEditText()).setText(null);
        criteria.setDocument(null);
    }
}