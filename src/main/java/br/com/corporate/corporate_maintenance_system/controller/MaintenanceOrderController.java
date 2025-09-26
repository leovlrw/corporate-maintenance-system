package br.com.corporate.corporate_maintenance_system.controller;

import br.com.corporate.corporate_maintenance_system.model.MaintenanceOrder;
import br.com.corporate.corporate_maintenance_system.repository.MaintenanceOrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceOrderController {

    @Autowired
    private MaintenanceOrderRepository maintenanceOrderRepository;

    // 🔥 ADICIONE ESTE MÉTODO PARA A ROTA RAIZ
    @GetMapping("/")
    public String home() {
        return "redirect:/maintenance";
    }

    @ModelAttribute("urgencyOptions")
    public MaintenanceOrder.Urgency[] getUrgencyOptions() {
        return MaintenanceOrder.Urgency.values();
    }

    @ModelAttribute("maintenanceTypeOptions")
    public MaintenanceOrder.MaintenanceType[] getMaintenanceTypeOptions() {
        return MaintenanceOrder.MaintenanceType.values();
    }

    @GetMapping
    public ModelAndView list() {
        return new ModelAndView("maintenance/list", Map.of("orders",
                maintenanceOrderRepository.findAll(Sort.by("openingDate"))));
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("maintenance/form", Map.of("order", new MaintenanceOrder()));
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("order") MaintenanceOrder order, BindingResult result) {
        if (result.hasErrors()) {
            return "maintenance/form";
        }
        maintenanceOrderRepository.save(order);
        return "redirect:/maintenance";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        var order = maintenanceOrderRepository.findById(id);
        if (order.isPresent()) {
            return new ModelAndView("maintenance/delete", Map.of("order", order.get()));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute MaintenanceOrder order) {
        maintenanceOrderRepository.delete(order);
        return "redirect:/maintenance";
    }

    @PostMapping("/complete/{id}")
    public String complete(@PathVariable Long id) {
        var optionalOrder = maintenanceOrderRepository.findById(id);
        if (optionalOrder.isPresent() && optionalOrder.get().getCompletionDate() == null) {
            var order = optionalOrder.get();
            order.setCompletionDate(LocalDateTime.now());
            maintenanceOrderRepository.save(order);
            return "redirect:/maintenance";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}