package ru.isakovalexey.lunch;

import ru.isakovalexey.lunch.matcher.ModelMatcher;
import ru.isakovalexey.lunch.model.Meal;
import sun.util.resources.LocaleData;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static ru.isakovalexey.lunch.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = ModelMatcher.of(Meal.class,
            (expected, actual) -> expected == actual ||
            (Objects.equals(expected.getId(), actual.getId())
                    && Objects.equals(expected.getDate(), actual.getDate())
                    && Objects.equals(expected.getDescription(), actual.getDescription())
                    && Objects.equals(expected.getPrice(), actual.getPrice())
            )
    );

    public static final int BLACK_THAI_ID = START_SEQ + 2;
    public static final int WHITE_RABBIT_ID = START_SEQ + 3;
    public static final int UGOLEK_ID = START_SEQ + 4;

    public static final int Black_Thai_MEAL_ID = START_SEQ + 5;
    public static final int White_Rabbit_MEAL_ID = START_SEQ + 8;
    public static final int Ugolek_MEAL_ID = START_SEQ + 11;

    public static final Meal Black_Thai_MEAL1 = new Meal(Black_Thai_MEAL_ID, "борщ", new BigDecimal("500.00"), getDate());
    public static final Meal Black_Thai_MEAL2 = new Meal(Black_Thai_MEAL_ID + 1, "каша гречневая", new BigDecimal("300.00"), getDate());
    public static final Meal Black_Thai_MEAL3 = new Meal(Black_Thai_MEAL_ID + 2, "котлета кур", new BigDecimal("700.00"), getDate());
    public static final Meal White_Rabbit_MEAL1 = new Meal(White_Rabbit_MEAL_ID, "омлет со спаржей", new BigDecimal("500.00"), getDate());
    public static final Meal White_Rabbit_MEAL2 = new Meal(White_Rabbit_MEAL_ID + 1, "салат по сезону", new BigDecimal("100.00"), getDate());
    public static final Meal White_Rabbit_MEAL3 = new Meal(White_Rabbit_MEAL_ID + 2, "каша молочная", new BigDecimal("500.00"), getDate());
    public static final Meal Ugolek_MEAL1 = new Meal(Ugolek_MEAL_ID, "суп фасолевый", new BigDecimal("200.00"), getDate());
    public static final Meal Ugolek_MEAL2 = new Meal(Ugolek_MEAL_ID + 1, "рыба запеченная", new BigDecimal("1000.00"), getDate());
    public static final Meal Ugolek_MEAL3 = new Meal(Ugolek_MEAL_ID + 2, "суп -харчо", new BigDecimal("300.00"), getDate());

    public static final List<Meal> WHITE_RABBIT_MEALS = Arrays.asList(White_Rabbit_MEAL2, White_Rabbit_MEAL1, White_Rabbit_MEAL3);

    public static Meal getCreated() {
        return new Meal(null, "новый суп -харчо", new BigDecimal("300.00"), getDate());
    }

    public static Meal getUpdated() {
        return new Meal(Black_Thai_MEAL_ID, "Обновленный борщ", new BigDecimal("200.00"), Black_Thai_MEAL1.getDate());
    }

    public static Date getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = sdf.parse("2017-05-30");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}