package Meeting_Management.System.Dto.HierarchyBranch;

import lombok.Data;

@Data
public class BranchHierarchyDTO {
    private BranchInfoParentDTO parentBranch;
    public BranchHierarchyDTO(BranchInfoParentDTO parentBranch) {
        this.parentBranch = parentBranch;
    }
}
