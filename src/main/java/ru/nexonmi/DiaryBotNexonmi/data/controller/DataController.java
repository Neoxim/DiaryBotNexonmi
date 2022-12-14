package ru.nexonmi.DiaryBotNexonmi.data.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nexonmi.DiaryBotNexonmi.data.repository.DataRepository;
import ru.nexonmi.DiaryBotNexonmi.data.service.DataService;

@RestController
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/deletealldata/bykey/*****")
    public ResponseEntity deleteAllData(
            @RequestParam(name = "accessKey") String key
    ) {
        try {
            return ResponseEntity.ok(
                    dataService.deleteAllData(key)
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/getlistoffiles/bykey/*****")
    public ResponseEntity getListOfFiles(
            @RequestParam(name = "accessKey") String key
    ) {
        try {
            return ResponseEntity.ok(
                    dataService.getListOfFiles(key)
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/getfiledatabyfilename/bykey/*****")
    public ResponseEntity getFileDataByFileName(
            @RequestParam(name = "accessKey") String key,
            @RequestParam(name = "fileName") String fileName
    ) {
        try {
            return ResponseEntity.ok(
                    dataService.getFileDataByFileName(key, fileName)
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/uploadfile/bykey/*****")
    public ResponseEntity uploadFile(
            @RequestParam(name = "accessKey") String key,
            @RequestParam(name = "fileName") String fileName,
            @RequestParam(name = "fileData") String fileData
    ) {
        try {
            return ResponseEntity.ok(
                    dataService.uploadFile(key, fileName, fileData)
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/uploaddatatofilebyfilename/bykey/*****")
    public ResponseEntity uploadDataToFileByFileName(
            @RequestParam(name = "accessKey") String key,
            @RequestParam(name = "fileName") String fileName,
            @RequestParam(name = "fileData") String fileData
    ) {
        try {
            return ResponseEntity.ok(
                    dataService.uploadDataToFileByFileName(key, fileName, fileData)
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
