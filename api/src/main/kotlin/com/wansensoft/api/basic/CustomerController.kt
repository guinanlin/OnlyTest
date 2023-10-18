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
package com.wansensoft.api.basic

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.wansensoft.dto.basic.*
import com.wansensoft.service.basic.CustomerService
import com.wansensoft.utils.response.Response
import com.wansensoft.vo.basic.CustomerVO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/basic/customer")
class CustomerController (private val customerService: CustomerService){

    @PostMapping("/list")
    fun customerList(@RequestBody queryCustomerDTO: QueryCustomerDTO?) : Response<Page<CustomerVO>> {
        return customerService.getCustomerList(queryCustomerDTO)
    }

    @PostMapping("/addOrUpdate")
    fun addOrUpdateCustomer(@RequestBody addOrUpdateCustomerDTO: AddOrUpdateCustomerDTO) : Response<String> {
        return customerService.addOrUpdateCustomer(addOrUpdateCustomerDTO)
    }

    @DeleteMapping("/deleteBatch")
    fun deleteBatchCustomer(@RequestParam ids: List<Long>?) : Response<String> {
        return customerService.deleteCustomer(ids)
    }

    @PostMapping("/updateStatus")
    fun updateCustomerStatus(@RequestParam("ids") ids: List<Long>?, @RequestParam("status") status: Int?) : Response<String> {
        return customerService.updateCustomerStatus(ids, status)
    }
}