package online.meavalia.ui.criteria;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import online.meavalia.R;
import online.meavalia.databinding.ActivityMainBinding;
import online.meavalia.domain.model.Criteria;

public class CriteriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.simple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_settings) {
            final NavController navController = Navigation
                    .findNavController(this, R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_about);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation
                .findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_transform);
        return true;
    }

    private Criteria loadAssessmentToContinueExecution() {
        final SharedPreferences shared = getSharedPreferences("online.meavalia.ui.criteria.FILE", Context.MODE_PRIVATE);
        final String criteriaId = shared.getString("criteria", null);

        // TODO load criteria here if exists

        return null;
    }
}