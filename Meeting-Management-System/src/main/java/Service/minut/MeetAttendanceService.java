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
import exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
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
        dto.setInsertUserId(entity.getInsertUser() != null ? entity.getInsertUser().getId() : null);
        dto.setInsertDate(entity.getInsertDate());
        dto.setEditUserId(entity.getEditUser() != null ? entity.getEditUser().getId() : null);
        dto.setEditDate(entity.getEditDate());
        return dto;
    }

    private MeetAttendance convertToEntity(MeetAttendanceDTO dto) {
        try {
            // Validate relationships
            MeetMinut meetMinut = meetMinutRepo.findById(dto.getMeetId())
                    .orElseThrow(() -> new ResourceNotFoundException("Meeting not found with ID: " + dto.getMeetId()));

            Member member = memberRepo.findById(dto.getMintMemberId())
                    .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + dto.getMintMemberId()));

            User insertUser = dto.getInsertUserId() != null ?
                    userRepo.findById(dto.getInsertUserId())
                            .orElseThrow(() -> new ResourceNotFoundException("Insert user not found with ID: " + dto.getInsertUserId())) :
                    null;

            MeetAttendance entity = new MeetAttendance();
            entity.setMeetMinut(meetMinut);
            entity.setMintMember(member);
            entity.setIsAttendant(dto.getIsAttendant());
            entity.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
            entity.setRemarks(dto.getRemarks());

            if (insertUser != null) {
                entity.setInsertUser(insertUser);
                entity.setInsertDate(ZonedDateTime.now());
            }

            if (dto.getEditUserId() != null) {
                User editUser = userRepo.findById(dto.getEditUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("Edit User not found with ID: " + dto.getEditUserId()));
                entity.setEditUser(editUser);
                entity.setEditDate(ZonedDateTime.now());
            }

            return entity;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error converting DTO to entity: " + e.getMessage());
        }
    }

    public List<MeetAttendanceDTO> getAllMeetAttendances() {
        try {
            return meetAttendanceRepo.findAll().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error retrieving attendance records: " + e.getMessage());
        }
    }

    public MeetAttendanceDTO getMeetAttendanceById(Long id) {
        try {
            return meetAttendanceRepo.findById(id)
                    .map(this::convertToDTO)
                    .orElseThrow(() -> new ResourceNotFoundException("Attendance record not found with ID: " + id));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error retrieving attendance record: " + e.getMessage());
        }
    }

    @Transactional
    public MeetAttendanceDTO createMeetAttendance(MeetAttendanceDTO dto) {
        try {
            // Convert and save
            MeetAttendance entity = convertToEntity(dto);
            entity = meetAttendanceRepo.save(entity);

            return convertToDTO(entity);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error creating attendance record: " + e.getMessage());
        }
    }

    @Transactional
    public MeetAttendanceDTO updateMeetAttendance(Long id, MeetAttendanceDTO dto) {
        try {
            // Verify record exists
            MeetAttendance existingEntity = meetAttendanceRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Attendance record not found with ID: " + id));

            // Find required references
            if (dto.getMintMemberId() != null) {
                Member member = memberRepo.findById(dto.getMintMemberId())
                        .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + dto.getMintMemberId()));
                existingEntity.setMintMember(member);
            }

            if (dto.getMeetId() != null) {
                MeetMinut meetMinut = meetMinutRepo.findById(dto.getMeetId())
                        .orElseThrow(() -> new ResourceNotFoundException("Meeting not found with ID: " + dto.getMeetId()));
                existingEntity.setMeetMinut(meetMinut);
            }

            // Update fields
            if (dto.getIsAttendant() != null) {
                existingEntity.setIsAttendant(dto.getIsAttendant());
            }

            if (dto.getStatus() != null) {
                existingEntity.setStatus(dto.getStatus());
            }

            existingEntity.setRemarks(dto.getRemarks());

            if (dto.getEditUserId() != null) {
                User editUser = userRepo.findById(dto.getEditUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("Edit User not found with ID: " + dto.getEditUserId()));
                existingEntity.setEditUser(editUser);
                existingEntity.setEditDate(ZonedDateTime.now());
            }

            return convertToDTO(meetAttendanceRepo.save(existingEntity));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error updating attendance record: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteMeetAttendance(Long id) {
        try {
            // Verify record exists
            if (!meetAttendanceRepo.existsById(id)) {
                throw new ResourceNotFoundException("Attendance record not found with ID: " + id);
            }

            meetAttendanceRepo.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error deleting attendance record: " + e.getMessage());
        }
    }
}