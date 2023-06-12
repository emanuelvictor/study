package online.meavalia.ui.generic;

import androidx.navigation.NavController;

public interface CustomFragment {

    int getNavHostFragmentId();

    NavController getNavController();
}
