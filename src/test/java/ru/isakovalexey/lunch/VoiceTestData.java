package ru.isakovalexey.lunch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.isakovalexey.lunch.matcher.ModelMatcher;
import ru.isakovalexey.lunch.model.Voice;

import java.util.Objects;

/**
 * Created by user on 21.06.2017.
 */
public class VoiceTestData {
    public static final ModelMatcher<Voice> MATCHER = ModelMatcher.of(Voice.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getRestaurantId(), actual.getRestaurantId())
                            && Objects.equals(expected.getUserId(), actual.getUserId())
                    )
    );
}
