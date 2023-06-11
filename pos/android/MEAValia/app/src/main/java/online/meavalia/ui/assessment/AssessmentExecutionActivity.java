package online.meavalia.ui.assessment;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.window.OnBackInvokedDispatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import online.meavalia.R;
import online.meavalia.ui.assessment.execution.LogoutDialogFragment;

public class AssessmentExecutionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_main_of_execution_activity);

        configureFullscreen();
        configureBackAction();
    }

    private void configureBackAction() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(
                    OnBackInvokedDispatcher.PRIORITY_DEFAULT,
                    () -> {
                        final DialogFragment dialog = new LogoutDialogFragment();
                        dialog.show(getSupportFragmentManager(), "LogoutDialogFragment");
                    }
            );
        }
    }

    private void configureFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_IMMERSIVE |
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                    View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}