package online.meavalia.ui.assessment.execution;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import online.meavalia.R;
import online.meavalia.databinding.SelectNotesBinding;
import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.repository.CriteriaRepository;
import online.meavalia.infrastructure.repository.impl.CriteriaRepositoryImpl;
import online.meavalia.ui.generic.AbstractCustomFragmentImpl;

public class SelectNotesFragment extends AbstractCustomFragmentImpl {

    public static String FILE = "online.meavalia.ui.criteria.FILE";
    public static String KEY = "meavaliaId";
    private Criteria criteria;
    private SelectNotesBinding binding;
    private CriteriaRepository criteriaRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        criteriaRepository = new CriteriaRepositoryImpl(getContext());
        binding = SelectNotesBinding.inflate(inflater, container, false);
        criteria = (Criteria) requireActivity().getIntent().getSerializableExtra("criteria");

        saveCurrentAssessment(criteria);

        configureAssessmentSentence();
        configureLabels();
        configureEmojis();
        configureButtons();

        return binding.getRoot();
    }

    void saveCurrentAssessment(final Criteria criteria) {
        final SharedPreferences shared = requireContext().getSharedPreferences(FILE,
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = shared.edit();
        editor.putInt(KEY, criteria.getId());
        editor.commit();
    }

    private void configureAssessmentSentence() {
        final TextView textView = binding.sentenceNameTextView;
        configureLabels(textView, criteria.getSentence());
    }

    private void configureLabels(final TextView textView, final String value) {
        textView.setText(value);
    }

    private void configureLabels() {
        configureLabels(binding.terribleNameTextView, R.string.terrible);
        configureLabels(binding.badNameTextView, R.string.bad);
        configureLabels(binding.regularNameTextView, R.string.regular);
        configureLabels(binding.goodNameTextView, R.string.good);
        configureLabels(binding.excellentNameTextView, R.string.excellent);
    }

    private void configureLabels(final TextView textView, final int stringId) {
        textView.setText(stringId);
    }

    private void configureEmojis() {
        configureEmoji(binding.terribleNameImageView, R.drawable.terrible, R.color.terrible_color);
        configureEmoji(binding.badNameImageView, R.drawable.bad, R.color.bad_color);
        configureEmoji(binding.regularNameImageView, R.drawable.regular, R.color.regular_color);
        configureEmoji(binding.goodNameImageView, R.drawable.good, R.color.good_color);
        configureEmoji(binding.excellentNameImageView, R.drawable.excellent, R.color.excellent_color);
    }

    private void configureEmoji(final ImageView imageView, int drawableId, int colorId) {
        final Drawable icon = ContextCompat.getDrawable(requireActivity(), drawableId);
        assert icon != null;
        icon.setColorFilter(ContextCompat.getColor(requireActivity(), colorId), PorterDuff.Mode.SRC_IN);
        imageView.setBackground(icon);
    }

    private void configureButtons() {
        configureButton(binding.terribleButtonView, 1);
        configureButton(binding.badButtonView, 2);
        configureButton(binding.regularButtonView, 3);
        configureButton(binding.goodButtonView, 4);
        configureButton(binding.excellentButtonView, 5);
    }

    private void configureButton(final ConstraintLayout constraintLayout, final int note) {
        constraintLayout.setClickable(true);
        constraintLayout.setOnClickListener(v -> {
            saveAssessment(criteria, note);
            navigateToThanks();
        });
    }

    private void saveAssessment(final Criteria criteria, final int note) {
        criteria.addAssessment(note);
        criteriaRepository.update(criteria);
    }

    private void navigateToThanks() {
        getNavController().navigate(R.id.nav_to_thanks);
    }

    @Override
    public int getNavHostFragmentId() {
        return R.id.nav_host_fragment_content_main_of_execution_activity;
    }
}