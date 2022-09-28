package com.lutomiah.registry.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lutomiah.registry.entity.Patient;
import com.lutomiah.registry.model.ApiResponse;
import com.lutomiah.registry.repository.PatientRepository;
import com.lutomiah.registry.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin("*")
public class PatientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientService patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    //Get All Employees
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllEmployees(){
        return ResponseEntity.ok().body(patientService.getPatients());
    }

    @RequestMapping(value = "/findPatientById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findReservation(@PathVariable("id") Long id) throws JsonProcessingException {
        LOGGER.info("REQUEST PAYLOAD {}", id);
        ApiResponse apiResponse = new ApiResponse();

        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()){
            LOGGER.info("WELL..." + patient.get().getFirstName());
            apiResponse.setResponseCode("00");
            apiResponse.setResponseBody(patient);
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(apiResponse) , HttpStatus.OK);
        }
        apiResponse.setResponseCode("01");
        apiResponse.setMessage("No Patient found with id " + id);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatePatient", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateReservation(@RequestBody Map<String, String> requestMap){
        LOGGER.info("REQUEST PAYLOAD {}", requestMap);
        ApiResponse apiResponse = new ApiResponse();
        Long id = Long.valueOf(requestMap.get("Id").trim());
        String firstName = requestMap.get("firstName").trim();
        String lastName = requestMap.get("lastName").trim();
        String emailId = requestMap.get("emailId").trim();
        int age = Integer.parseInt(requestMap.get("age").trim());
        Long national_Id = Long.valueOf(requestMap.get("national_Id").trim());
        String gender = requestMap.get("gender").trim();
        String placeOfBirth = requestMap.get("place_of_birth").trim();
        String maritalStatus = requestMap.get("marital_status").trim();
        String empStatus = requestMap.get("employment_status").trim();
        String address = requestMap.get("address").trim();
        String rsnForAdmsn = requestMap.get("reason_for_admission").trim();

        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()){
            LOGGER.info("PATIENT {} is PRESENT", patient.get().getFirstName());
            patient.get().setFirstName(firstName);
            patient.get().setLastName(lastName);
            patient.get().setEmailId(emailId);
            patient.get().setAge(age);
            patient.get().setNational_Id(national_Id);
            patient.get().setGender(gender);
            patient.get().setPlace_of_birth(placeOfBirth);
            patient.get().setMarital_status(maritalStatus);
            patient.get().setEmployment_status(empStatus);
            patient.get().setAddress(address);
            patient.get().setReason_for_admission(rsnForAdmsn);
            patientRepository.save(patient.get());

            apiResponse.setResponseCode("00");
            apiResponse.setResponseBody(patient);
        }else {
            LOGGER.info("PATIENT !PRESENT");
            apiResponse.setResponseCode("01");
            apiResponse.setMessage("There is no patient with id " + id);
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //Create employee function
    @PostMapping("/createpatient")
    public Patient createEmployee(@RequestBody Patient patient){
        ApiResponse apiResponse = new ApiResponse();
        Optional<Patient> patient1 = Optional.ofNullable(patientService.getPatientByClientIdAndEmail(patient.getEmailId()));

        if (patient1.isPresent()){
            LOGGER.info("EMAIL ALREADY EXIST");
            apiResponse.setResponseCode("01");
            apiResponse.setMessage("Email "+  patient.getEmailId() + " already exist ");
        }

        apiResponse.setResponseCode("00");
        return patientService.savePatient(patient);

    }

    //Delete Patient
    @RequestMapping(path = "/deletePatient", method = RequestMethod.DELETE)
    public void deletePatientById(@PathParam("id") Long id) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setResponseCode("00");
        apiResponse.setMessage("Deleted Successfully");
        patientRepository.deleteById(id);
    }
}
