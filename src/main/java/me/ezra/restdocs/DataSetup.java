package me.ezra.restdocs;

import lombok.AllArgsConstructor;
import me.ezra.restdocs.admin.Admin;
import me.ezra.restdocs.admin.AdminRepository;
import me.ezra.restdocs.member.domain.Member;
import me.ezra.restdocs.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataSetup implements ApplicationRunner {

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final List<Member> members = new ArrayList<>();

        members.add(new Member("Lim1@bbb.com", "Lim1"));
        members.add(new Member("Lim2@bbb.com", "Lim2"));
        members.add(new Member("Lim3@bbb.com", "Lim3"));
        members.add(new Member("Lim4@bbb.com", "Lim4"));
        memberRepository.saveAll(members);

        Admin admin = new Admin("admin@gmail.com", "ezra lim", "password");
        adminRepository.save(admin);
    }
}
