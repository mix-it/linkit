package models.api.dto;

import java.util.Set;

public class SessionDetailedDTO extends AbstractSessionDTO {

    private Set<InterestDTO> interests;
    private Set<SessionSpeakerDTO> speakers;

    public Set<InterestDTO> getInterests() {
        return interests;
    }

    public void setInterests(Set<InterestDTO> interests) {
        this.interests = interests;
    }

    public Set<SessionSpeakerDTO> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<SessionSpeakerDTO> speakers) {
        this.speakers = speakers;
    }
}
