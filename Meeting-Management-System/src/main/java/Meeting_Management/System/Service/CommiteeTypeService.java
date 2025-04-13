package Meeting_Management.System.Service;

import Meeting_Management.System.Dto.CommiteeDTO;
import Meeting_Management.System.Dto.CommiteeTypeDTO;
import Meeting_Management.System.Dto.ResponseDTO;

public interface CommiteeTypeService {
public ResponseDTO createCommiteeType(CommiteeTypeDTO commiteeTypeDTO);
public ResponseDTO getCommiteeType(long id);
public ResponseDTO getAllCommiteeType();
public ResponseDTO updateCommiteeType(CommiteeTypeDTO commiteeTypeDTO, long id);
public ResponseDTO deleteCommiteeType(long id);
}
