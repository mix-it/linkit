package controllers.api;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import helpers.JSON;
import helpers.Models;
import models.*;
import models.api.dto.*;
import models.planning.PlanedSlot;

public abstract class AbstractSessionToDTOVisitor implements SessionToJsonVisitor {

    private void initCommon(Session session, AbstractSessionDTO dto) {
        dto.setDescription(session.description);
        dto.setId(session.id);
        dto.setLanguage(session.lang);
        dto.setSummary(session.summary);
        dto.setTitle(session.title);
        dto.setIdeaForNow(session.ideaForNow);
    }

    protected void initSlot(PlanedSlot slot, AbstractSessionDTO dto) {
        initCommon(slot.talk, dto);
        if (slot.slot != null) {
            dto.setStart(slot.slot.getStartDateTime().toString());
            dto.setEnd(slot.slot.getEndDateTime().toString());
            dto.setRoom(slot.slot.getRoom().toString());
        }
    }

    protected void initSimpleSession(Session session, SessionSimpleDTO dto) {
        initCommon(session, dto);
        dto.setInterests(JSON.nullify(FluentIterable.from(session.interests).transform(Models.toId()).toImmutableSet()));
        dto.setSpeakers(JSON.nullify(FluentIterable.from(session.speakers).transform(Models.toId()).toImmutableSet()));
    }

    protected void initDetailedSession(final Session session, SessionDetailedDTO dto) {

        initCommon(session, dto);

        dto.setInterests(JSON.nullify(FluentIterable.from(session.interests).transform(new Function<Interest, InterestDTO>() {
            @Override
            public InterestDTO apply(Interest interest) {
                InterestDTO dto = new InterestDTO();
                dto.setId(interest.getId());
                dto.setName(interest.name);
                dto.setUrl(ApiUrl.getInterestUrl(interest.getId()));
                return dto;
            }
        }).toImmutableSet()));

        dto.setSpeakers(JSON.nullify(FluentIterable.from(session.speakers).transform(new Function<Member, SessionSpeakerDTO>() {
            @Override
            public SessionSpeakerDTO apply(Member speaker) {
                SessionSpeakerDTO dto = new SessionSpeakerDTO();
                dto.setFirstname(speaker.firstname);
                dto.setId(speaker.id);
                dto.setLastname(speaker.lastname);
                dto.setUrl(ApiUrl.getMemberUrl(speaker.id));
                dto.setUrlimage(speaker.getUrlImage());
                return dto;
            }
        }).toImmutableSet()));
    }
}
