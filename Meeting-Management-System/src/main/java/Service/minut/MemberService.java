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
import exception.ResourceNotFoundException;  // Import the custom exception
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

    private MemberDTO convertToDTO(Member member) {
        MemberDTO dto = new MemberDTO();
        dto.setId(member.getId());
        dto.setFullName(member.getFullName());
        dto.setFullNameLocale(member.getFullNameLocale());

        dto.setGenderId(member.getGender().getId().longValue());
        dto.setMemTypeId(member.getMemType().getId());
        dto.setMintDepartId(member.getMintDepartId());
        dto.setMintMemDegId(member.getMintMemDegId());
        dto.setEthnicityId(member.getEthinicity() != null ? member.getEthinicity().getId().longValue() : null);

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

        Gender gender = genderRepo.findById(dto.getGenderId().longValue())
                .orElseThrow(() -> new ResourceNotFoundException("Gender not found for ID: " + dto.getGenderId()));
        member.setGender(gender);

        MemType memType = memTypeRepo.findById(dto.getMemTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("MemType not found for ID: " + dto.getMemTypeId()));
        member.setMemType(memType);

        if (dto.getEthnicityId() != null) {
            EthnicCategory ethnicity = ethnicCategoryRepo.findById(dto.getEthnicityId().longValue())
                    .orElseThrow(() -> new ResourceNotFoundException("Ethnicity not found for ID: " + dto.getEthnicityId()));
            member.setEthinicity(ethnicity);
        }

        User insertUser = userRepo.findById(dto.getInsertUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + dto.getInsertUserId()));
        member.setInsertUser(insertUser);

        if (dto.getEditUserId() != null) {
            User editUser = userRepo.findById(dto.getEditUserId()).orElse(null);
            member.setEditUser(editUser);
        }

        member.setInsertDate(dto.getInsertDate());
        member.setEditDate(dto.getEditDate());
        member.setStatus(dto.getStatus());
        member.setRemarks(dto.getRemarks());

        return member;
    }

    public MemberDTO createMember(MemberDTO dto) {
        Member member = convertToEntity(dto);
        return convertToDTO(memberRepo.save(member));
    }

    public List<MemberDTO> getAllMembers() {
        return memberRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public MemberDTO getMemberById(Long id) {
        Member member = memberRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found for ID: " + id));
        return convertToDTO(member);
    }

    public MemberDTO updateMember(Long id, MemberDTO dto) {
        Member member = memberRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found for ID: " + id));

        member.setFullName(dto.getFullName());
        member.setFullNameLocale(dto.getFullNameLocale());
        member.setStatus(dto.getStatus());
        member.setRemarks(dto.getRemarks());

        if (dto.getEditUserId() != null) {
            User editUser = userRepo.findById(dto.getEditUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + dto.getEditUserId()));
            member.setEditUser(editUser);
        }

        member.setEditDate(dto.getEditDate());
        return convertToDTO(memberRepo.save(member));
    }

    public void deleteMember(Long id) {
        if (!memberRepo.existsById(id)) {
            throw new ResourceNotFoundException("Member not found for ID: " + id);
        }
        memberRepo.deleteById(id);
    }
}
