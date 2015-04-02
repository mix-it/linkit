package controllers.api;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import helpers.JSON;
import helpers.Models;
import models.*;
import models.api.dto.*;

public abstract class AbstractMemberToDTOVisitor implements MemberToJsonVisitor {

    private void initCommon(Member member, AbstractMemberDTO dto) {
        dto.setCompany(member.company);
        dto.setFirstName(member.firstname);
        dto.setId(member.id);
        dto.setLastName(member.lastname);
        dto.setLogin(member.login);
        dto.setLongdesc(member.longDescription);
        dto.setNbConsults(member.nbConsults);
        dto.setShortdesc(member.shortDescription);
        dto.setUrlimage(member.getUrlImage());

        dto.setSharedLinks(Lists.transform(member.sharedLinks, new Function<SharedLink, SharedLinkDTO>() {
            @Override
            public SharedLinkDTO apply(SharedLink sharedLink) {
                SharedLinkDTO linkDTO = new SharedLinkDTO();
                linkDTO.setName(sharedLink.name);
                linkDTO.setUrl(sharedLink.URL);
                return linkDTO;
            }
        }));
    }

    protected void initSimpleMember(Member member, MemberSimpleDTO dto) {
        initCommon(member, dto);
        dto.setInterests(JSON.nullify(FluentIterable.from(member.interests).transform(Models.toId()).toImmutableSet()));
        dto.setSessions(JSON.nullify(FluentIterable.from(member.getValidatedTalks()).transform(Models.toId()).toImmutableSet()));
    }

    protected void initDetailedMember(Member member, MemberDetailedDTO dto) {

        initCommon(member, dto);

        dto.setInterests(JSON.nullify(FluentIterable.from(member.interests).transform(new Function<Interest, InterestDTO>() {
            @Override
            public InterestDTO apply(Interest interest) {
                InterestDTO dto = new InterestDTO();
                dto.setId(interest.getId());
                dto.setName(interest.name);
                dto.setUrl(ApiUrl.getInterestUrl(interest.getId()));
                return dto;
            }
        }).toImmutableSet()));

        dto.setSessions(JSON.nullify(FluentIterable.from(member.getValidatedTalks()).transform(new Function<Session, SpeakerSessionDTO>() {
            @Override
            public SpeakerSessionDTO apply(Session session) {
                SpeakerSessionDTO dto = new SpeakerSessionDTO();
                dto.setDescription(session.description);
                dto.setId(session.getId());
                dto.setLanguage(session.lang);
                dto.setSummary(session.summary);
                dto.setTitle(session.title);
                return dto;
            }
        }).toImmutableSet()));
    }
}
