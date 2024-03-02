package com.management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	 @Autowired
	    private EmployeeRepository employeeRepository;

	    @GetMapping("/home")
	    public String homePage() {
	        return "home";
	    }

	    @GetMapping("/add")
	    public String addEmployeeForm(Model model) {
	        model.addAttribute("employee", new Employee());
	        return "addEmployee";
	    }

	    @PostMapping("/add")
	    public String addEmployee(@ModelAttribute Employee employee) {
	        employeeRepository.save(employee);
	        return "redirect:/employees/home";
	    }

	    @GetMapping("/show")
	    public String showEmployees(Model model) {
	        List<Employee> employees = employeeRepository.findAll();
	        model.addAttribute("employees", employees);
	        return "showEmployees";
	    }

	    @GetMapping("/find")
	    public String findEmployeeForm() {
	        return "findEmployee";
	    }

	    @PostMapping("/find")
	    public String findEmployeeById(@RequestParam Long id, Model model) {
	        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
	        if (optionalEmployee.isPresent()) {
	            model.addAttribute("employee", optionalEmployee.get());
	            return "employeeDetails";
	        } else {
	            return "employeeNotFound";
	        }
	    }

	    @GetMapping("/update/{id}")
	    public String updateEmployeeForm(@PathVariable Long id, Model model) {
	        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
	        if (optionalEmployee.isPresent()) {
	            model.addAttribute("employee", optionalEmployee.get());
	            return "updateEmployee";
	        } else {
	            return "employeeNotFound";
	        }
	    }

	    @PostMapping("/update")
	    public String updateEmployee(@ModelAttribute Employee employee) {
	        employeeRepository.save(employee);
	        return "redirect:/employees/home";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteEmployee(@PathVariable Long id) {
	        employeeRepository.deleteById(id);
	        return "redirect:/employees/home";
	    }

}
