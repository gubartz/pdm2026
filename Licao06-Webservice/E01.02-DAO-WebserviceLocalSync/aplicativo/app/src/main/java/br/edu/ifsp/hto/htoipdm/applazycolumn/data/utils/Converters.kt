package br.edu.ifsp.hto.htoipdm.applazycolumn.data.utils

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): Int? {
        return date?.let {
            it.year * 10000 +
                    it.monthValue * 100 +
                    it.dayOfMonth
        }
    }

    @TypeConverter
    fun toLocalDate(value: Int?): LocalDate? {
        if (value == null) return null

        val year = value / 10000
        val month = (value % 10000) / 100
        val day = value % 100

        return LocalDate.of(year, month, day)
    }
}