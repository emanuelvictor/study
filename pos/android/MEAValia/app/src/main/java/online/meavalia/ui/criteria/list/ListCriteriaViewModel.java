package online.meavalia.ui.criteria.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.repository.CriteriaRepository;

public class ListCriteriaViewModel extends ViewModel {

    private final MutableLiveData<List<Criteria>> mCriterias = new MutableLiveData<>();

    public LiveData<List<Criteria>> getCriterias(final CriteriaRepository criteriaRepository) {
        final List<Criteria> listOfCriterias = new ArrayList<>(criteriaRepository.getAll());
        mCriterias.setValue(new ArrayList<>(listOfCriterias));
        return mCriterias;
    }
}