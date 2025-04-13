package Meeting_Management.System.Service.Impl;

import Meeting_Management.System.Exception.ServiceException;
import Meeting_Management.System.Repository.CommiteeRepo;
import Meeting_Management.System.Repository.CommiteeTypeRepo;
import Meeting_Management.System.Repository.DepartRepo;
import Meeting_Management.System.Repository.OfficeRepo;
import Meeting_Management.System.Service.ReferenceValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReferenceValidationServiceImpl implements ReferenceValidationService {
    @Autowired
    private DepartRepo departRepo;
    @Autowired
    private CommiteeTypeRepo commiteeTypeRepo;
    @Autowired
    private OfficeRepo officeRepo;
    @Autowired
    private CommiteeRepo commiteeRepo;

    @Override
    public void validateOfficeExists(String officeName) {
        if(!officeRepo.existsByOfficeName(officeName)){
            throw new ServiceException("Office not found with name: "+ officeName, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void validateDepartmentExists(String departmentName) {
        if(!departRepo.existsByDepartName(departmentName)){
            throw new ServiceException("Department not found with name: "+ departmentName,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void validateCommiteeTypeExists(String typeName) {
        if(!commiteeTypeRepo.existsByCommiteeTypeName(typeName)){
            throw new ServiceException("Commitee Type not found with name: "+ typeName, HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public void validateCommiteeExistsByName(String commiteeName){
        if(!commiteeRepo.existsByCommitteeName(commiteeName)){
            throw new ServiceException("Commitee not found with name: "+ commiteeName,HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public void validateCommiteeExistsById(long id){
        if(!commiteeRepo.existsById(id)){
            throw new ServiceException("Commitee does not exist",HttpStatus.NOT_FOUND);
        }
    }

    public void validateCommiteeNameExists(String commiteeName){
        if(commiteeRepo.existsByCommitteeName(commiteeName)){
            throw new ServiceException("Commitee Name already exists",HttpStatus.CONFLICT);
        }
    }
    public void validateCommiteeTypeExistsById(long id){
        if(!commiteeTypeRepo.existsById(id)){
            throw new ServiceException("CommiteeType of id: "+ id+" does not exist",HttpStatus.NOT_FOUND);
        }
    }
    public void validateCommiteeTypeNameExists(String commiteeTypeName){
        if(commiteeTypeRepo.existsByCommiteeTypeName(commiteeTypeName)){
            throw new ServiceException("CommiteeType of Name: '"+commiteeTypeName+"' already exists.Please choose another name.", HttpStatus.CONFLICT);
        }
    }
}
