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

        configureBackButton();
        configureLabels();
        configureLogo();

        return binding.getRoot();
    }

    private void configureLabels(){
        binding.studentNameLabel.setText("Student name: ");
        binding.studentEmailLabel.setText("Student e-mail: ");
        binding.appDescriptionLabel.setText("APP description: ");
        binding.institutionNameLabel.setText("Institution name: ");

        binding.studentNameValue.setText("Emanuel Victor de Oliveira Fonseca");
        binding.studentEmailValue.setText("emanuel.info@gmail.com");
        binding.appDescriptionValue.setText("This application create assessments and allow it to be executed by users.");
        binding.institutionNameValue.setText("Universidade Tecnológica Federal do Paraná");
    }

    private void configureLogo(){
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