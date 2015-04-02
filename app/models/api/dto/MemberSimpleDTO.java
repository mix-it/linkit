package models.api.dto;

import java.util.Set;

public class MemberSimpleDTO extends AbstractMemberDTO {

    private Set<Long> interests;
    private Set<Long> sessions;

    public Set<Long> getInterests() {
        return interests;
    }

    public void setInterests(Set<Long> interests) {
        this.interests = interests;
    }

    public Set<Long> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Long> sessions) {
        this.sessions = sessions;
    }
}
