package com.project.Tailoring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.Tailoring.Entities.Member;
import com.project.Tailoring.Service.MemberService;


@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // CREATE
    @PostMapping
    public Member saveMember(
            @RequestBody Member member) {

        return memberService.saveMember(member);
    }

    // GET ALL
    @GetMapping
    public List<Member> getAllMembers() {

        return memberService.getAllMembers();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Member getMemberById(
            @PathVariable Long id) {

        return memberService.getMemberById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Member updateMember(
            @PathVariable Long id,
            @RequestBody Member member) {

        return memberService.updateMember(id, member);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteMember(
            @PathVariable Long id) {

        memberService.deleteMember(id);

        return "Member deleted successfully";
    }
}
