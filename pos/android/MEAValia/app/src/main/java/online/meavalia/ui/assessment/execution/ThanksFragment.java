package online.meavalia.ui.assessment.execution;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import online.meavalia.R;
import online.meavalia.databinding.ThanksBinding;
import online.meavalia.ui.generic.AbstractCustomFragmentImpl;

public class ThanksFragment extends AbstractCustomFragmentImpl {

    private ThanksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ThanksBinding.inflate(inflater, container, false);

        configureThanksTextView();
        configureBackToListOfCriteriaTimeout();

        return binding.getRoot();
    }

    public void configureBackToListOfCriteriaTimeout() {
        final Handler handler = new Handler();
        handler.postDelayed(this::navigateToSelectNotes, 3000);
    }

    private void configureThanksTextView() {
        binding.sentenceNameTextView.setText(R.string.thanks_sentence);
    }

    private void navigateToSelectNotes() {
        getNavController().navigate(R.id.nav_to_select_notes);
    }

    @Override
    public int getNavHostFragmentId() {
        return R.id.nav_host_fragment_content_main_of_execution_activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}