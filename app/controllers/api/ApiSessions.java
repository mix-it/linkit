package controllers.api;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.gson.JsonSerializer;
import models.*;
import models.api.dto.*;
import models.planning.PlanedSlot;
import models.planning.Planning;
import models.planning.VisitedToJson;

import java.util.List;

public class ApiSessions extends JsonpController {

    private static final SessionToJsonVisitor SESSION_SIMPLE_VISITOR = new AbstractSessionToDTOVisitor() {
        @Override
        public AbstractSessionDTO visit(Talk talk) {
            SessionSimpleDTO dto = new SessionSimpleDTO();
            initSimpleSession(talk, dto);
            dto.setFormat(talk.format);
            dto.setLevel(talk.level);
            dto.setTrack(talk.track);
            return dto;
        }

        @Override
        public AbstractSessionDTO visit(LightningTalk lt) {
            LightningTalkSimpleDTO dto = new LightningTalkSimpleDTO();
            initSimpleSession(lt, dto);
            dto.setNbVotes(Vote.findNumberOfVotesBySession(lt));
            return dto;
        }

        @Override
        public AbstractSessionDTO visit(PlanedSlot slot) {
            SessionSimpleDTO dto = new SessionSimpleDTO();
            initSimpleSession(slot.talk, dto);
            initSlot(slot, dto);
            return dto;
        }
    };

    private static final SessionToJsonVisitor SESSION_DETAILED_VISITOR = new AbstractSessionToDTOVisitor() {
        @Override
        public AbstractSessionDTO visit(Talk talk) {
            SessionDetailedDTO dto = new SessionDetailedDTO();
            initDetailedSession(talk, dto);
            return dto;
        }

        @Override
        public AbstractSessionDTO visit(LightningTalk lt) {
            LightningTalkDetailedDTO dto = new LightningTalkDetailedDTO();
            initDetailedSession(lt, dto);
            dto.setNbVotes(Vote.findNumberOfVotesBySession(lt));
            return dto;
        }

        @Override
        public AbstractSessionDTO visit(PlanedSlot slot) {
            SessionDetailedDTO dto = new SessionDetailedDTO();
            initDetailedSession(slot.talk, dto);
            initSlot(slot, dto);
            return dto;
        }
    };

    private static Function<VisitedToJson, AbstractSessionDTO> toSessionDTO(boolean details) {
        final SessionToJsonVisitor visitor = details ? SESSION_DETAILED_VISITOR : SESSION_SIMPLE_VISITOR;
        return new Function<VisitedToJson, AbstractSessionDTO>() {
            @Override
            public AbstractSessionDTO apply(VisitedToJson session) {
                return session.accept(visitor);
            }
        };
    }

    private static AbstractSessionDTO toSessionDTO(VisitedToJson session, boolean details) {
        final SessionToJsonVisitor visitor = details ? SESSION_DETAILED_VISITOR : SESSION_SIMPLE_VISITOR;
        return session.accept(visitor);
    }

    public static Iterable<AbstractSessionDTO> toSessionsDTO(List<? extends VisitedToJson> sessions, boolean details) {
        return Lists.transform(sessions, toSessionDTO(details));
    }

    public static void talks(boolean details) {
        Planning planning = PlanedSlot.on(ConferenceEvent.CURRENT, true);
        renderJSON(toSessionsDTO(planning.getSlots(), details));
    }

    public static void talk(long id, boolean details) {
        Talk talk = Talk.findById(id);
        notFoundIfNull(talk);
        PlanedSlot slot = PlanedSlot.forTalkOn(talk, ConferenceEvent.CURRENT);
        if (slot == null) {
            slot = new PlanedSlot(talk);
        }
        renderJSON(toSessionDTO(slot, details));
    }

    public static void lightningTalks(boolean details) {
        List<LightningTalk> talks = LightningTalk.findAllOn(ConferenceEvent.CURRENT);
        renderJSON(toSessionsDTO(talks,details));
    }

    public static void lightningTalk(long id, boolean details) {
        LightningTalk talk = LightningTalk.findById(id);
        notFoundIfNull(talk);
        renderJSON(toSessionDTO(talk, details));
    }



}