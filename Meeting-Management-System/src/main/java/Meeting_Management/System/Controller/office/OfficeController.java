package Meeting_Management.System.Controller.office;

import Meeting_Management.System.Dto.OfficeDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Service.Impl.IOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    private IOfficeService officeService;

    @GetMapping("/view")
    public ResponseDTO getAllOffices() {
        return officeService.getAllOffices();
    }
    @GetMapping("/active")
    public ResponseDTO getActiveOffices() {
        return officeService.getActiveOffices();
    }

    @GetMapping("/{id}")
    public ResponseDTO getOfficeById(@PathVariable Long id) {
        return officeService.getOfficeById(id);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseDTO getOfficesByBranchId(@PathVariable Integer branchId) {
        return officeService.getOfficesByBranchId(branchId);
    }

    @PostMapping("/add")
    public ResponseDTO createOffice(@RequestBody OfficeDTO officeDTO) {
        return officeService.createOffice(officeDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateOffice(@PathVariable Long id, @RequestBody OfficeDTO officeDTO) {
        return officeService.updateOffice(id, officeDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteOffice(@PathVariable Long id) {
        return officeService.deleteOffice(id);
    }
}
