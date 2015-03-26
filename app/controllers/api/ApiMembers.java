package controllers.api;

import com.google.gson.JsonSerializer;

import java.util.List;

import models.ConferenceEvent;
import models.Member;
import models.Sponsor;
import models.Staff;
import models.Talk;
import models.Vote;

public class ApiMembers extends JsonpController {

    private static JsonSerializer DETAILED_SPONSOR_SERIALIZER = new SponsorJsonSerializer(true);
    private static JsonSerializer DETAILED_STAFF_SERIALIZER = new StaffJsonSerializer(true);
    private static JsonSerializer DETAILED_MEMBER_SERIALIZER = new MemberJsonSerializer(true);
    private static JsonSerializer SPONSOR_SERIALIZER = new SponsorJsonSerializer(false);
    private static JsonSerializer STAFF_SERIALIZER = new StaffJsonSerializer(false);
    private static JsonSerializer MEMBER_SERIALIZER = new MemberJsonSerializer(false);
    private static JsonSerializer TALK_SERIALIZER = new TalkJsonSerializer(true);


    public static void speakers(boolean details) {
        List<Member> speakers = Talk.findAllSpeakersOn(ConferenceEvent.CURRENT);
        renderJSON(Member.class, speakers, details ? DETAILED_MEMBER_SERIALIZER : MEMBER_SERIALIZER);
    }

    public static void sponsors(boolean details) {
        List<Sponsor> sponsors = Sponsor.findOn(ConferenceEvent.CURRENT);
        renderJSON(Sponsor.class, sponsors, details ? DETAILED_SPONSOR_SERIALIZER : SPONSOR_SERIALIZER);
    }

    public static void staff(boolean details) {
        List<Staff> staff = Staff.findAll();
        renderJSON(Staff.class, staff, details ? DETAILED_STAFF_SERIALIZER : STAFF_SERIALIZER);
    }

    public static void members(boolean details) {
        List<Member> members = Member.findAll();
        renderJSON(Member.class, members, details ? DETAILED_MEMBER_SERIALIZER : MEMBER_SERIALIZER);
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
        renderJSON(Member.class, member, details ? DETAILED_MEMBER_SERIALIZER : MEMBER_SERIALIZER);
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
        renderJSON(Talk.class, favorites, TALK_SERIALIZER);
    }
}
