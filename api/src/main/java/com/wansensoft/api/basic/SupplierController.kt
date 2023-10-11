package com.wansensoft.api.basic

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.wansensoft.dto.basic.AddSupplierDTO
import com.wansensoft.dto.basic.QuerySupplierDTO
import com.wansensoft.dto.basic.UpdateSupplierDTO
import com.wansensoft.dto.basic.UpdateSupplierStatusDTO
import com.wansensoft.service.basic.SupplierService
import com.wansensoft.utils.response.Response
import com.wansensoft.vo.basic.SupplierVO
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/basic/supplier")
class SupplierController (private val supplierService: SupplierService){

    @PostMapping("/list")
    fun supplierList(@RequestBody querySupplierDTO: QuerySupplierDTO) : Response<Page<SupplierVO>> {
        return supplierService.getSupplierList(querySupplierDTO)
    }

    @PostMapping("/add")
    fun addSupplier(@RequestBody addSupplierDTO: AddSupplierDTO) : Response<String> {
        return supplierService.addSupplier(addSupplierDTO)
    }

    @PostMapping("/update")
    fun updateSupplier(@RequestBody updateSupplierDTO: UpdateSupplierDTO) : Response<String> {
        return supplierService.updateSupplier(updateSupplierDTO)
    }

    @DeleteMapping("/deleteBatch")
    fun deleteSupplier(@RequestParam ids: List<Long>) : Response<String> {
        return supplierService.deleteSupplier(ids)
    }

    @PostMapping("/updateStatus")
    fun updateSupplierStatus(@RequestBody updateSupplierStatusDTO: UpdateSupplierStatusDTO) : Response<String> {
        return supplierService.updateSupplierStatus(updateSupplierStatusDTO)
    }
}