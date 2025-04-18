package Meeting_Management.System.Controller.BranchInfo;

import Meeting_Management.System.ConvertUtil.HttpStatusUtility;
import Meeting_Management.System.Dto.BranchInfoDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Service.Impl.BranchInfoRouter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branch")
public class BranchInfoController {

    private final BranchInfoRouter branchInfoService;

    public BranchInfoController(BranchInfoRouter branchInfoService) {
        this.branchInfoService = branchInfoService;
    }

    @GetMapping("/{type}/view")
    public ResponseEntity<ResponseDTO> getAllBranchInfos(@PathVariable("type") String type) {
        ResponseDTO response = branchInfoService.getBranchInfoService(type).getAllBranchInfos();
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @PostMapping("/{type}/add")
    public ResponseEntity<ResponseDTO> createBranchInfo(@PathVariable("type") String type,@RequestBody BranchInfoDTO branchInfoDTO) {
        ResponseDTO response = branchInfoService.getBranchInfoService(type).createBranchInfo(branchInfoDTO);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @PutMapping("/{type}/{id}")
    public ResponseEntity<ResponseDTO> updateBranchInfo(@PathVariable("type") String type,@PathVariable Integer id, @RequestBody BranchInfoDTO branchInfoDTO) {
        ResponseDTO response = branchInfoService.getBranchInfoService(type).updateBranchInfo(id, branchInfoDTO);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @DeleteMapping("/{type}/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteBranchInfo(@PathVariable("type") String type,@PathVariable Integer id) {
        ResponseDTO response = branchInfoService.getBranchInfoService(type).deleteBranchInfo(id);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @GetMapping("/{type}/{id}")
    public ResponseEntity<ResponseDTO> getBranchInfoById(@PathVariable("type") String type,@PathVariable Integer id) {
        ResponseDTO response = branchInfoService.getBranchInfoService(type).getBranchInfoById(id);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }
}
