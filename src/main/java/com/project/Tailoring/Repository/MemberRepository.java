package com.project.Tailoring.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Tailoring.Entities.Member;

public interface MemberRepository 
extends JpaRepository<Member, Long> {

}
