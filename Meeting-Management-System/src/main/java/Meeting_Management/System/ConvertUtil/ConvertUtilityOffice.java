package Meeting_Management.System.ConvertUtil;

import Meeting_Management.System.Dto.OfficeDTO;
import Meeting_Management.System.Entity.BranchInfo;
import Meeting_Management.System.Entity.Office;
import Meeting_Management.System.Entity.User;
import lombok.experimental.UtilityClass;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ConvertUtilityOffice {
    public static List<OfficeDTO> convertListToOfficeDTOs(List<Office> offices) {
        return offices.stream()
                .map(ConvertUtilityOffice::convertToOfficeDTO)
                .collect(Collectors.toList());
    }

    public static OfficeDTO convertToOfficeDTO(Office office) {
        if (office == null) {
            return null;
        }

        OfficeDTO officeDTO = new OfficeDTO();
        officeDTO.setId(office.getId());
        officeDTO.setBranchInfoId(office.getBranchInfo() != null ? office.getBranchInfo().getId() : null);
        officeDTO.setOfficeName(office.getOfficeName());
        officeDTO.setOfficeLocale(office.getOfficeLocale());
        officeDTO.setDescription(office.getDescription());
        officeDTO.setStatus(office.getStatus());
        officeDTO.setRemarks(office.getRemarks());
        officeDTO.setInsertUserId(office.getInsertUser() != null ? office.getInsertUser().getId() : null);
        officeDTO.setInsertDate(office.getInsertDate());
        officeDTO.setEditUserId(office.getEditUser() != null ? office.getEditUser().getId() : null);
        officeDTO.setEditDate(office.getEditDate());

        return officeDTO;
    }

    public static Office convertToEntity(BranchInfo branchInfo, User user, OfficeDTO officeDTO) {
        Office office = new Office();
        office.setBranchInfo(branchInfo);
        office.setOfficeName(officeDTO.getOfficeName());
        office.setOfficeLocale(officeDTO.getOfficeLocale());
        office.setDescription(officeDTO.getDescription());
        office.setStatus(true);
        office.setRemarks(officeDTO.getRemarks());
        office.setInsertUser(user);
        office.setInsertDate(ZonedDateTime.now());
        return office;
    }
}
