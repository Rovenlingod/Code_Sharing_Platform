package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.domain.Code;
import platform.dto.CodeDto;
import platform.dto.CodeIdDto;
import platform.dto.CodeReturnDto;
import platform.exceptions.NoSuchCodeException;
import platform.mappers.CodeMapper;
import platform.repository.CodeRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;
    private final CodeMapper codeMapper;

    @Autowired
    public CodeServiceImpl(CodeRepository codeRepository, CodeMapper codeMapper) {
        this.codeRepository = codeRepository;
        this.codeMapper = codeMapper;
    }

    @Override
    public CodeIdDto saveCode(CodeDto codeDto) {
        return codeMapper.toIdDto(codeRepository.save(codeMapper.toEntity(codeDto)));
    }

    @Override
    public CodeReturnDto getCode(String id) {
        Optional<Code> entity = codeRepository.findById(UUID.fromString(id));
        if (entity.isPresent()) {
            if (entity.get().getTime() > 0
                    && Duration.between(entity.get().getDate(), LocalDateTime.now()).getSeconds() > entity.get().getTime()) {
                throw new NoSuchCodeException();
            }
            if (entity.get().getSecret()) {
                if (entity.get().getViews() > 0) {
                    updateRemainingViews(entity.get());
                } else {
                    throw new NoSuchCodeException();
                }
            }
            return codeMapper.toReturnDto(entity.get());
        } else {
            throw new NoSuchCodeException();
        }
    }

    private Code updateRemainingViews(Code code) {
        code.setViews(code.getViews() - 1);
        return codeRepository.save(code);
    }

    @Override
    public List<CodeReturnDto> getLatest() {
        return codeMapper.toReturnDtos(codeRepository.findTop10ByIsSecretAndTimeLessThanOrderByDateDesc(false, 1));
    }
}
