package com.project.Tailoring.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Tailoring.Entities.Member;
import com.project.Tailoring.Entities.TopMeasurement;
import com.project.Tailoring.Repository.MemberRepository;
import com.project.Tailoring.Repository.TopMeasurementRepository;
import com.project.Tailoring.Service.TopMeasurementService;

@Service   // ⭐ THIS FIXES YOUR "bean not found" ERROR
public class TopMeasurementServiceImpl implements TopMeasurementService {

    @Autowired
    private TopMeasurementRepository topMeasurementRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public TopMeasurement saveTopMeasurement(Long memberId, TopMeasurement topMeasurement) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        topMeasurement.setMember(member);

        TopMeasurement saved = topMeasurementRepository.save(topMeasurement);

        member.setTopMeasurement(saved);
        memberRepository.save(member);

        return saved;
    }

    @Override
    public TopMeasurement getTopMeasurementById(Long id) {
        return topMeasurementRepository.findById(id).orElse(null);
    }
}