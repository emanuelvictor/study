package online.meavalia.ui.criterion.insert;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CriterionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CriterionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is settings fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}