package online.meavalia.ui.about;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Objects;

import online.meavalia.R;
import online.meavalia.databinding.AboutBinding;
import online.meavalia.ui.generic.AbstractCustomFragmentImpl;

public class AboutFragment extends AbstractCustomFragmentImpl {

    private AboutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = AboutBinding.inflate(inflater, container, false);

        configureTitle();
        configureBackButton();
        configureLabels();
        configureLogo();

        return binding.getRoot();
    }

    private void configureTitle() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setTitle(R.string.about);
    }

    private void configureLabels() {
        binding.studentNameLabel.setText(R.string.student_name_label);
        binding.studentEmailLabel.setText(R.string.student_email_label);
        binding.appDescriptionLabel.setText(R.string.app_description_label);
        binding.institutionNameLabel.setText(R.string.instution_name_label);

        binding.studentNameValue.setText(R.string.student_name_value);
        binding.studentEmailValue.setText(R.string.student_email_value);
        binding.appDescriptionValue.setText(R.string.app_description_value);
        binding.institutionNameValue.setText(R.string.instution_name_value);
    }

    private void configureLogo() {
        configureLogo(binding.institutionLogoValue, R.drawable.utfpr_logo);
    }

    private void configureLogo(final ImageView imageView, int drawableId) {
        final Drawable icon = ContextCompat.getDrawable(requireActivity(), drawableId);
        imageView.setBackground(icon);
    }

    private void configureBackButton() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private AppCompatActivity getMainActivity() {
        return ((AppCompatActivity) getActivity());
    }

    @Override
    public int getNavHostFragmentId() {
        return R.id.nav_host_fragment_content_main;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}