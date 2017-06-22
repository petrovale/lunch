package ru.isakovalexey.lunch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.isakovalexey.lunch.matcher.ModelMatcher;
import ru.isakovalexey.lunch.model.Restaurant;

import java.util.Objects;

import static ru.isakovalexey.lunch.model.BaseEntity.START_SEQ;

/**
 * Created by user on 21.06.2017.
 */
public class RestaurantTestData {

    public static final int BLACK_THAI_ID = START_SEQ + 2;
    public static final int WHITE_RABBIT_ID = START_SEQ + 3;
    public static final int UGOLEK_ID = START_SEQ + 4;

    public static final Restaurant BLACK_THAI = new Restaurant(BLACK_THAI_ID, "Black Thai", 0);
    public static final Restaurant WHITE_RABBIT = new Restaurant(WHITE_RABBIT_ID, "White Rabbit", 0);
    public static final Restaurant UGOLEK = new Restaurant(UGOLEK_ID, "Уголек", 0);

    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getVote(), actual.getVote())
                    )
    );
}
