package Service.minut;

import dto.minut.MeetAttendanceDTO;
import Entity.MeetAttendance;
import Entity.MeetMinut;
import Entity.Member;
import Entity.User;
import Repository.MeetAttendanceRepo;
import Repository.MeetMinutRepo;
import Repository.MemberRepo;
import Repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeetAttendanceService {

    private final MeetAttendanceRepo meetAttendanceRepo;
    private final MeetMinutRepo meetMinutRepo;
    private final MemberRepo memberRepo;
    private final UserRepo userRepo;

    public MeetAttendanceService(MeetAttendanceRepo meetAttendanceRepo, MeetMinutRepo meetMinutRepo,
                                 MemberRepo memberRepo, UserRepo userRepo) {
        this.meetAttendanceRepo = meetAttendanceRepo;
        this.meetMinutRepo = meetMinutRepo;
        this.memberRepo = memberRepo;
        this.userRepo = userRepo;
    }

    private MeetAttendanceDTO convertToDTO(MeetAttendance entity) {
        MeetAttendanceDTO dto = new MeetAttendanceDTO();
        dto.setId(entity.getId());
        dto.setMeetId(entity.getMeetMinut().getId());
        dto.setMintMemberId(entity.getMintMember().getId());
        dto.setIsAttendant(entity.getIsAttendant());
        dto.setStatus(entity.getStatus());
        dto.setRemarks(entity.getRemarks());
        dto.setInsertUserId(entity.getInsertUser().getId());
        dto.setInsertDate(entity.getInsertDate());
        dto.setEditUserId(entity.getEditUser() != null ? entity.getEditUser().getId() : null);
        dto.setEditDate(entity.getEditDate());
        return dto;
    }

    private MeetAttendance convertToEntity(MeetAttendanceDTO dto) {
        MeetMinut meetMinut = meetMinutRepo.findById(dto.getMeetId())
                .orElseThrow(() -> new RuntimeException("MeetMinut not found"));
        Member member = memberRepo.findById(dto.getMintMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        User insertUser = userRepo.findById(dto.getInsertUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MeetAttendance entity = new MeetAttendance();
        entity.setMeetMinut(meetMinut);
        entity.setMintMember(member);
        entity.setIsAttendant(dto.getIsAttendant());
        entity.setStatus(dto.getStatus());
        entity.setRemarks(dto.getRemarks());
        entity.setInsertUser(insertUser);
        entity.setInsertDate(ZonedDateTime.now());

        if (dto.getEditUserId() != null) {
            User editUser = userRepo.findById(dto.getEditUserId())
                    .orElseThrow(() -> new RuntimeException("Edit User not found"));
            entity.setEditUser(editUser);
            entity.setEditDate(ZonedDateTime.now());
        }
        return entity;
    }

    public List<MeetAttendanceDTO> getAllMeetAttendances() {
        return meetAttendanceRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<MeetAttendanceDTO> getMeetAttendanceById(Long id) {
        return meetAttendanceRepo.findById(id).map(this::convertToDTO);
    }

    @Transactional
    public MeetAttendanceDTO createMeetAttendance(MeetAttendanceDTO dto) {
        MeetAttendance entity = convertToEntity(dto);
        return convertToDTO(meetAttendanceRepo.save(entity));
    }

    @Transactional
    public MeetAttendanceDTO updateMeetAttendance(Long id, MeetAttendanceDTO dto) {
        MeetAttendance existingEntity = meetAttendanceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("MeetAttendance not found"));

        existingEntity.setIsAttendant(dto.getIsAttendant());
        existingEntity.setStatus(dto.getStatus());
        existingEntity.setRemarks(dto.getRemarks());

        if (dto.getEditUserId() != null) {
            User editUser = userRepo.findById(dto.getEditUserId())
                    .orElseThrow(() -> new RuntimeException("Edit User not found"));
            existingEntity.setEditUser(editUser);
            existingEntity.setEditDate(ZonedDateTime.now());
        }

        return convertToDTO(meetAttendanceRepo.save(existingEntity));
    }

    public void deleteMeetAttendance(Long id) {
        meetAttendanceRepo.deleteById(id);
    }
}
