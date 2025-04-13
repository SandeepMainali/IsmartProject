package Meeting_Management.System.Controller;

import Meeting_Management.System.Dto.CommiteeTypeDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Service.Impl.CommiteeTypeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/commiteeType")
@RestController
public class CommiteeTypeController {
    @Autowired
    private CommiteeTypeServiceImpl commiteeTypeService;

    @PostMapping("/newCommiteeType")
    public ResponseEntity<ResponseDTO> createNewCommiteeType(@Valid @RequestBody CommiteeTypeDTO commiteeTypeDTO){
        ResponseDTO responseDTO = commiteeTypeService.createCommiteeType(commiteeTypeDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/listCommiteeTypes")
    public ResponseEntity<ResponseDTO> getAll(){
        ResponseDTO responseDTO = commiteeTypeService.getAllCommiteeType();
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    @GetMapping("/getCommiteeType/{id}")
    public ResponseEntity<ResponseDTO> getCommiteeType(@PathVariable long id){
        ResponseDTO responseDTO = commiteeTypeService.getCommiteeType(id);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    @PutMapping("/updateCommiteeType/{id}")
    public ResponseEntity<ResponseDTO> updateCommiteeType(@RequestBody CommiteeTypeDTO commiteeTypeDTO ,@PathVariable long id){
        ResponseDTO responseDTO = commiteeTypeService.updateCommiteeType(commiteeTypeDTO,id);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/deleteCommiteeType/{id}")
    public ResponseEntity<ResponseDTO> deleteCommiteeType(@PathVariable long id){
        ResponseDTO responseDTO = commiteeTypeService.deleteCommiteeType(id);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}

