package ru.isakovalexey.lunch;

import ru.isakovalexey.lunch.matcher.ModelMatcher;
import ru.isakovalexey.lunch.model.Meal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
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

    public static final int BLACK_THAI_MEAL_ID = START_SEQ + 5;
    public static final int WHITE_RABBIT_MEAL_ID = START_SEQ + 8;
    public static final int UGOLEK_MEAL_ID = START_SEQ + 11;

    public static final Meal BLACK_THAI_MEAL1 = new Meal(BLACK_THAI_MEAL_ID, "борщ", new BigDecimal("500.00"), getDate(), BLACK_THAI_ID);
    public static final Meal BLACK_THAI_MEAL2 = new Meal(BLACK_THAI_MEAL_ID + 1, "каша гречневая", new BigDecimal("300.00"), getDate(), BLACK_THAI_ID);
    public static final Meal BLACK_THAI_MEAL3 = new Meal(BLACK_THAI_MEAL_ID + 2, "котлета кур", new BigDecimal("700.00"), getDate(), BLACK_THAI_ID);
    public static final Meal WHITE_RABBIT_MEAL1 = new Meal(WHITE_RABBIT_MEAL_ID, "омлет со спаржей", new BigDecimal("500.00"), getDate(), WHITE_RABBIT_ID);
    public static final Meal WHITE_RABBIT_MEAL2 = new Meal(WHITE_RABBIT_MEAL_ID + 1, "салат по сезону", new BigDecimal("100.00"), getDate(), WHITE_RABBIT_ID);
    public static final Meal WHITE_RABBIT_MEAL3 = new Meal(WHITE_RABBIT_MEAL_ID + 2, "каша молочная", new BigDecimal("500.00"), getDate(), WHITE_RABBIT_ID);
    public static final Meal UGOLEK_MEAL1 = new Meal(UGOLEK_MEAL_ID, "суп фасолевый", new BigDecimal("200.00"), getDate(), UGOLEK_ID);
    public static final Meal UGOLEK_MEAL2 = new Meal(UGOLEK_MEAL_ID + 1, "рыба запеченная", new BigDecimal("1000.00"), getDate(), UGOLEK_ID);
    public static final Meal UGOLEK_MEAL3 = new Meal(UGOLEK_MEAL_ID + 2, "суп -харчо", new BigDecimal("300.00"), getDate(), UGOLEK_ID);

    public static final List<Meal> WHITE_RABBIT_MEALS = Arrays.asList(WHITE_RABBIT_MEAL2, WHITE_RABBIT_MEAL1, WHITE_RABBIT_MEAL3);

    public static Meal getCreated() {
        return new Meal(null, "новый суп -харчо", new BigDecimal("300.00"), getDate(), BLACK_THAI_ID);
    }

    public static Meal getUpdated() {
        return new Meal(BLACK_THAI_MEAL_ID, "Обновленный борщ", new BigDecimal("200.00"), BLACK_THAI_MEAL1.getDate(), BLACK_THAI_ID);
    }

    public static LocalDate getDate() {
        return LocalDate.of(2017,5,30);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}