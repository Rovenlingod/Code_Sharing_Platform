package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.domain.Code;
import platform.domain.EmptyJson;
import platform.dto.CodeDto;
import platform.dto.CodeIdDto;
import platform.dto.CodeReturnDto;
import platform.services.CodeService;

import java.util.List;

@RestController
public class ApiController {

    private final CodeService codeService;

    @Autowired
    public ApiController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CodeReturnDto> getCode(@PathVariable String id) {
        return ResponseEntity.ok().body(codeService.getCode(id));
    }

    @PostMapping(value = "/api/code/new")
    public ResponseEntity<CodeIdDto> newCode(@RequestBody CodeDto codeDto) {
        return ResponseEntity.status(HttpStatus.OK).body(codeService.saveCode(codeDto));
    }

    @GetMapping(value = "api/code/latest")
    public ResponseEntity<List<CodeReturnDto>> getLatest() {
        return ResponseEntity.ok().body(codeService.getLatest());
    }
}
