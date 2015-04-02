package models.api.dto;

import java.util.Set;

public class SessionSimpleDTO extends AbstractSessionDTO {

    private Set<Long> interests;
    private Set<Long> speakers;

    public Set<Long> getInterests() {
        return interests;
    }

    public void setInterests(Set<Long> interests) {
        this.interests = interests;
    }

    public Set<Long> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<Long> speakers) {
        this.speakers = speakers;
    }
}
