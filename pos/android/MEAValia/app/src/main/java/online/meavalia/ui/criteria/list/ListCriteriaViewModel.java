package online.meavalia.ui.criteria.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.repository.CriteriaRepository;
import online.meavalia.infrastructure.repository.impl.CriteriaRepositoryImpl;

public class ListCriteriaViewModel extends ViewModel {

    private final MutableLiveData<List<Criteria>> mCriterias;

    private final CriteriaRepository criteriaRepository = new CriteriaRepositoryImpl();

    public ListCriteriaViewModel() {
        mCriterias = new MutableLiveData<>();
        final List<Criteria> texts = criteriaRepository.getAll();
        mCriterias.setValue(texts);
    }

    public LiveData<List<Criteria>> getCriterias() {
        return mCriterias;
    }
}