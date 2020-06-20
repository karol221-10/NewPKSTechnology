package pl.kompikownia.pksmanager.reportgenerator.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.AnonymousAccess;
import pl.kompikownia.pksmanager.usermanager.business.api.command.UserAccessor;

@Controller
@AllArgsConstructor
public class CourseReportEndpoint {

    private UserAccessor userAccessor;

    @AnonymousAccess
    @GetMapping("/thymeleaf/report")
    public String showReportForm(Model model) {
        model.addAttribute("numberOfRegisteredUsers",userAccessor.getUserCount());
        model.addAttribute("numberOfRegisteredWorkers", userAccessor.getWorkerCount());
        return "coursereport";
    }
}
