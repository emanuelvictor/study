package online.meavalia.ui.custom;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import online.meavalia.R;

public abstract class AbstractCustomFragmentImpl extends Fragment implements CustomFragment {




    @Override
    public NavController getNavController() {
        final NavHostFragment navHostFragment =
                (NavHostFragment) requireActivity()
                        .getSupportFragmentManager()
                        .findFragmentById(getNavHostFragmentId());
        assert navHostFragment != null;
        return navHostFragment.getNavController();
    }
}
