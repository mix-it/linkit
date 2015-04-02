package controllers.api;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;

import models.*;
import models.api.dto.*;

public class ApiMembers extends JsonpController {

    private static final MemberToJsonVisitor SIMPLE_MEMBER_VISITOR = new AbstractMemberToDTOVisitor() {

        @Override
        public AbstractMemberDTO visit(Member member) {
            MemberSimpleDTO dto = new MemberSimpleDTO();
            initSimpleMember(member, dto);
            return dto;
        }

        @Override
        public AbstractMemberDTO visit(Staff staff) {
            MemberSimpleDTO dto = new MemberSimpleDTO();
            initSimpleMember(staff, dto);
            return dto;
        }

        @Override
        public AbstractMemberDTO visit(Sponsor sponsor) {
            SponsorSimpleDTO dto = new SponsorSimpleDTO();
            initSimpleMember(sponsor, dto);

            dto.setLevel(sponsor.level);
            dto.setLogo(ApiUrl.getFullUrl(sponsor.logoUrl));
            return dto;
        }
    };

    private static final MemberToJsonVisitor DETAILED_MEMBER_VISITOR = new AbstractMemberToDTOVisitor() {

        @Override
        public AbstractMemberDTO visit(Member member) {
            MemberDetailedDTO dto = new MemberDetailedDTO();
            initDetailedMember(member, dto);
            return dto;
        }

        @Override
        public AbstractMemberDTO visit(Staff staff) {
            MemberDetailedDTO dto = new MemberDetailedDTO();
            initDetailedMember(staff, dto);
            return dto;
        }

        @Override
        public AbstractMemberDTO visit(Sponsor sponsor) {
            SponsorDetailedDTO dto = new SponsorDetailedDTO();
            initDetailedMember(sponsor, dto);

            dto.setLevel(sponsor.level);
            dto.setLogo(ApiUrl.getFullUrl(sponsor.logoUrl));
            return dto;
        }
    };

    private static Function<Member, AbstractMemberDTO> toMemberDTO(boolean details) {
        final MemberToJsonVisitor visitor = details ? DETAILED_MEMBER_VISITOR : SIMPLE_MEMBER_VISITOR;
        return new Function<Member, AbstractMemberDTO>() {
            @Override
            public AbstractMemberDTO apply(Member member) {
                return member.accept(visitor);
            }
        };
    }

    private static AbstractMemberDTO toMemberDTO(Member member, boolean details) {
        final MemberToJsonVisitor visitor = details ? DETAILED_MEMBER_VISITOR : SIMPLE_MEMBER_VISITOR;
        return member.accept(visitor);
    }

    private static Iterable<AbstractMemberDTO> toMembersDTO(List<? extends Member> members, boolean details) {
        return Lists.transform(members, toMemberDTO(details));
    }


    public static void speakers(boolean details) {
        List<Member> speakers = Talk.findAllSpeakersOn(ConferenceEvent.CURRENT);
        renderJSON(toMembersDTO(speakers, details));
    }

    public static void sponsors(boolean details) {
        List<Sponsor> sponsors = Sponsor.findOn(ConferenceEvent.CURRENT);
        renderJSON(toMembersDTO(sponsors, details));
    }

    public static void staff(boolean details) {
        List<Staff> staff = Staff.findAll();
        renderJSON(toMembersDTO(staff, details));
    }

    public static void members(boolean details) {
        List<Member> members = Member.findAll();
        renderJSON(toMembersDTO(members, details));
    }

    public static void member(long id, boolean details) {
        Member member = Member.findById(id);
        member(member, details);
    }

    public static void memberByLogin(String login, boolean details) {
        Member member = Member.findByLogin(login);
        member(member, details);
    }

    private static void member(Member member, boolean details) {
        notFoundIfNull(member);
        renderJSON(toMemberDTO(member, details));
    }

    public static void favorites(long memberId) {
        Member member = Member.findById(memberId);
        favorites(member);
    }

    public static void favoritesByLogin(String memberLogin) {
        Member member = Member.findByLogin(memberLogin);
        favorites(member);
    }

    private static void favorites(Member member) {
        notFoundIfNull(member);
        List<Talk> favorites = Vote.findFavoriteTalksByMemberOn(member, ConferenceEvent.CURRENT);

        renderJSON(ApiSessions.toSessionsDTO(favorites, false));
    }
}