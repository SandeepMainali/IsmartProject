package Meeting_Management.System.Service.Impl;

import Meeting_Management.System.Dto.OfficeDTO;
import Meeting_Management.System.Dto.ResponseDTO;

public interface IOfficeService {
    ResponseDTO getAllOffices();
    ResponseDTO getOfficeById(Long id);
    ResponseDTO getOfficesByBranchId(Integer branchId);
    ResponseDTO createOffice(OfficeDTO officeDTO);
    ResponseDTO updateOffice(Long id, OfficeDTO officeDTO);
    ResponseDTO deleteOffice(Long id);
    ResponseDTO getActiveOffices();
}
