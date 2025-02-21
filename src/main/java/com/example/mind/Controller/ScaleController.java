package com.example.mind.Controller;

import com.example.mind.DTO.CreateScaleDTO.CreateScaleRequest;
import com.example.mind.DTO.SclaeDTO.ScaleGetResponse;
import com.example.mind.DTO.SclaeDTO.ScaleSimpleDTO;
import com.example.mind.DTO.SclaeDTO.Stats.ScaleStatsDTO;
import com.example.mind.Service.ScaleService;
import com.example.mind.annotation.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ScaleController {

    @Autowired
    private ScaleService scaleService;

    @LogAnnotation("查询所有量表")
    @ApiOperation(value = "查询所有量表")
    @RequestMapping(value = "/query/allSchemes",method = RequestMethod.GET)
    public List<ScaleSimpleDTO> getAllScales() {
        return scaleService.getAllScales();
    }

    @LogAnnotation("查询量表")
    @ApiOperation(value = "查询量表")
    @RequestMapping(value = "/query/scheme",method = RequestMethod.GET)
    public ScaleGetResponse getScaleDetail(@RequestParam("apifoxApiId") String apifoxApiId) {
        return scaleService.getScaleDetails(apifoxApiId);
    }

    @ApiOperation(value = "查询量表统计信息")
    @RequestMapping(value = "/query/scheme_result",method = RequestMethod.GET)
    public ScaleStatsDTO getScaleStats(@RequestParam("id") String id) {
        return scaleService.getScaleStatistics(id);
    }

    @LogAnnotation("创建量表")
    @ApiOperation(value = "创建量表")
    @RequestMapping(value = "/scales/create",method = RequestMethod.POST)
    public ResponseEntity<Map<String, Boolean>> createScale(@RequestBody CreateScaleRequest createScaleRequest) {
        System.out.println("createScaleRequest.getScaleName(): " + createScaleRequest.getQuestions().get(0).getId());
        scaleService.createScale(createScaleRequest);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return ResponseEntity.ok(response);
        // TODO
    }

}
