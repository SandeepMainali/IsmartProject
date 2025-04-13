package Meeting_Management.System.Service.Impl;

import Meeting_Management.System.Converter.ConverterUtil;
import Meeting_Management.System.Dto.CommiteeDTO;
import Meeting_Management.System.Dto.CommiteeTypeDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Entity.CommiteeType;
import Meeting_Management.System.Repository.CommiteeTypeRepo;
import Meeting_Management.System.Repository.UserRepo;
import Meeting_Management.System.Service.CommiteeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class CommiteeTypeServiceImpl implements CommiteeTypeService {
    @Autowired
    private ReferenceValidationServiceImpl referenceValidationService;
    @Autowired
    private CommiteeTypeRepo commiteeTypeRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ConverterUtil converterUtil;
    @Override
    public ResponseDTO createCommiteeType(CommiteeTypeDTO commiteeTypeDTO) {
        referenceValidationService.validateCommiteeTypeNameExists(commiteeTypeDTO.getCommiteeTypeName());
        Map<String,Object> detail = new HashMap<>();
        CommiteeType commiteeType = converterUtil.convertToCommiteeTypeEntity(commiteeTypeDTO);
        commiteeType.setInsertUser(userRepo.findById(1));
        commiteeType.setInsertDate(ZonedDateTime.now());
        detail.put("CommiteeTypeCreated", converterUtil.convertToCommiteeTypeDTO(commiteeTypeRepo.save(commiteeType)));
        return new ResponseDTO("Success","T000","New Commitee Type created with name: "+commiteeTypeDTO.getCommiteeTypeName(),null,detail);
    }
    @Transactional(readOnly = true)
    @Override
    public ResponseDTO getCommiteeType(long id) {
        referenceValidationService.validateCommiteeTypeExistsById(id);

        Map<String,Object> detail = new HashMap<>();
        detail.put("CommiteeType", converterUtil.convertToCommiteeTypeDTO(commiteeTypeRepo.findById(id).get()));
        return new ResponseDTO("Success","T000","CommiteeType with id: "+id+" retrieved successfully.",null,detail);
    }
    @Transactional(readOnly = true)
    @Override
    public ResponseDTO getAllCommiteeType() {
        Map<String,Object> details = new HashMap<>();
        List<CommiteeTypeDTO> commiteeTypeDTOS = new ArrayList<>();
        commiteeTypeRepo.findAll().forEach(commiteeTypeDTO -> commiteeTypeDTOS.add(converterUtil.convertToCommiteeTypeDTO(commiteeTypeDTO) ));
        details.put("All CommiteeTypes",commiteeTypeDTOS);
        return new ResponseDTO("Success","T000","All the CommiteeType retrieved successfully",details,null);
    }

    @Override
    public ResponseDTO updateCommiteeType(CommiteeTypeDTO commiteeTypeDTO, long id) {
        referenceValidationService.validateCommiteeTypeExistsById(id);
        CommiteeType existing = commiteeTypeRepo.findById(id).get();
        String DTOTypeName = commiteeTypeDTO.getCommiteeTypeName();
        if(!existing.getCommiteeTypeName().equals(DTOTypeName)){
            referenceValidationService.validateCommiteeTypeNameExists(DTOTypeName);
            existing.setCommiteeTypeName(DTOTypeName);
            existing.setCommiteeTypeLocale(commiteeTypeDTO.getCommiteeTypeLocale());
        }
        existing.setDescription(commiteeTypeDTO.getDescription());
        existing.setRemarks(commiteeTypeDTO.getRemarks());
        existing.setEditUser(userRepo.findById(1));
        existing.setEditDate(ZonedDateTime.now());
        Map<String,Object> detail = new HashMap<>();
        detail.put("UpdatedCommiteeType",converterUtil.convertToCommiteeTypeDTO(commiteeTypeRepo.save(existing)));
        return new ResponseDTO("Success","T000","CommiteeType with id:"+id+ " updated successfully",null,detail);

    }

    @Override
    public ResponseDTO deleteCommiteeType(long id) {
        referenceValidationService.validateCommiteeTypeExistsById(id);
        CommiteeType commiteeType = commiteeTypeRepo.findById(id).get();
        commiteeTypeRepo.deleteById(id);
        Map<String, Object> detail = new HashMap<>();
        return new ResponseDTO("Success","T000","CommiteeType deleted with name: "+commiteeType.getCommiteeTypeName(),null,detail);
    }
}
