package online.meavalia.ui.criteria.list;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import online.meavalia.domain.model.Assessment;
import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.repository.AssessmentRepository;
import online.meavalia.domain.repository.CriteriaRepository;
import online.meavalia.infrastructure.repository.impl.AssessmentRepositoryImpl;
import online.meavalia.infrastructure.repository.impl.CriteriaRepositoryImpl;

public class ListCriteriaViewModel extends ViewModel {

    private final MutableLiveData<List<Criteria>> mCriterias;

    private final CriteriaRepository criteriaRepository = new CriteriaRepositoryImpl();

    private final AssessmentRepository assessmentRepository = new AssessmentRepositoryImpl();

    public ListCriteriaViewModel() {
        mCriterias = new MutableLiveData<>();
        final List<Criteria> listOfCriterias = new ArrayList<>(criteriaRepository.getAll());
        final List<Assessment> listOfAssessments = new ArrayList<>(assessmentRepository.getAll());

        for (int i = 0; i < listOfCriterias.size(); i++) {
            final Criteria criteria = listOfCriterias.get(i);

            BigDecimal sum = BigDecimal.ZERO;
            for (int j = 0; j < listOfAssessments.size(); j++) {
                if (listOfAssessments.get(j).getCriteria().getName().equals(criteria.getName()))
                    sum = sum.add(BigDecimal.valueOf(listOfAssessments.get(j).getNote().getValue()));
            }

            if (!sum.equals(BigDecimal.ZERO))
                criteria.setAvg(sum.divide(BigDecimal.valueOf(listOfAssessments.size()),1, RoundingMode.UNNECESSARY));
        }

        mCriterias.setValue(new ArrayList<>(listOfCriterias));
    }

    public LiveData<List<Criteria>> getCriterias() {
        return mCriterias;
    }
}