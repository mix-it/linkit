package models.api.dto;

import java.util.Set;

public class MemberDetailedDTO extends AbstractMemberDTO {

    private Set<InterestDTO> interests;
    private Set<SpeakerSessionDTO> sessions;

    public Set<InterestDTO> getInterests() {
        return interests;
    }

    public void setInterests(Set<InterestDTO> interests) {
        this.interests = interests;
    }

    public Set<SpeakerSessionDTO> getSessions() {
        return sessions;
    }

    public void setSessions(Set<SpeakerSessionDTO> sessions) {
        this.sessions = sessions;
    }
}
