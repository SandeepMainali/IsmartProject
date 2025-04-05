package Meeting_Management.System.Controller.BranchInfo;

import Meeting_Management.System.Dto.BranchInfoDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Service.Impl.IBranchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branch")
public class BranchInfoController {
    @Autowired
    IBranchInfoService branchInfoService;


    @GetMapping("/view")
    public ResponseDTO getAllBranchInfos() {
        return branchInfoService.getAllBranchInfos();
    }

    @PostMapping("/add")
    public ResponseDTO createBranchInfo(@RequestBody BranchInfoDTO branchInfoDTO) {
        return branchInfoService.createBranchInfo(branchInfoDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateBranchInfo(@PathVariable Integer id, @RequestBody BranchInfoDTO branchInfoDTO) {
        return branchInfoService.updateBranchInfo(id, branchInfoDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteBranchInfo(@PathVariable Integer id) {
        return branchInfoService.deleteBranchInfo(id);
    }

    @GetMapping("/{id}")
    public ResponseDTO getBranchInfoById(@PathVariable Integer id) {
        return branchInfoService.getBranchInfoById(id);
    }
}
