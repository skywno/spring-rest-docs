package me.ezra.restdocs.member;

import me.ezra.restdocs.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
