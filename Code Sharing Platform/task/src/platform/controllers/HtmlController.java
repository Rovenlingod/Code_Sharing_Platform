package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.domain.Code;
import platform.dto.CodeReturnDto;
import platform.repository.CodeRepository;
import platform.services.CodeService;

import java.time.format.DateTimeFormatter;

@Controller
public class HtmlController {

    private CodeService codeService;

    @Autowired
    public HtmlController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(value = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getCode(@PathVariable String id, Model model) {
        CodeReturnDto result = codeService.getCode(id);
        model.addAttribute("code", result.getCode());
        model.addAttribute("date", result.getDate());
        if (result.getTime() > 0) {
            model.addAttribute("time", "The code will be available for " + result.getTime() + " seconds");
        } else {
            model.addAttribute("time", "");
        }
        if (result.getSecret()) {
            model.addAttribute("views", result.getViews() + " more views allowed");
        } else {
            model.addAttribute("views", "");
        }
        return "code";
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewCode(Model model) {
        return "newCode";
    }

    @GetMapping(value = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getLatest(Model model) {
        model.addAttribute("codeList", codeService.getLatest());
        return "latest";
    }
}
