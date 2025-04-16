package Meeting_Management.System.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class MenuDTO {
    private Integer id;
    private String menuAlias;
    private String menuName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer parentMenuId; // only storing the ID of parent menu
    private String menuType;
    private String version;
    private String menuNameOther;
    private String menuUrl;
    private String menuController;
    private String menuAction;
    private Integer displayIndex;
    private String faCode;
    private String remarks;
    private String insertUser;
    private ZonedDateTime insertDate;
    private String editUser;
    private ZonedDateTime editDate;
}
