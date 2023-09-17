package com.example.effectivemobiletest.helpers

object TouristData {
    enum class TouristNumber {
        Первый, Второй, Третий, Четвертый, Пятый, Шестой, Седьмой, Восьмой, Девятый, Десятый, Следующий
    }

    fun getNumber(index: Int): TouristNumber {
        return if (index in 0..9) TouristNumber.values()[index]
        else TouristNumber.values()[10]
    }
}