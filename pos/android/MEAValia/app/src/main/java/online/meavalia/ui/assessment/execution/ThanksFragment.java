package online.meavalia.ui.assessment.execution;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import online.meavalia.R;
import online.meavalia.databinding.ThanksBinding;
import online.meavalia.ui.assessment.AssessmentExecutionActivity;

public class ThanksFragment extends Fragment {

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

    // TODO centralizar
    private NavController getNavController() {
        final NavHostFragment navHostFragment =
                (NavHostFragment) requireActivity()
                        .getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_content_main_of_execution_activity);
        assert navHostFragment != null;
        return navHostFragment.getNavController();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}