package me.ezra.restdocs.member.api;

import lombok.RequiredArgsConstructor;
import me.ezra.restdocs.member.MemberRepository;
import me.ezra.restdocs.member.domain.Member;
import me.ezra.restdocs.member.dto.MemberModificationRequest;
import me.ezra.restdocs.member.dto.MemberResponse;
import me.ezra.restdocs.member.dto.MemberSignUpRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberRepository memberRepository;

    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable long id) {
        Member member =
                memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));

        return new MemberResponse(member);
    }
    @PostMapping
    public void createMember(@RequestBody @Valid MemberSignUpRequest dto) {
        memberRepository.save(dto.toEntity());
    }

    @PutMapping("/{id}")
    public void modify(
            @PathVariable Long id,
            @RequestBody @Valid MemberModificationRequest dto
    ) {
        final Member member = memberRepository.findById(id).get();
        member.modify(dto.getName());
        memberRepository.save(member);
    }

    @GetMapping
    public Page<MemberResponse> getMembers (
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return memberRepository.findAll(pageable).map(MemberResponse::new);
    }
}
