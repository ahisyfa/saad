package com.github.ahisyfa.saad.saad.controller;

import com.github.ahisyfa.saad.saad.entity.Student;
import com.github.ahisyfa.saad.saad.entity.Tuition;
import com.github.ahisyfa.saad.saad.helper.PeriodHelper;
import com.github.ahisyfa.saad.saad.service.StudentService;
import com.github.ahisyfa.saad.saad.helper.TuitionExporterPdf;
import com.github.ahisyfa.saad.saad.service.TuitionService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

/**
 * TuitionController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: TuitionController.java, v 0.1 2023-07-16  10.39 Ahmad Isyfalana Amin Exp $
 */
@Controller
public class TuitionController {

    private static final Logger logger = LoggerFactory.getLogger(TuitionController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private TuitionService tuitionService;

    @GetMapping("/tuition")
    public String tuitionList(Model model){
        List<Tuition> tuitions = tuitionService.findAll();

        model.addAttribute("tuitions", tuitions);

        return "tuition/tuition_list.html";
    }

    @GetMapping("/tuition/{studentId}")
    public String payTuitionForm(@PathVariable Long studentId, Model model) throws Exception {
        Optional<Student> studentOpt = studentService.findById(studentId);

        if (!studentOpt.isPresent()) {
            throw new Exception("Invalid studentId");
        }

        Tuition tuition = new Tuition();
        tuition.setStudent(studentOpt.get());
        tuition.setPeriod(YearMonth.now());
        tuition.setPaymentDate(LocalDate.now());

        model.addAttribute("student", studentOpt.get());
        model.addAttribute("tuition", tuition);

        return "tuition/tuition_form.html";
    }

    @PostMapping("/tuition/{studentId}")
    public String submitPayTuition(
            @PathVariable Long studentId,
            @Valid @ModelAttribute("tuition") Tuition tuitionDTO,
            BindingResult bindingResult,
            Model model) throws Exception {
        Optional<Student> student = studentService.findById(studentId);

        if (!student.isPresent()) {
            throw new Exception("Invalid studentId");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("student", student.get());
            model.addAttribute("tuition", tuitionDTO);

            return "tuition/tuition_form.html";
        }

        Tuition tuition = new Tuition();
        BeanUtils.copyProperties(tuitionDTO, tuition);

        tuitionService.save(tuition);

        return "redirect:/tuition";
    }

    @GetMapping("/tuition/edit/{tuitionId}")
    public String editTuitionForm(@PathVariable Long tuitionId, Model model) throws Exception {
        Optional<Tuition> tuitionOpt = tuitionService.findById(tuitionId);

        if (!tuitionOpt.isPresent()) {
            throw new Exception("Invalid tuitionId");
        }

        Tuition tuition = tuitionOpt.get();
        Long studentId = tuition.getStudent().getId();

        Optional<Student> studentOpt = studentService.findById(studentId);

        if (!studentOpt.isPresent()) {
            throw new Exception("Invalid studentId");
        }

        model.addAttribute("student", studentOpt.get());
        model.addAttribute("tuition", tuition);

        return "tuition/tuition_form_edit";
    }

    @PostMapping("/tuition/edit/{tuitionId}")
    public String submitEditTuition(
            @PathVariable Long tuitionId,
            @Valid @ModelAttribute("tuition") Tuition tuition,
            BindingResult bindingResult,
            Model model) throws Exception {

        logger.info("Tuition", tuition);

        boolean exist = tuitionService.isExist(tuition.getId());

        if (!exist) {
            throw new Exception("Invalid tuitionId");
        }

        Optional<Student> studentOpt = studentService.findById(tuition.getStudent().getId());

        if (!studentOpt.isPresent()) {
            throw new Exception("Invalid studentId");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("student", studentOpt.get());
            model.addAttribute("tuition", tuition);

            return "tuition/tuition_form_edit";
        }

        tuitionService.save(tuition);

        return "redirect:/tuition";
    }


    @GetMapping("tuition/export/period")
    public void exportByPeriod(@RequestParam YearMonth period, HttpServletResponse response) throws IOException {
        String fileName = "bani_saad_tuition_period_" + period.toString() + ".pdf";
        String title = "Laporan SPP TK Bani Saad\nPeriode " + PeriodHelper.getIndonesianWording(period);

        List<Tuition> tuitions = tuitionService.findByPeriod(period);

        response.setContentType("application/pdf");
        response.setHeader("content-disposition", "attachment; filename=" + fileName);

        TuitionExporterPdf.export(title, tuitions, response);
    }

    @GetMapping("tuition/export/range")
    public void exportByDateRange(@RequestParam LocalDate start, @RequestParam LocalDate end,
                                  HttpServletResponse response) throws IOException {
        String fileName = "bani_saad_tuition_period_" + start.toString() + "-" + end.toString() + ".pdf";
        String title = "Laporan SPP TK Bani Saad\nTanggal " + start.toString() + " sampai " + end.toString();

        List<Tuition> tuitions = tuitionService.findBetweenDate(start, end);

        response.setContentType("application/pdf");
        response.setHeader("content-disposition", "attachment; filename=" + fileName);

        TuitionExporterPdf.export(title, tuitions, response);
    }
}