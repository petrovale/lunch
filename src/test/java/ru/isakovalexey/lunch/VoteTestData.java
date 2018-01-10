package ru.isakovalexey.lunch;

import ru.isakovalexey.lunch.model.Vote;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class VoteTestData {

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "user", "date");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "user", "date").isEqualTo(expected);
    }
}
