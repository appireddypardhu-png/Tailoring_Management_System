package com.project.Tailoring.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Tailoring.Entities.BottomMeasurement;
import com.project.Tailoring.Entities.Member;
import com.project.Tailoring.Repository.BottomMeasurementRepository;
import com.project.Tailoring.Repository.MemberRepository;
import com.project.Tailoring.Service.BottomMeasurementService;

@Service
public class BottomMeasurementServiceImpl implements BottomMeasurementService {

    @Autowired
    private BottomMeasurementRepository bottomMeasurementRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public BottomMeasurement saveBottomMeasurement(
            Long memberId,
            BottomMeasurement bottomMeasurement) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("Member not found"));

        bottomMeasurement.setMember(member);

        BottomMeasurement saved =
                bottomMeasurementRepository.save(bottomMeasurement);

        member.setBottomMeasurement(saved);
        memberRepository.save(member);

        return saved;
    }

    @Override
    public BottomMeasurement getBottomMeasurementById(Long id) {
        return bottomMeasurementRepository.findById(id).orElse(null);
    }
}