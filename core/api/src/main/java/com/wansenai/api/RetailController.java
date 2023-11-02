/*
 * Copyright 2023-2033 WanSen AI Team, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://opensource.wansenai.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.wansenai.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wansenai.dto.receipt.QueryShipmentsDTO;
import com.wansenai.dto.receipt.RetailShipmentsDTO;
import com.wansenai.service.receipt.RetailService;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.receipt.RetailShipmentsVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retail")
public class RetailController {

    private final RetailService retailService;

    public RetailController(RetailService retailService) {
        this.retailService = retailService;
    }

    @PostMapping("/shipments/pageList")
    public Response<Page<RetailShipmentsVO>> pageList(@RequestBody QueryShipmentsDTO queryShipmentsDTO) {
        return retailService.getRetailShipments(queryShipmentsDTO);
    }

    @PostMapping("/shipments/addOrUpdate")
    public Response<String> addOrUpdate(@RequestBody RetailShipmentsDTO retailShipmentsDTO) {
        return retailService.addorUpdateRetailShipments(retailShipmentsDTO);
    }
}