package Meeting_Management.System.Service.Impl;

import Meeting_Management.System.Dto.BranchInfoDTO;
import Meeting_Management.System.Dto.ResponseDTO;


public interface IBranchInfoService {
    ResponseDTO  getAllBranchInfos();
    ResponseDTO getBranchInfoById(Integer id);
    ResponseDTO  getBranchInfoByAlias(String alias);
    ResponseDTO  createBranchInfo(BranchInfoDTO branchInfoDTO);
    ResponseDTO  updateBranchInfo(Integer id, BranchInfoDTO branchInfoDTO);
    ResponseDTO  deleteBranchInfo(Integer id);
    ResponseDTO  getBranchInfosByParentId(Integer parentId);
    ResponseDTO  getActiveBranchInfos();
    ResponseDTO  getBranchInfosByCity(String city);
    ResponseDTO  getBranchInfosByProvince(String province);
}
