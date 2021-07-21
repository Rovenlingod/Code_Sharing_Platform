package platform.mappers;

import org.springframework.stereotype.Component;
import platform.domain.Code;
import platform.dto.CodeDto;
import platform.dto.CodeIdDto;
import platform.dto.CodeReturnDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodeMapper {

    public Code toEntity(CodeDto codeDto) {
        Code result = new Code();
        result.setCode(codeDto.getCode());
        result.setTime(codeDto.getTime() == null ? 0 : codeDto.getTime());
        result.setViews(codeDto.getViews() == null ? 0 : codeDto.getViews());
        result.setSecret(result.getViews() > 0);
        return result;
    }

    public CodeDto toDto(Code code) {
        CodeDto result = new CodeDto();
        result.setCode(code.getCode());
        return result;
    }

    public CodeIdDto toIdDto(Code code) {
        CodeIdDto codeIdDto = new CodeIdDto();
        codeIdDto.setId(code.getId().toString());
        return codeIdDto;
    }

    public CodeReturnDto toReturnDto(Code code) {
        CodeReturnDto result = new CodeReturnDto();
        LocalDateTime dateTime = code.getDate();
        String resultDate = dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        result.setDate(resultDate);
        result.setCode(code.getCode());
        if (code.getTime() > 0) {
            result.setTime(code.getTime() - Duration.between(code.getDate(), LocalDateTime.now()).getSeconds());
        } else {
            result.setTime(code.getTime());
        }
        result.setViews(code.getViews());
        result.setSecret(code.getSecret());
        return result;
    }

    public List<CodeReturnDto> toReturnDtos(List<Code> codeList) {
        return codeList.stream()
                .map(this::toReturnDto)
                .collect(Collectors.toList());
    }
}
