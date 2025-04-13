package Meeting_Management.System.Service.Impl;

import Meeting_Management.System.Converter.ConverterUtil;
import Meeting_Management.System.Dto.CommiteeDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Entity.Commitee;
import Meeting_Management.System.Exception.ServiceException;
import Meeting_Management.System.Repository.CommiteeRepo;
import Meeting_Management.System.Repository.CommiteeTypeRepo;
import Meeting_Management.System.Repository.OfficeRepo;
import Meeting_Management.System.Service.CommiteeService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class CommiteeServiceImpl implements CommiteeService {

    @Autowired
    private CommiteeRepo commiteeRepo;
    @Autowired
    private CommiteeTypeRepo commiteeTypeRepo;
    @Autowired
    private OfficeRepo officeRepo;
    @Autowired
    private ConverterUtil converterUtil;
    @Autowired
    private ReferenceValidationServiceImpl referenceValidationService;
    private static final Logger logger = LoggerFactory.getLogger(CommiteeServiceImpl.class);

    @Override
    @Transactional
    public ResponseDTO createCommitee(CommiteeDTO commiteeDTO) {
//        referenceValidationService.validateCommiteeExists(commiteeDTO.getCommitteeName());
        if(!commiteeRepo.existsByCommitteeName(commiteeDTO.getCommitteeName())){
            referenceValidationService.validateCommiteeTypeExists(commiteeDTO.getCommitteeTypeName());
            referenceValidationService.validateDepartmentExists(commiteeDTO.getDepartName());
            referenceValidationService.validateOfficeExists(commiteeDTO.getOfficeName());
            Commitee commitee = converterUtil.convertToCommiteeEntity(commiteeDTO);
            Map<String, Object> detail = new HashMap<>();
            detail.put("commiteeCreated",converterUtil.convertToCommiteeDTO(commiteeRepo.save(commitee)));
            return new ResponseDTO("Success","M000","Commitee created successfully.",null,detail);
        }

        throw new ServiceException("Community Name already exists with name {}"+commiteeDTO.getCommitteeName(),HttpStatus.CONFLICT);

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDTO getCommitees() {
        Map<String,Object> details = new HashMap<>();
        List<CommiteeDTO> commiteeDTOS = new ArrayList<>();
        commiteeRepo.findAll().forEach(commitee -> commiteeDTOS.add(converterUtil.convertToCommiteeDTO(commitee)));
        details.put("commiteeList", commiteeDTOS );
        return new ResponseDTO("Success","M000","List of all commitees retrieved", details ,null);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDTO getCommiteeByCommiteeType(long id){
        if(commiteeTypeRepo.existsById(id)){
        Map<String,Object> details = new HashMap<>();
        List<CommiteeDTO> commiteeDTOs = new ArrayList<>();
        commiteeRepo.findAllByCommitee( id).forEach(commitee -> commiteeDTOs.add(converterUtil.convertToCommiteeDTO(commitee)));
        details.put("Commitee_List_By_CommiteeType",commiteeDTOs);
        return new ResponseDTO("Success","M000","List of all commitees by CommiteeType", details,null);}
        else{
            throw new ServiceException("CommiteeType not found with id: "+id,HttpStatus.NOT_FOUND);
        }

    }
    @Override
    @Transactional(readOnly = true)
    public ResponseDTO getCommiteeByOffice(int id){
        if(officeRepo.existsById(id)) {


            Map<String, Object> details = new HashMap<>();
            List<CommiteeDTO> commiteeDTOs = new ArrayList<>();
            commiteeRepo.findAllByOffice(id).forEach(commitee -> commiteeDTOs.add(converterUtil.convertToCommiteeDTO(commitee)));
            details.put("Commitee_List_By_CommiteeType", commiteeDTOs);
            return new ResponseDTO("Success", "M000", "List of all commitees by Office", details, null);
        }
        else {
            throw new ServiceException("Office not found with id: "+id,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseDTO updateCommitee(CommiteeDTO commiteeDTO, Long id){
            Commitee existing  = commiteeRepo.findById(id).orElseThrow( ()-> new EntityNotFoundException("Commitee not found with id: "+id));
            if(!commiteeDTO.getCommitteeName().equals(existing.getCommitteeName())){
                referenceValidationService.validateCommiteeNameExists(commiteeDTO.getCommitteeName());
                existing.setCommitteeName(commiteeDTO.getCommitteeName());
                existing.setCommitteeLocale(commiteeDTO.getCommitteeLocale());
                existing.setNickName(commiteeDTO.getNickName());
                existing.setNickNameLocale(commiteeDTO.getNickNameLocale());
            }
            if(!commiteeDTO.getCommitteeTypeName().equals(existing.getCommiteeType().getCommiteeTypeName())){
                referenceValidationService.validateCommiteeTypeExists(commiteeDTO.getCommitteeTypeName());
                existing.setCommiteeType(commiteeTypeRepo.findByCommiteeTypeName(commiteeDTO.getCommitteeTypeName()));
            }
            if(!commiteeDTO.getDepartName().equals(existing.getDepartName())){
                referenceValidationService.validateDepartmentExists(commiteeDTO.getDepartName());
                existing.setDepartName(commiteeDTO.getDepartName());

            }
            if(!commiteeDTO.getOfficeName().equals(existing.getOffice().getOfficeName())){
                referenceValidationService.validateOfficeExists(commiteeDTO.getOfficeName());

            }
            existing.setDescription(commiteeDTO.getDescription());
            existing.setRemarks(commiteeDTO.getRemarks());
            existing.setEstBody(commiteeDTO.getEstBody());
            existing.setEstDate(commiteeDTO.getEstDate());
//            Commitee existing = commiteeRepo.findByCommitteeName(commiteeDTO.getCommitteeName());
//           Commitee updated = converterUtil.convertToCommiteeEntity(commiteeDTO);
           Map<String,Object> detail = new HashMap<>();
           detail.put("updatedCommitee",converterUtil.convertToCommiteeDTO(commiteeRepo.save(existing)));
           return new ResponseDTO("Success","M000","Commitee updated successfully",null,detail);


    }
    @Override
    @Transactional
    public ResponseDTO deleteCommitee(long id){
        referenceValidationService.validateCommiteeExistsById(id);
        Optional<Commitee> commitee = commiteeRepo.findById(id);
        commiteeRepo.deleteById(id);
        logger.info("Deleted committee with id: {} and name: {}",id ,commitee.get().getCommitteeName());
        return new ResponseDTO("Success","M000","Commitee deleted with name: "+ commitee.get().getCommitteeName(),null,null);


    }
}
