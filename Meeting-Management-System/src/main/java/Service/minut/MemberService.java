package Service.minut;

import dto.minut.MemberDTO;
import Entity.Member;
import Entity.User;
import Entity.Gender;
import Entity.MemType;
import Entity.EthnicCategory;
import Repository.MemberRepo;
import Repository.UserRepo;
import Repository.GenderRepo;
import Repository.MemTypeRepo;
import Repository.EthnicCategoryRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepo memberRepo;
    private final UserRepo userRepo;
    private final GenderRepo genderRepo;
    private final MemTypeRepo memTypeRepo;
    private final EthnicCategoryRepo ethnicCategoryRepo;

    // Convert Entity to DTO
    private MemberDTO convertToDTO(Member member) {
        MemberDTO dto = new MemberDTO();
        dto.setId(member.getId());
        dto.setFullName(member.getFullName());
        dto.setFullNameLocale(member.getFullNameLocale());
        dto.setGenderId(member.getGender().getId());
        dto.setMemTypeId(member.getMemType().getId());
        dto.setMintDepartId(member.getMintDepartId());
        dto.setMintMemDegId(member.getMintMemDegId());
        dto.setEthnicityId(member.getEthinicity() != null ? member.getEthinicity().getId() : null);
        dto.setIsBackwardClass(member.getIsBackwardClass());
        dto.setEmail(member.getEmail());
        dto.setMobNo(member.getMobNo());
        dto.setOfficeName(member.getOfficeName());
        dto.setDescription(member.getDescription());
        dto.setStatus(member.getStatus());
        dto.setRemarks(member.getRemarks());
        dto.setInsertUserId(member.getInsertUser().getId());
        dto.setInsertDate(member.getInsertDate());
        dto.setEditUserId(member.getEditUser() != null ? member.getEditUser().getId() : null);
        dto.setEditDate(member.getEditDate());
        return dto;
    }

    // Convert DTO to Entity
    private Member convertToEntity(MemberDTO dto) {
        Member member = new Member();
        member.setId(dto.getId());
        member.setFullName(dto.getFullName());
        member.setFullNameLocale(dto.getFullNameLocale());

        // Add these missing properties
        member.setMintDepartId(dto.getMintDepartId());
        member.setMintMemDegId(dto.getMintMemDegId());
        member.setIsBackwardClass(dto.getIsBackwardClass());
        member.setEmail(dto.getEmail());
        member.setMobNo(dto.getMobNo());
        member.setOfficeName(dto.getOfficeName());
        member.setDescription(dto.getDescription());

        // Get references to related entities
        Gender gender = genderRepo.findById(dto.getGenderId())
                .orElseThrow(() -> new EntityNotFoundException("Gender not found"));
        member.setGender(gender);

        MemType memType = memTypeRepo.findById(dto.getMemTypeId())
                .orElseThrow(() -> new EntityNotFoundException("MemType not found"));
        member.setMemType(memType);

        if (dto.getEthnicityId() != null) {
            EthnicCategory ethnicity = ethnicCategoryRepo.findById(dto.getEthnicityId())
                    .orElseThrow(() -> new EntityNotFoundException("Ethnicity not found"));
            member.setEthinicity(ethnicity);
        }

        User insertUser = userRepo.findById(dto.getInsertUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        member.setInsertUser(insertUser);
        if (dto.getEditUserId() != null) {
            User editUser = userRepo.findById(dto.getEditUserId())
                    .orElse(null);
            member.setEditUser(editUser);
        }
        member.setInsertDate(dto.getInsertDate());
        member.setEditDate(dto.getEditDate());
        member.setStatus(dto.getStatus());
        member.setRemarks(dto.getRemarks());
        return member;
    }

    // Create Member
    public MemberDTO createMember(MemberDTO dto) {
        Member member = convertToEntity(dto);
        return convertToDTO(memberRepo.save(member));
    }

    // Get All Members
    public List<MemberDTO> getAllMembers() {
        return memberRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get Member by ID
    public MemberDTO getMemberById(Long id) {
        Member member = memberRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return convertToDTO(member);
    }

    // Update Member
    public MemberDTO updateMember(Long id, MemberDTO dto) {
        Member member = memberRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Member not found"));

        member.setFullName(dto.getFullName());
        member.setFullNameLocale(dto.getFullNameLocale());
        member.setStatus(dto.getStatus());
        member.setRemarks(dto.getRemarks());

        if (dto.getEditUserId() != null) {
            User editUser = userRepo.findById(dto.getEditUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
            member.setEditUser(editUser);
        }
        member.setEditDate(dto.getEditDate());

        return convertToDTO(memberRepo.save(member));
    }

    // Delete Member
    public void deleteMember(Long id) {
        if (!memberRepo.existsById(id)) {
            throw new EntityNotFoundException("Member not found");
        }
        memberRepo.deleteById(id);
    }
}