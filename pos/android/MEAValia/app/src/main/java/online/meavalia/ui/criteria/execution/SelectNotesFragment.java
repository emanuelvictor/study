package online.meavalia.ui.criteria.execution;

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
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import online.meavalia.R;
import online.meavalia.databinding.SelectNotesBinding;
import online.meavalia.domain.model.Assessment;
import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.model.Note;
import online.meavalia.domain.repository.AssessmentRepository;
import online.meavalia.infrastructure.repository.impl.AssessmentRepositoryImpl;

public class SelectNotesFragment extends Fragment {

    private Criteria criteria;
    private SelectNotesBinding binding;

    private final AssessmentRepository assessmentRepository = new AssessmentRepositoryImpl();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = SelectNotesBinding.inflate(inflater, container, false);

        configureAssessmentSentence();
        configureLabels();
        configureEmojis();
        configureButtons();

        return binding.getRoot();
    }


    private void configureAssessmentSentence() {
        criteria = (Criteria) requireActivity().getIntent().getSerializableExtra("criteria");
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
        final Drawable pessimoIcon = ContextCompat.getDrawable(requireActivity(), drawableId);
        assert pessimoIcon != null;
        pessimoIcon.setColorFilter(ContextCompat.getColor(requireActivity(), colorId), PorterDuff.Mode.SRC_IN);
        imageView.setBackground(pessimoIcon);
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

    void saveAssessment(final Criteria criteria, final int note) {
        final Assessment assessment = new Assessment(criteria, new Note(note));
        assessmentRepository.save(assessment);
    }

    private void navigateToThanks() {
        getNavController().navigate(R.id.nav_to_thanks);
    }

    private NavController getNavController() {
        final NavHostFragment navHostFragment =
                (NavHostFragment) requireActivity()
                        .getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_content_main_of_execution_activity);
        assert navHostFragment != null;
        return navHostFragment.getNavController();
    }
}