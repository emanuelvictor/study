package online.meavalia.domain.converters;


import androidx.room.TypeConverter;

import java.math.BigDecimal;

public class BigDecimalConverter  {
    @TypeConverter
    public BigDecimal doubleToBigDecimal(Double value) {
        return value == null ? null : new BigDecimal(value);
    }

    @TypeConverter
    public Double bigDecimalToDouble(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        } else {
            return bigDecimal.doubleValue();
        }
    }
}