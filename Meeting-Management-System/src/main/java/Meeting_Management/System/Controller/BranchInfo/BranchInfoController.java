package Meeting_Management.System.Controller.BranchInfo;

import Meeting_Management.System.Dto.BranchInfoDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Service.Impl.BranchInfoRouter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branch")
public class BranchInfoController {

    private final BranchInfoRouter branchInfoService;

    public BranchInfoController(BranchInfoRouter branchInfoService) {
        this.branchInfoService = branchInfoService;
    }


    @GetMapping("/{type}/view")
    public ResponseDTO getAllBranchInfos(@PathVariable("type") String type) {
        return branchInfoService.getBranchInfoService(type).getAllBranchInfos();
    }

    @PostMapping("/{type}/add")
    public ResponseDTO createBranchInfo(@PathVariable("type") String type,@RequestBody BranchInfoDTO branchInfoDTO) {
        return branchInfoService.getBranchInfoService(type).createBranchInfo(branchInfoDTO);
    }

    @PutMapping("/{type}/{id}")
    public ResponseDTO updateBranchInfo(@PathVariable("type") String type,@PathVariable Integer id, @RequestBody BranchInfoDTO branchInfoDTO) {
        return branchInfoService.getBranchInfoService(type).updateBranchInfo(id, branchInfoDTO);
    }

    @DeleteMapping("/{type}/delete/{id}")
    public ResponseDTO deleteBranchInfo(@PathVariable("type") String type,@PathVariable Integer id) {
        return branchInfoService.getBranchInfoService(type).deleteBranchInfo(id);
    }

    @GetMapping("/{type}/{id}")
    public ResponseDTO getBranchInfoById(@PathVariable("type") String type,@PathVariable Integer id) {
        return branchInfoService.getBranchInfoService(type).getBranchInfoById(id);
    }
}
