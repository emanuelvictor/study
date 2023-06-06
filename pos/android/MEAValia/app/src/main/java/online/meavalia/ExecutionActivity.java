package online.meavalia;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import online.meavalia.domain.model.Criteria;

public class ExecutionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.execution_activity);

        configureAssessmentSentence();
        configureLabels();
        configureEmojis();
        configureFullscreen();
    }

    private void configureAssessmentSentence(){
        final Criteria criteria = (Criteria) getIntent().getSerializableExtra("criteria");
        final TextView textView = findViewById(R.id.sentence_name_text_view);
        textView.setText(criteria.getSentence());
    }

    private void configureLabels() {
        configureLabels(R.id.terrible_name_text_view, R.string.terrible);
        configureLabels(R.id.bad_name_text_view, R.string.bad);
        configureLabels(R.id.regular_name_text_view, R.string.regular);
        configureLabels(R.id.good_name_text_view, R.string.good);
        configureLabels(R.id.excellent_name_text_view, R.string.excellent);
    }

    private void configureLabels(int textViewId, int stringId) {
        final TextView textView = findViewById(textViewId);
        textView.setText(stringId);
    }

    private void configureEmojis() {
        configureEmoji(R.id.terrible_name_image_view, R.drawable.terrible, R.color.terrible_color);
        configureEmoji(R.id.bad_name_image_view, R.drawable.bad, R.color.bad_color);
        configureEmoji(R.id.regular_name_image_view, R.drawable.regular, R.color.regular_color);
        configureEmoji(R.id.good_name_image_view, R.drawable.good, R.color.good_color);
        configureEmoji(R.id.excellent_name_image_view, R.drawable.excellent, R.color.excellent_color);
    }

    private void configureEmoji(int imageViewId, int drawableId, int colorId) {
        final ImageView imageView = findViewById(imageViewId);
        final Drawable pessimoIcon = ContextCompat.getDrawable(this, drawableId);
        assert pessimoIcon != null;
        pessimoIcon.setColorFilter(ContextCompat.getColor(this, colorId), PorterDuff.Mode.SRC_IN);
        imageView.setBackground(pessimoIcon);
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

}