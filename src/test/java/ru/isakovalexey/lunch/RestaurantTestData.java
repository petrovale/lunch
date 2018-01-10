package ru.isakovalexey.lunch;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.to.RestaurantTo;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.isakovalexey.lunch.model.BaseEntity.START_SEQ;
import static ru.isakovalexey.lunch.web.json.JsonUtil.writeIgnoreProps;

public class RestaurantTestData {

    public static final int BLACK_THAI_ID = START_SEQ + 2;
    public static final int WHITE_RABBIT_ID = START_SEQ + 3;
    public static final int UGOLEK_ID = START_SEQ + 4;

    public static final Restaurant BLACK_THAI = new Restaurant(BLACK_THAI_ID, "Black Thai");
    public static final Restaurant WHITE_RABBIT = new Restaurant(WHITE_RABBIT_ID, "White Rabbit");
    public static final Restaurant UGOLEK = new Restaurant(UGOLEK_ID, "Уголек");

    public static final RestaurantTo BLACK_THAI_TO = new RestaurantTo(BLACK_THAI_ID, "Black Thai", 0);
    public static final RestaurantTo WHITE_RABBIT_TO = new RestaurantTo(WHITE_RABBIT_ID, "White Rabbit", 0);
    public static final RestaurantTo UGOLEK_TO = new RestaurantTo(UGOLEK_ID, "Уголек", 0);

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "voices", "meals");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "voices", "meals").isEqualTo(expected);
    }

    public static void assertMatchTo(Iterable<RestaurantTo> actual, RestaurantTo... expected) {
        assertMatchTo(actual, Arrays.asList(expected));
    }

    public static void assertMatchTo(Iterable<RestaurantTo> actual, Iterable<RestaurantTo> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "voices", "meals").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Restaurant... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "registered"));
    }

    public static ResultMatcher contentJson(Restaurant expected) {
        return content().json(writeIgnoreProps(expected, "registered"));
    }

    public static ResultMatcher contentJson(RestaurantTo... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "registered"));
    }

    public static ResultMatcher contentJson(RestaurantTo expected) {
        return content().json(writeIgnoreProps(expected, "registered"));
    }
}
