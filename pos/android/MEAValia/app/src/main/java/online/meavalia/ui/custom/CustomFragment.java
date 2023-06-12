package online.meavalia.ui.custom;

import androidx.navigation.NavController;

public interface CustomFragment {

    int getNavHostFragmentId();

    NavController getNavController();
}
