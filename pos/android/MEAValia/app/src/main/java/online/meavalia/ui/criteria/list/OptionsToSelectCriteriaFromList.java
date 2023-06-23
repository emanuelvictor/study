package online.meavalia.ui.criteria.list;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import online.meavalia.R;
import online.meavalia.domain.model.Criteria;
import online.meavalia.ui.assessment.AssessmentExecutionActivity;

public class OptionsToSelectCriteriaFromList implements ActionMode.Callback {

    private final Criteria criteria;
    private final AppCompatActivity appCompatActivity;
    private ActionMode mode;

    public OptionsToSelectCriteriaFromList(Criteria criteria, AppCompatActivity appCompatActivity) {
        this.criteria = criteria;
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        this.mode = mode;
        MenuInflater inflate = mode.getMenuInflater();
        inflate.inflate(R.menu.criteria_selected, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if (item.getItemId() == R.id.menuItemUpdateCriteria) {
            Toast.makeText(appCompatActivity, "Update " + criteria.getName(), Toast.LENGTH_LONG).show();
            mode.finish();
            return true;
        } else if (item.getItemId() == R.id.menuItemUpdateRemoveCriteria) {
            Toast.makeText(appCompatActivity, "Remover " + criteria.getName(), Toast.LENGTH_LONG).show();
            mode.finish();
            return true;
        } else if (item.getItemId() == R.id.menuItemExecuteAssessment) {
            startAssessmentActivity(criteria);
            mode.finish();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
    }

    void finish() {
        mode.finish();
    }

    private void startAssessmentActivity(final Criteria criteria) {
        final Intent intent = new Intent(appCompatActivity, AssessmentExecutionActivity.class);
        intent.putExtra("criteria", criteria);
        appCompatActivity.startActivity(intent);
    }
}
