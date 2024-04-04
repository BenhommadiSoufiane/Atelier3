package ma.emsi.hopital.web;

import lombok.AllArgsConstructor;
import ma.emsi.hopital.entities.Patient;
import ma.emsi.hopital.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index (Model model,
                         @RequestParam(name="page",defaultValue = "0") int page,
                         @RequestParam(name="keyword",defaultValue = "") String kw,
                         @RequestParam(name="size",defaultValue = "4") int size)
    {
       Page<Patient> pagePatient=patientRepository.findByNomContains(kw,PageRequest.of(page,size));
            model.addAttribute("listepatient",pagePatient.getContent());
            model.addAttribute("pages",new int[pagePatient.getTotalPages()]);
            model.addAttribute("Current page",page);
            model.addAttribute("keyword",kw);
            return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id){
        patientRepository.deleteById(id);
        return "redirect:/index";
    }


}
