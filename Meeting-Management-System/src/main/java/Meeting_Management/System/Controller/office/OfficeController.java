package Meeting_Management.System.Controller.office;

import Meeting_Management.System.ConvertUtil.HttpStatusUtility;
import Meeting_Management.System.Dto.OfficeDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Service.Impl.IOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    private IOfficeService officeService;

    @GetMapping("/view")
    public ResponseEntity<ResponseDTO> getAllOffices() {
        ResponseDTO response = officeService.getAllOffices();
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }
    @GetMapping("/active")
    public ResponseEntity<ResponseDTO> getActiveOffices() {
        ResponseDTO response = officeService.getActiveOffices();
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getOfficeById(@PathVariable Long id) {
        ResponseDTO response = officeService.getOfficeById(id);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<ResponseDTO> getOfficesByBranchId(@PathVariable Integer branchId) {
        ResponseDTO response = officeService.getOfficesByBranchId(branchId);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createOffice(@RequestBody OfficeDTO officeDTO) {
        ResponseDTO response = officeService.createOffice(officeDTO);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateOffice(@PathVariable Long id, @RequestBody OfficeDTO officeDTO) {
        ResponseDTO response = officeService.updateOffice(id, officeDTO);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteOffice(@PathVariable Long id) {
        ResponseDTO response = officeService.deleteOffice(id);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }
}
