package com.project.Tailoring.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Tailoring.Entities.Customer;
import com.project.Tailoring.Entities.Member;
import com.project.Tailoring.Repository.CustomerRepository;
import com.project.Tailoring.Repository.MemberRepository;
import com.project.Tailoring.Service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Member saveMember(Member member) {

        // IMPORTANT FIX
        if (member.getCustomerid() != null) {

            Customer customer =
                    customerRepository
                    .findById(member.getCustomerid())
                    .orElseThrow(() ->
                            new RuntimeException("Customer not found"));

            member.setCustomer(customer);
        }

        return memberRepository.save(member);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member updateMember(Long id, Member member) {

        Member existingMember =
                memberRepository.findById(id)
                .orElse(null);

        if (existingMember != null) {

            existingMember.setMname(member.getMname());

            // IMPORTANT FIX
            if (member.getCustomerid() != null) {

                Customer customer =
                        customerRepository
                        .findById(member.getCustomerid())
                        .orElseThrow(() ->
                                new RuntimeException("Customer not found"));

                existingMember.setCustomer(customer);
            }

            return memberRepository.save(existingMember);
        }

        return null;
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}