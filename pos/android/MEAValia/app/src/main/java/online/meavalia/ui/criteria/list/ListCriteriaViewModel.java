package online.meavalia.ui.criteria.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import online.meavalia.domain.model.Assessment;
import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.repository.AssessmentRepository;
import online.meavalia.domain.repository.CriteriaRepository;
import online.meavalia.infrastructure.repository.impl.AssessmentRepositoryImpl;
import online.meavalia.infrastructure.repository.impl.CriteriaRepositoryImpl;

public class ListCriteriaViewModel extends ViewModel {

    private final MutableLiveData<List<Criteria>> mCriterias;
    private final CriteriaRepository criteriaRepository = new CriteriaRepositoryImpl();

    public ListCriteriaViewModel() {
        mCriterias = new MutableLiveData<>();
    }

    public LiveData<List<Criteria>> getCriterias() {
        final List<Criteria> listOfCriterias = new ArrayList<>(criteriaRepository.getAll());
        mCriterias.setValue(new ArrayList<>(listOfCriterias));
        return mCriterias;
    }
}