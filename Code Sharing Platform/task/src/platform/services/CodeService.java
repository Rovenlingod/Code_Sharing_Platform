package platform.services;

import platform.domain.Code;
import platform.dto.CodeDto;
import platform.dto.CodeIdDto;
import platform.dto.CodeReturnDto;

import java.util.List;

public interface CodeService {

    CodeIdDto saveCode(CodeDto codeDto);
    CodeReturnDto getCode(String id);
    List<CodeReturnDto> getLatest();
}
