package models;

import models.api.dto.AbstractMemberDTO;

public interface MemberToJsonVisitor {
    AbstractMemberDTO visit(Member member);
    AbstractMemberDTO visit(Staff staff);
    AbstractMemberDTO visit(Sponsor sponsor);
}
