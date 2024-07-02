package lk.ijse.gdse66.microservice.ticketservice.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.microservice.ticketservice.dto.*;
import lk.ijse.gdse66.microservice.ticketservice.service.TicketService;
import lk.ijse.gdse66.microservice.ticketservice.service.execption.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author: Vishal Sandakelum,
 * @Runtime version: 11.0.11+9-b1341.60 amd64
 **/

@RestController
@RequestMapping("api/v0/ticket")
@RequiredArgsConstructor
public class TicketAPI {
    private final TicketService ticketService;
    private RestTemplate restTemplate;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<TicketDTO> getAllTicket(){
        return ticketService.getAllTicket();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    TicketDTO saveTicket(@Valid @RequestBody TicketDTO ticketDTO){
        try {
            UserDTO userDTOResponse = restTemplate.getForObject("http://user-service/api/v0/users/"+ticketDTO.getUserNic(), UserDTO.class);
            if(userDTOResponse.getUserNic()==null){
                throw new BadRequestException("This User : "+ ticketDTO.getUserNic()+ " Not Exicts!");
            }else{
                VehicleDTO vehicleDTOResponse = restTemplate.getForObject("http://user-service/api/v0/vehicle/"+ticketDTO.getVehicleNumber(), VehicleDTO.class);
            }
            return ticketService.saveTicket(ticketDTO);
        }catch (Exception e){
            throw new BadRequestException("This Vehicle : "+ ticketDTO.getVehicleNumber()+ "Not Exicts!");
        }
    }

    @PutMapping(value = "/{ticketId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateTicket(@Valid @RequestBody TicketDTO ticketDTO,@PathVariable("ticketId") String ticketId){
        try {
            UserDTO userDTOResponse = restTemplate.getForObject("http://user-service/api/v0/users/"+ticketDTO.getUserNic(), UserDTO.class);
            if(userDTOResponse.getUserNic()==null){
                throw new BadRequestException("This User : "+ ticketDTO.getUserNic()+ " Not Exicts!");
            }else{
                VehicleDTO vehicleDTOResponse = restTemplate.getForObject("http://user-service/api/v0/vehicle/"+ticketDTO.getVehicleNumber(), VehicleDTO.class);
            }
            ticketService.updateTicket(ticketId,ticketDTO);
        }catch (Exception e){
            throw new BadRequestException("This Vehicle : "+ ticketDTO.getVehicleNumber()+ "Not Exicts!");
        }
    }

    @DeleteMapping(value = "/{ticketId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteTicket(@PathVariable("ticketId") String ticketId){
        ticketService.deleteTicket(ticketId);
    }

    @GetMapping(value = "/{ticketId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    TicketDTO getTicket(@PathVariable("ticketId") String ticketId){
        return ticketService.getTicketDetails(ticketId);
    }
}
