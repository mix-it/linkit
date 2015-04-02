package models;

import models.api.dto.AbstractSessionDTO;
import models.planning.PlanedSlot;

public interface SessionToJsonVisitor {

    AbstractSessionDTO visit(Talk talk);
    AbstractSessionDTO visit(LightningTalk lt);
    AbstractSessionDTO visit(PlanedSlot slot);
}
