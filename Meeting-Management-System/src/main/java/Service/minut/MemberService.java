package Service.minut;

import dto.minut.MemberDTO;
import Entity.*;
import Repository.*;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors; // Added import

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepo memberRepo;
    private final UserRepo userRepo;
    private final GenderRepo genderRepo;
    private final MemTypeRepo memTypeRepo;
    private final EthnicCategoryRepo ethnicCategoryRepo;

    private MemberDTO convertToDTO(Member member) {
        MemberDTO dto = new MemberDTO();
        dto.setId(member.getId());
        dto.setFullName(member.getFullName());
        dto.setFullNameLocale(member.getFullNameLocale());
        if (member.getGender() != null) {
            dto.setGenderId(member.getGender().getId());
        }

        if (member.getMemType() != null) {
            dto.setMemTypeId(member.getMemType().getId());
        }

        dto.setMintDepartId(member.getMintDepartId());
        dto.setMintMemDegId(member.getMintMemDegId());

        if (member.getEthnicity() != null) {
            dto.setEthnicityId(member.getEthnicity().getId());
        }

        dto.setIsBackwardClass(member.getIsBackwardClass());
        dto.setEmail(member.getEmail());
        dto.setMobNo(member.getMobNo());
        dto.setOfficeName(member.getOfficeName());
        dto.setDescription(member.getDescription());
        dto.setStatus(member.getStatus());
        dto.setRemarks(member.getRemarks());

        if (member.getInsertUser() != null) {
            dto.setInsertUserId(member.getInsertUser().getId());
        }

        dto.setInsertDate(member.getInsertDate());

        if (member.getEditUser() != null) {
            dto.setEditUserId(member.getEditUser().getId());
        }

        dto.setEditDate(member.getEditDate());
        return dto;
    }

    private Member convertToEntity(MemberDTO dto) {
        Member member = new Member();
        member.setId(dto.getId());
        member.setFullName(dto.getFullName());
        member.setFullNameLocale(dto.getFullNameLocale());
        member.setMintDepartId(dto.getMintDepartId());
        member.setMintMemDegId(dto.getMintMemDegId());
        member.setIsBackwardClass(dto.getIsBackwardClass());
        member.setEmail(dto.getEmail());
        member.setMobNo(dto.getMobNo());
        member.setOfficeName(dto.getOfficeName());
        member.setDescription(dto.getDescription());
        member.setStatus(dto.getStatus());
        member.setRemarks(dto.getRemarks());
        member.setInsertUserId(dto.getInsertUserId());

        // Handle relationships
        Gender gender = genderRepo.findById(dto.getGenderId())
                .orElseThrow(() -> new ResourceNotFoundException("Gender not found"));
        member.setGender(gender);

        MemType memType = memTypeRepo.findById(dto.getMemTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("MemberType not found"));
        member.setMemType(memType);

        if (dto.getEthnicityId() != null) {
            EthnicCategory ethnicity = ethnicCategoryRepo.findById(dto.getEthnicityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ethnicity not found"));
            member.setEthnicity(ethnicity);
        }

        User insertUser = userRepo.findById(dto.getInsertUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Insert user not found"));
        member.setInsertUser(insertUser);

        if (dto.getEditUserId() != null) {
            User editUser = userRepo.findById(dto.getEditUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Edit user not found"));
            member.setEditUser(editUser);
        }

        member.setInsertDate(dto.getInsertDate() != null ?
                dto.getInsertDate() : ZonedDateTime.now());
        member.setEditDate(dto.getEditDate());

        return member;
    }

    public MemberDTO createMember(MemberDTO dto) {
        Member member = convertToEntity(dto);
        if (member.getStatus() == null) member.setStatus(true);
        Member savedMember = memberRepo.save(member);
        return convertToDTO(savedMember);
    }

    public List<MemberDTO> getAllMembers() {
        return memberRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()); // Now works with imported Collectors
    }

    public MemberDTO getMemberById(Long id) {
        return memberRepo.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
    }

    public MemberDTO updateMember(Long id, MemberDTO dto) {
        Member existing = memberRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        // Update fields (simplified example)
        if (dto.getFullName() != null) existing.setFullName(dto.getFullName());
        // ... update other fields

        Member updated = memberRepo.save(existing);
        return convertToDTO(updated);
    }

    public void deleteMember(Long id) {
        memberRepo.deleteById(id);
    }
}