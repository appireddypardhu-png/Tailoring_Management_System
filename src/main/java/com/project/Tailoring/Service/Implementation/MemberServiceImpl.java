package com.project.Tailoring.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Tailoring.Entities.Customer;
import com.project.Tailoring.Entities.Member;
import com.project.Tailoring.Entities.TopMeasurement;
import com.project.Tailoring.Entities.BottomMeasurement;
import com.project.Tailoring.Repository.CustomerRepository;
import com.project.Tailoring.Repository.MemberRepository;
import com.project.Tailoring.Repository.TopMeasurementRepository;
import com.project.Tailoring.Repository.BottomMeasurementRepository;
import com.project.Tailoring.Service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TopMeasurementRepository topMeasurementRepository;

    @Autowired
    private BottomMeasurementRepository bottomMeasurementRepository;

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

        // Handle nested measurements: persist member first to obtain id, then persist owning sides
        TopMeasurement top = member.getTopMeasurement();
        BottomMeasurement bottom = member.getBottomMeasurement();

        // Detach measurements from member to avoid transient reference issues
        member.setTopMeasurement(null);
        member.setBottomMeasurement(null);

        Member savedMember = memberRepository.save(member);

        if (top != null) {
            top.setMember(savedMember);
            TopMeasurement savedTop = topMeasurementRepository.save(top);
            savedMember.setTopMeasurement(savedTop);
        }

        if (bottom != null) {
            bottom.setMember(savedMember);
            BottomMeasurement savedBottom = bottomMeasurementRepository.save(bottom);
            savedMember.setBottomMeasurement(savedBottom);
        }

        return savedMember;
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

             // Handle measurements on update: update existing or create new
             TopMeasurement incomingTop = member.getTopMeasurement();
             BottomMeasurement incomingBottom = member.getBottomMeasurement();

             if (incomingTop != null) {
                 TopMeasurement existingTop = existingMember.getTopMeasurement();
                 
                 if (existingTop != null && incomingTop.getTopid() == null) {
                     // Update existing measurement's fields (incoming measurement has no id)
                     existingTop.setBust(incomingTop.getBust());
                     existingTop.setWaist(incomingTop.getWaist());
                     existingTop.setShoulder(incomingTop.getShoulder());
                     existingTop.setSleeveLength(incomingTop.getSleeveLength());
                     existingTop.setTopLength(incomingTop.getTopLength());
                     existingTop.setNeckSize(incomingTop.getNeckSize());
                     existingTop.setArmhole(incomingTop.getArmhole());
                     TopMeasurement savedTop = topMeasurementRepository.save(existingTop);
                     existingMember.setTopMeasurement(savedTop);
                 } else {
                     // Create new measurement (incoming has id or no existing measurement)
                     incomingTop.setMember(existingMember);
                     TopMeasurement savedTop = topMeasurementRepository.save(incomingTop);
                     existingMember.setTopMeasurement(savedTop);
                 }
             }

             if (incomingBottom != null) {
                 BottomMeasurement existingBottom = existingMember.getBottomMeasurement();
                 
                 if (existingBottom != null && incomingBottom.getBottomid() == null) {
                     // Update existing measurement's fields (incoming measurement has no id)
                     existingBottom.setWaist(incomingBottom.getWaist());
                     existingBottom.setHip(incomingBottom.getHip());
                     existingBottom.setThigh(incomingBottom.getThigh());
                     existingBottom.setKneeSize(incomingBottom.getKneeSize());
                     existingBottom.setAnkleSize(incomingBottom.getAnkleSize());
                     existingBottom.setBottomLength(incomingBottom.getBottomLength());
                     BottomMeasurement savedBottom = bottomMeasurementRepository.save(existingBottom);
                     existingMember.setBottomMeasurement(savedBottom);
                 } else {
                     // Create new measurement (incoming has id or no existing measurement)
                     incomingBottom.setMember(existingMember);
                     BottomMeasurement savedBottom = bottomMeasurementRepository.save(incomingBottom);
                     existingMember.setBottomMeasurement(savedBottom);
                 }
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