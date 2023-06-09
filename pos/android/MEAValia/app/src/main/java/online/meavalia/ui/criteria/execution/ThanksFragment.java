package online.meavalia.ui.criteria.execution;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import online.meavalia.R;
import online.meavalia.databinding.ThanksBinding;

public class ThanksFragment extends Fragment {

    private ThanksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ThanksBinding.inflate(inflater, container, false);

        configureThanksTextView();
        configureBackToListOfCriteriaTimeout();

        return binding.getRoot();
    }

    private void configureThanksTextView() {
        binding.sentenceNameTextView.setText(R.string.thanks_sentence);
    }

    private void configureBackToListOfCriteriaTimeout() {
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.schedule(this::navigateToSelectNotes, 5000, TimeUnit.MILLISECONDS);
        executor.shutdown();
    }

    private void navigateToSelectNotes() {
        getNavController().navigate(R.id.nav_to_select_notes);
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