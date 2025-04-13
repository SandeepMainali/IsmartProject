package Meeting_Management.System.Converter;

import Meeting_Management.System.Dto.CommiteeDTO;
import Meeting_Management.System.Dto.CommiteeTypeDTO;
import Meeting_Management.System.Entity.Commitee;
import Meeting_Management.System.Entity.CommiteeType;
import Meeting_Management.System.Repository.CommiteeRepo;
import Meeting_Management.System.Repository.CommiteeTypeRepo;
import Meeting_Management.System.Repository.OfficeRepo;
import Meeting_Management.System.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class ConverterUtil {
    @Autowired
    private CommiteeTypeRepo commiteeTypeRepo;
    @Autowired
    private OfficeRepo officeRepo;
    @Autowired
    private UserRepo userRepo;

    //Request
    public Commitee convertToCommiteeEntity(CommiteeDTO dto){
        Commitee commitee = new Commitee();
        commitee.setCommiteeType(commiteeTypeRepo.findByCommiteeTypeName(dto.getCommitteeTypeName()));
        commitee.setCommitteeName(dto.getCommitteeName());
        commitee.setCommitteeLocale(dto.getCommitteeLocale());
        commitee.setNickName(dto.getNickName());
        commitee.setNickNameLocale(dto.getNickNameLocale());
        commitee.setEstDate(dto.getEstDate());
        commitee.setDescription(dto.getDescription());
        commitee.setEstBody(dto.getEstBody());
        commitee.setDepartName(dto.getDepartName());
        commitee.setOffice(officeRepo.findByOfficeName(dto.getOfficeName()));
        commitee.setInsertUser(userRepo.findById(1));
        commitee.setInsertDate(ZonedDateTime.now());
        return commitee;
    }

    public CommiteeDTO convertToCommiteeDTO(Commitee commitee){
        CommiteeDTO dto = new CommiteeDTO();
        dto.setCommitteeTypeName(commitee.getCommiteeType().getCommiteeTypeName());
        dto.setCommitteeName(commitee.getCommitteeName());
        dto.setCommitteeLocale(commitee.getCommitteeLocale());
        dto.setNickName(commitee.getNickName());
        dto.setNickNameLocale(commitee.getNickNameLocale());
        dto.setEstDate(commitee.getEstDate());
        dto.setDescription(commitee.getDescription());
        dto.setEstBody(commitee.getEstBody());
        dto.setDepartName(commitee.getDepartName());
        dto.setOfficeName(commitee.getOffice().getOfficeName());
//        dto.setInsertUserId(commitee.getInsertUser().getId());
//        dto.setInsertDate(commitee.getInsertDate());
        return dto;
    }

    public CommiteeType convertToCommiteeTypeEntity(CommiteeTypeDTO commiteeTypeDTO ){
        CommiteeType commiteeType = new CommiteeType();
        commiteeType.setCommiteeTypeName(commiteeTypeDTO.getCommiteeTypeName());
        commiteeType.setCommiteeTypeLocale(commiteeTypeDTO.getCommiteeTypeLocale());
        commiteeType.setDescription(commiteeTypeDTO.getDescription());
        commiteeType.setRemarks(commiteeTypeDTO.getRemarks());
        return commiteeType;
    }
    public CommiteeTypeDTO convertToCommiteeTypeDTO(CommiteeType commiteeType){
        CommiteeTypeDTO commiteeTypeDTO = new CommiteeTypeDTO();
        commiteeTypeDTO.setCommiteeTypeName(commiteeType.getCommiteeTypeName());
        commiteeTypeDTO.setCommiteeTypeLocale(commiteeType.getCommiteeTypeLocale());
        commiteeTypeDTO.setDescription(commiteeType.getDescription());
        commiteeTypeDTO.setRemarks(commiteeType.getRemarks());
        return commiteeTypeDTO;
    }
}
