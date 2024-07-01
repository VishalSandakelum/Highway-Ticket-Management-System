package lk.ijse.gdse66.microservice.vehicleservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Vishal Sandakelum,
 * @Runtime version: 11.0.11+9-b1341.60 amd64
 **/

@RestController
@RequestMapping("api/v0/vehicle")
public class VehicleAPI {
    @GetMapping
    public void printSomething(){
        System.out.println("jhg");
    }
}
