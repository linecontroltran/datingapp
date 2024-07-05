package datingapp.datingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import datingapp.datingapp.dto.ResponseData;
import datingapp.datingapp.dto.RequestData;
import datingapp.datingapp.service.DataService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/data", produces = "application/json")
    public ResponseEntity<ResponseData> getData() {
        String responseData = dataService.fetchData();
        ResponseData data = new ResponseData(responseData);
        System.out.println("Fetched Data: " + responseData); // Log fetched data
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/data", produces = "application/json")
    public ResponseEntity<ResponseData> postData(@RequestBody RequestData requestData) {
        String responseMessage = dataService.saveData(requestData.getMessage());
        ResponseData responseData = new ResponseData(responseMessage);
        System.out.println("Saved Data: " + responseMessage); // Log saved data
        return ResponseEntity.ok(responseData);
    }
}
