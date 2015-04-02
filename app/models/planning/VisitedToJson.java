package models.planning;

import models.SessionToJsonVisitor;
import models.api.dto.AbstractSessionDTO;

public interface VisitedToJson {
    public AbstractSessionDTO accept(SessionToJsonVisitor visitor);
}
