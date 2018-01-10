package ru.isakovalexey.lunch;

import ru.isakovalexey.lunch.matcher.ModelMatcher;
import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.model.Voice;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class VoiceTestData {
    public static final ModelMatcher<Voice> MATCHER = ModelMatcher.of(Voice.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getRestaurantId(), actual.getRestaurantId())
                            && Objects.equals(expected.getUserId(), actual.getUserId())
                    )
    );

    public static void assertMatch(Voice actual, Voice expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "user", "date");
    }

    public static void assertMatch(Iterable<Voice> actual, Voice... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Voice> actual, Iterable<Voice> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "user", "date").isEqualTo(expected);
    }
}
