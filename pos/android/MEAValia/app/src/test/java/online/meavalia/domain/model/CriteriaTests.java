package online.meavalia.domain.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CriteriaTests {

    @Test
    public void mustCalculateAvgToFirstNote(){
        final Criteria criteria = new Criteria("name", "sentence", Priority.HIGH_PRIORITY.getValue());
        final int firstNote = 5;
        final BigDecimal avgExpected = BigDecimal.valueOf(firstNote);

        criteria.addAssessment(firstNote);

        assertEquals(criteria.getAvg(), avgExpected);
    }

    @Test
    public void mustCalculateAvgToSecondNote(){
        final Criteria criteria = new Criteria("name", "sentence", Priority.HIGH_PRIORITY.getValue());
        final int firstNote = 5;
        criteria.addAssessment(firstNote);
        final int secondNote = 3;
        final BigDecimal avgExpected = BigDecimal.valueOf(firstNote + secondNote).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);

        criteria.addAssessment(secondNote);

        assertEquals(criteria.getAvg(), avgExpected);
    }

    @Test
    public void mustCalculateAvgToThirdNote(){
        final Criteria criteria = new Criteria("name", "sentence", Priority.HIGH_PRIORITY.getValue());
        final int firstNote = 5;
        criteria.addAssessment(firstNote);
        final int secondNote = 3;
        criteria.addAssessment(secondNote);
        final int thirdNote = 5;
        final BigDecimal avgExpected = BigDecimal.valueOf(firstNote + secondNote + thirdNote).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);

        criteria.addAssessment(thirdNote);

        assertEquals(criteria.getAvg(), avgExpected);
    }
}
