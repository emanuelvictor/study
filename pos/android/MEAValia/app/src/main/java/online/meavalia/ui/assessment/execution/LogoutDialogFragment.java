package online.meavalia.ui.assessment.execution;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import online.meavalia.databinding.LogoutDialogBinding;

public class LogoutDialogFragment extends DialogFragment {
    private static final String DEFAULT_PASSWORD_TO_LOGOUT = "129000";
    private LogoutDialogBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = LogoutDialogBinding.inflate(inflater, container, false);

        configureLoginToLogout();

        return binding.getRoot();
    }

    private void configureLoginToLogout() {
        final EditText passwordLogout = binding.passwordLogout;
        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals(DEFAULT_PASSWORD_TO_LOGOUT))
                    requireActivity().finish();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        passwordLogout.addTextChangedListener(textWatcher);
    }

}