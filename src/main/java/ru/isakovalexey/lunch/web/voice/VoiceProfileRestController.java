package ru.isakovalexey.lunch.web.voice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.isakovalexey.lunch.AuthorizedUser;
import ru.isakovalexey.lunch.service.VoiceService;

/**
 * Created by user on 18.06.2017.
 */
@RestController
@RequestMapping(value = VoiceProfileRestController.REST_URL)
public class VoiceProfileRestController {
    private static final Logger log = LoggerFactory.getLogger(VoiceProfileRestController.class);

    static final String REST_URL = "/rest/profile/restaurants";

    @Autowired
    VoiceService service;

    @PostMapping(value = "/{id}")
    public void voice(@PathVariable("id") int restaurantId, @RequestParam("voice") boolean voice) {
        log.info((voice ? "Voice for a restaurant " : "remove the voice ") + restaurantId);
        int userId = AuthorizedUser.id();
        service.voice(restaurantId, voice, userId);
    }
}
