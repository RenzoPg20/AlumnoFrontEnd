package pe.edu.cibertec.AlumnoFrontEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.AlumnoFrontEnd.client.AlumnoClient;
import pe.edu.cibertec.AlumnoFrontEnd.dto.AlumnoRequest;
import pe.edu.cibertec.AlumnoFrontEnd.dto.AlumnoResponse;
import pe.edu.cibertec.AlumnoFrontEnd.model.AlumnoModel;

@Controller
@RequestMapping("/busqueda")
public class AlumnoController {

    @Autowired
    AlumnoClient alumnoClient;


    @GetMapping("/buscarAlumno")
    public String mostrarFormularioBusqueda(Model model) {
        AlumnoModel alumnoModel=new AlumnoModel("","","","","","");
        model.addAttribute("alumnoModel",alumnoModel);
        return "busqueda"; // Muestra la página de búsqueda
    }

 /*
    @PostMapping("/buscar")
    public String buscarAlumno(@RequestParam("codigo") String codigo, Model model){

        System.out.println("Consumiendo FeignClient");


        if (codigo == null || codigo.trim().length()==0){
           AlumnoModel alumnoModel=new AlumnoModel("01","No se encontraron resultados","");
           model.addAttribute("alumnoModel",alumnoModel);
           return "busqueda";
        }


        try {

            AlumnoRequest alumnoRequest=new AlumnoRequest(codigo);
            ResponseEntity<AlumnoResponse> responseEntity=alumnoClient.busqueda(alumnoRequest);




            if (responseEntity.getStatusCode().is2xxSuccessful()){
                AlumnoResponse alumnoResponse=responseEntity.getBody();

                if (alumnoResponse.codigo().equals("00")){
                    AlumnoModel alumnoModel=new AlumnoModel("00","",alumnoResponse.codigo());
                    model.addAttribute("alumnoModel",alumnoModel);

                    return "detalle";
                }else {
                    AlumnoModel alumnoModel=new AlumnoModel("01","No se encontraron resultados", alumnoResponse.codigo());
                    model.addAttribute("alumnoModel",alumnoModel);
                    return "busqueda";
                }

            }else {
                AlumnoModel alumnoModel=new AlumnoModel("01","No se encontraron resultados","");
                model.addAttribute("alumnoModel",alumnoModel);
                return "busqueda";
            }

        }catch (Exception e){
            AlumnoModel alumnoModel=new AlumnoModel("99","No se encontraron resultados","");
            model.addAttribute("alumnoModel",alumnoModel);
            return "busqueda";




        }

*/
 @PostMapping("/buscar")
 public String buscarAlumno(@RequestParam("codigo") String codigo, Model model) {
     System.out.println("Consumiendo FeignClient con código: " + codigo);

     if (codigo == null || codigo.trim().isEmpty()) {
         AlumnoModel alumnoModel = new AlumnoModel("01", "", "", "", "", "No se encontraron resultados");
         model.addAttribute("alumnoModel", alumnoModel);
         return "busqueda";
     }

     try {
         AlumnoRequest alumnoRequest = new AlumnoRequest(codigo);
         ResponseEntity<AlumnoResponse> responseEntity = alumnoClient.busqueda(alumnoRequest);

         if (responseEntity.getStatusCode().is2xxSuccessful()) {
             AlumnoResponse alumnoResponse = responseEntity.getBody();

             if (alumnoResponse.codigo()!= null) {

                 AlumnoModel alumnoModel = new AlumnoModel(
                         alumnoResponse.codigo(),
                         alumnoResponse.Nombre(),
                         alumnoResponse.Apellido(),
                         alumnoResponse.Carrera(),
                         alumnoResponse.Ciclo(),
                         "");  // Sin mensaje de error
                 model.addAttribute("alumnoModel", alumnoModel);
                 return "detalle";  // Muestra la vista 'detalle' con los datos del alumno






             } else {
                 AlumnoModel alumnoModel = new AlumnoModel("01", "", "", "", "", "No se encontraron resultados");
                 model.addAttribute("alumnoModel", alumnoModel);
                 return "busqueda";
             }
         } else {
             AlumnoModel alumnoModel = new AlumnoModel("01", "", "", "", "", "No se encontraron resultados");
             model.addAttribute("alumnoModel", alumnoModel);
             return "busqueda";
         }
     } catch (Exception e) {
         AlumnoModel alumnoModel = new AlumnoModel("99", "", "", "", "", "Error al consumir el servicio");
         model.addAttribute("alumnoModel", alumnoModel);
         return "busqueda";
     }


}//FIN POSTMAN

}//FIN
