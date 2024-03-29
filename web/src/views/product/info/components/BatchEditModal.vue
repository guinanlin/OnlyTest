<template>
  <div ref="container">
    <a-modal
        :title="title"
        :width="700"
        :confirm-loading="confirmLoading"
        v-model:open="openBatchEditModal"
        @ok="handleOk"
        @cancel="handleCancel"
        style="top:20%;height: 45%;">
      <a-spin :spinning="confirmLoading">
        <a-form>
          <a-row class="form-row" :gutter="24">
            <a-col :md="8" :sm="24">
              <a-form-item :label-col="labelCol" :wrapper-col="wrapperCol" label="颜色">
                <a-input placeholder="请输入颜色" v-model:value="color"/>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item :label-col="labelCol" :wrapper-col="wrapperCol" label="基础重量">
                <a-input-number placeholder="请输入基础重量(kg)" v-model:value="weight" />
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item :label-col="labelCol" :wrapper-col="wrapperCol" label="保质期">
                <a-input-number placeholder="请输入保质期(天)" v-model:value="expiryNum" />
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item :label-col="labelCol" :wrapper-col="wrapperCol" label="类别">
                <a-tree-select :dropdownStyle="{maxHeight:'200px',overflow:'auto'}" allow-clear
                               :tree-data="categoryTree" v-model:value="categoryId" placeholder="请选择类别">
                </a-tree-select>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item :label-col="labelCol" :wrapper-col="wrapperCol" label="序列号">
                <a-select placeholder="有无序列号" v-model:value="enableSerialNumber">
                  <a-select-option value="1">有</a-select-option>
                  <a-select-option value="0">无</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item :label-col="labelCol" :wrapper-col="wrapperCol" label="批号">
                <a-select placeholder="有无批号" v-model:value="enableBatchNumber">
                  <a-select-option value="1">有</a-select-option>
                  <a-select-option value="0">无</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item :label-col="labelCol" :wrapper-col="wrapperCol" label="备注">
                <a-textarea :rows="1" placeholder="请输入备注" v-model:value="remark" style="margin-top:8px;"/>
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
      </a-spin>
    </a-modal>
  </div>
</template>

<script lang="ts">
import { ref, reactive } from 'vue';
import {
  Button, Col,
  Form,
  FormItem,
  InputNumber,
  Modal, Row,
  Select,
  SelectOption,
  Spin,
  Textarea,
  TreeSelect
} from "ant-design-vue";
import {getCategoryList} from "/@/api/product/productCategory"
import {UpdateBatchProductInfoReq} from "/@/api/product/model/productModel"
import {updateBatchProductInfo} from "/@/api/product/product"

export default {
  name: 'BatchEditModal',
  emits: ['success'],
  components: {
    'a-modal': Modal,
    'a-button': Button,
    'a-spin': Spin,
    'a-form': Form,
    'a-form-item': FormItem,
    'a-input-number': InputNumber,
    'a-textarea': Textarea,
    'a-select': Select,
    'a-select-option': SelectOption,
    'a-tree-select': TreeSelect,
    'a-row': Row,
    'a-col': Col,
  },
  setup(_, context) {
    const title = ref('批量编辑商品信息');
    const openBatchEditModal = ref(false);

    const color = ref('');
    const weight = ref(0);
    const expiryNum = ref(0);
    const categoryId = ref(0);
    const enableSerialNumber = ref('');
    const enableBatchNumber = ref('');
    const remark = ref('');
    const categoryTree = reactive([]);
    const productIds = ref([])

    const confirmLoading = ref(false);
    const formRef = ref();

    const labelCol = ref({
      xs: { span: 24 },
      sm: { span: 5 },
    })

    const wrapperCol = ref({
      xs: { span: 24 },
      sm: { span: 16 },
    })

    const open = (ids) => {
      openBatchEditModal.value = true
      loadCategoryTree()
      productIds.value = ids
    };

    function loadCategoryTree() {
      getCategoryList().then((res) => {
        if (res.code === '00000') {
          const data = res.data
          const tree = data.filter((item) => !item.parentId).map((item) => {
            return {
              ...item,
              label: item.categoryName,
              value: item.id,
              key: item.id,
              children: data.filter((child) => child.parentId === item.id).map((child) => {
                return {
                  ...child,
                  label: child.categoryName,
                  value: child.id,
                  key: child.id,
                }
              })
            }
          })
          categoryTree.splice(0, categoryTree.length, ...tree)
        }
      })
    }

    const close = () => {
      openBatchEditModal.value = false
    };

    const handleOk = () => {
      const updateData: UpdateBatchProductInfoReq = {
        productIds: productIds.value,
        productCategoryId: categoryId.value,
        productColor: color.value,
        productWeight: weight.value,
        productExpiryNum: expiryNum.value,
        enableSerialNumber: enableSerialNumber.value,
        enableBatchNumber: enableBatchNumber.value,
        remark: remark.value,
      }
      updateBatchProductInfo(updateData).then((res) => {
        if (res.code === 'P0014') {
          close()
          context.emit('success')
        }
      })
    };

    const handleCancel = () => {
      close();
    };

    return {
      title,
      color,
      weight,
      expiryNum,
      categoryId,
      categoryTree,
      enableSerialNumber,
      enableBatchNumber,
      remark,
      confirmLoading,
      formRef,
      labelCol,
      wrapperCol,
      open,
      close,
      handleOk,
      handleCancel,
      openBatchEditModal,
    };
  }
};
</script>