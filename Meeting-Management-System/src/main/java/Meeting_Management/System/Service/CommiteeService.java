package Meeting_Management.System.Service;

import Meeting_Management.System.Dto.CommiteeDTO;
import Meeting_Management.System.Dto.ResponseDTO;

public interface CommiteeService {
    public ResponseDTO createCommitee(CommiteeDTO commiteeDTO);
    public ResponseDTO getCommitees();
    public ResponseDTO getCommiteeByCommiteeType(long id);
    public ResponseDTO getCommiteeByOffice(int id);
    public ResponseDTO deleteCommitee(long id);
    public ResponseDTO updateCommitee(CommiteeDTO commiteeDTO, Long id);

}
