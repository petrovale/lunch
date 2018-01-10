package ru.isakovalexey.lunch;

import ru.isakovalexey.lunch.model.Voice;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class VoiceTestData {

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
