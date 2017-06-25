package ru.isakovalexey.lunch.web.voice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.isakovalexey.lunch.AuthorizedUser;
import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.service.VoiceService;

import java.net.URI;

@RestController
@RequestMapping(value = VoiceProfileRestController.REST_URL)
public class VoiceProfileRestController {
    private static final Logger log = LoggerFactory.getLogger(VoiceProfileRestController.class);

    static final String REST_URL = "/rest/profile/restaurants";

    private VoiceService service;

    @Autowired
    public VoiceProfileRestController(VoiceService service) {
        this.service = service;
    }

    @PostMapping(value = "/{id}/vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Voice> voice(@PathVariable("id") int restaurantId) {
        log.info("Voice for a restaurant " + restaurantId);

        int userId = AuthorizedUser.id();
        Voice vote = service.voice(restaurantId, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(vote.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(vote);
    }
}
