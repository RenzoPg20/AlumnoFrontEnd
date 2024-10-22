package pe.edu.cibertec.AlumnoFrontEnd.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.edu.cibertec.AlumnoFrontEnd.dto.AlumnoRequest;
import pe.edu.cibertec.AlumnoFrontEnd.dto.AlumnoResponse;

@FeignClient(name = "busqueda-alumno",url = "http://localhost:8080")
public interface AlumnoClient {

    @PostMapping("/busqueda/buscar")
    ResponseEntity<AlumnoResponse> busqueda(@RequestBody AlumnoRequest alumnoRequest);



}
