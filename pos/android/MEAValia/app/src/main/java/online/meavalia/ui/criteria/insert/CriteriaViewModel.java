package online.meavalia.ui.criteria.insert;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CriteriaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CriteriaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is insert criteria fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}