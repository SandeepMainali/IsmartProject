package Meeting_Management.System.Service;

public interface ReferenceValidationService {
    void validateOfficeExists(String officeName);
    void validateDepartmentExists(String departmentName);
    void validateCommiteeTypeExists(String typeName);
    void validateCommiteeExistsByName(String commiteeName);
    void validateCommiteeExistsById(long commiteeId);
}
