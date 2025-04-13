package Meeting_Management.System.Controller;

import Meeting_Management.System.Dto.CommiteeDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Exception.ServiceException;
import Meeting_Management.System.Repository.CommiteeRepo;
import Meeting_Management.System.Service.Impl.CommiteeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/api/commitee")
@RestController
public class CommiteeController {
    @Autowired
    private CommiteeServiceImpl commiteeServiceImpl;

    @PostMapping("/newCommitee")
    public ResponseEntity<ResponseDTO> createNewCommitee(@Valid @RequestBody CommiteeDTO commiteeDTO){
        ResponseDTO responseDTO = commiteeServiceImpl.createCommitee(commiteeDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);

    }

    @GetMapping("/listCommitees")
    public ResponseEntity<ResponseDTO> getCommiteesDetails(){
            return new ResponseEntity<>(commiteeServiceImpl.getCommitees(),HttpStatus.OK);


    }
    @GetMapping("/getCommiteeByCommiteeType/{id}")
    public ResponseEntity<ResponseDTO> getCommiteeByCommiteeType(@PathVariable long id){
        return new ResponseEntity<>(commiteeServiceImpl.getCommiteeByCommiteeType(id),HttpStatus.OK);
    }
    @GetMapping("/getCommiteeByOffice/{id}")
    public ResponseEntity<ResponseDTO> getCommiteeByOffice(@PathVariable  int id){
        return new ResponseEntity<>(commiteeServiceImpl.getCommiteeByOffice(id),HttpStatus.OK);
    }

    @PutMapping("/updateCommitee/{id}")
    public ResponseEntity<ResponseDTO> updateCommitee(@Valid @RequestBody CommiteeDTO commiteeDTO ,@PathVariable Long id){

        return new ResponseEntity<>(commiteeServiceImpl.updateCommitee(commiteeDTO,id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCommitee/{id}")
    public ResponseEntity<ResponseDTO> deleteCommitee(@PathVariable Long id){
        return new ResponseEntity<>(commiteeServiceImpl.deleteCommitee(id),HttpStatus.OK);
    }
}
