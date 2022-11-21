package me.ezra.restdocs.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ezra.restdocs.member.dto.MemberResponse;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminApi {

    private final AdminRepository adminRepository;

    @GetMapping
    public Page<AdminResponse> getAdminsInfo (
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return adminRepository.findAll(pageable).map(AdminResponse::new);
    }

    @GetMapping("/{id}")
    public AdminResponse getAdminInfo(@PathVariable long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not found")
        );
        return new AdminResponse(admin);
    }

    @PostMapping
    public void createAdmin(@RequestBody @Valid AdminSignUpRequest dto) {
        adminRepository.save(dto.toEntity());
    }

    @PutMapping("/{id}")
    public void modifyAdmin(
            @PathVariable Long id,
            @RequestBody @Valid AdminPasswordModificationRequest dto
    ) {
        final Admin admin = adminRepository.findById(id).get();
        admin.updatePassword(dto.getPassword());
        adminRepository.save(admin);
    }

}
