package com.springboot.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.student.Repository.StudentRepository;

@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
   
    
    
      @GetMapping("/ViewALl")
    public ModelAndView getALl(Model model)
    { ModelAndView modelAndView=new ModelAndView();
      modelAndView.setViewName("displayall.html");

       
       List<Student> students= (List)studentRepository.findAll();

       model.addAttribute("students", students);
      return modelAndView;}
     
    @GetMapping
    public ModelAndView homeController(Model model)
    { ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("Student", new Student());
        
        return modelAndView;
    }
    @PostMapping("/Insert_Student")
    public ModelAndView formProcessor(@ModelAttribute Student student,Model model)
    {ModelAndView modelAndView=new ModelAndView();
        
        modelAndView.setViewName("displayStudents.html");

       studentRepository.save(student);
       List<Student> students= (List)studentRepository.findAll();
       model.addAttribute("students", students);
      return modelAndView;
    }
    @PostMapping("/insertstudent")
    public Student formprocess(@RequestBody Student student)
    {
      return studentRepository.save(student);
    }
  
    @GetMapping("/DeleteById/{id}")
    public ModelAndView delbystdid(@PathVariable("id")int studentId,Model model,@ModelAttribute Student student)
    {

      ModelAndView modelAndView=new ModelAndView();
      student=studentRepository.findById(studentId).get();
     List<Student> students= (List)studentRepository.findAll();
     model.addAttribute("id", student.getId());
     model.addAttribute("student", student);
       model.addAttribute("students", students);
      


       studentRepository.deleteById(studentId);
       modelAndView.setViewName("display1Student.html");

        return modelAndView;
    }
 
     

    @GetMapping("/FetchById/{id}")
    public ModelAndView getstdbyid(@PathVariable("id") int id,Model model)
    {
      ModelAndView modelAndView =new ModelAndView();
      Student student=studentRepository.findById(id).get();
      model.addAttribute("id", student.getId());
      model.addAttribute("student", student);
      modelAndView.setViewName("display1Student.html");
      return modelAndView; 
    }
   
        @GetMapping("/FetchById")
    public ModelAndView getbyid(@ModelAttribute("Student") Student student,Model model)
    {
      ModelAndView modelAndView =new ModelAndView();
      
      student=studentRepository.findById(student.getId()).get();
      model.addAttribute("id", student.getId());
      model.addAttribute("student", student);
      modelAndView.setViewName("display1Student.html");
      return modelAndView; 
    }


         @GetMapping("/UpdateById")
    public ModelAndView updatebyid1(@ModelAttribute("student") Student student,Model model)
    { ModelAndView modelAndView =new ModelAndView();
     
      
      student=studentRepository.findById(student.getId()).get();
      model.addAttribute("id", student.getId());
      model.addAttribute("student", student);
      modelAndView.setViewName("update.html");
    
      
    
      return modelAndView; 
    }
         @GetMapping("/UpdateById/{id}")
    public ModelAndView updatebyid(@PathVariable("id") int id,@ModelAttribute("student")Student student,Model model)
    { ModelAndView modelAndView =new ModelAndView();
      modelAndView.setViewName("update.html");
      student=studentRepository.findById(id).get();
      model.addAttribute("id", id);

     
      // model.addAttribute("student", student);
      
    
      return modelAndView; 
    }
     @PostMapping("/UpdateById/{id}")
    public ModelAndView updatebyids(@PathVariable("id")int id,@ModelAttribute("student")Student student,Model model)
    {
      ModelAndView modelAndView =new ModelAndView();
   
     
      model.addAttribute("id", id);
      student.setId(id);
      student.setAge(student.getAge());
      student.setName(student.getName());
      student.setAddress(student.getAddress());
      model.addAttribute("student", student);
      studentRepository.save(student);
      modelAndView.setViewName("display1Student.html");
        return modelAndView;
}
   @GetMapping("/DeleteById")
   public ModelAndView deleteview(@ModelAttribute("student") Student student,Model model)
   {
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.setViewName("displayall.html");
    student=studentRepository.findById(student.getId()).get();
    model.addAttribute("id", student.getId());
    model.addAttribute("students", studentRepository.findAll());
    studentRepository.deleteById(student.getId());
    return modelAndView;
   }

   @GetMapping("/view")
     public List<Student> viewAll()
     {
      return studentRepository.findAll();
     }
@PutMapping("updtion/{id}")
    public Student updatestudent(@RequestBody Student student,@PathVariable("id") int id)
    {
      if(studentRepository.findById(id)!=null)
      return studentRepository.save(student);
      else
      return null;
    }
    @DeleteMapping("deleted/{id}")
    public void deletestdbyID(@RequestBody Student student,@PathVariable("id") int id)
    {
      
      studentRepository.deleteById(id);
    }



}
