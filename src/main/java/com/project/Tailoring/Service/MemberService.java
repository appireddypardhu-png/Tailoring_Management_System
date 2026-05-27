package com.project.Tailoring.Service;

import java.util.List;

import com.project.Tailoring.Entities.Member;

public interface MemberService {

    Member saveMember(Member member);

    List<Member> getAllMembers();

    Member getMemberById(Long id);

    Member updateMember(Long id, Member member);

    void deleteMember(Long id);
}