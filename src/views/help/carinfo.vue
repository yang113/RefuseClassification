<template>
  <div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd" style="float: right">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="carnum" label="车牌号"></el-table-column>
      <el-table-column prop="polivynum" label="保单编号"></el-table-column>
      <el-table-column prop="settlestatus" label="落户状态"></el-table-column>
      <el-table-column prop="checkstatus" label="验车状态"></el-table-column>
      <el-table-column prop="checktime" label="验车日期"></el-table-column>
      <el-table-column prop="settletime" label="落户日期"></el-table-column>
      <el-table-column prop="vipnum" label="会员编号"></el-table-column>
      <el-table-column prop="policystatus" label="保险状态"></el-table-column>
      <el-table-column prop="cartype" label="车型"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.carnum)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="90px" size="small">
        <el-form-item label="车牌号">
          <el-input v-model="form.carnum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="保单编号">
          <el-input v-model="form.polivynum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="落户状态">
          <el-input v-model="form.settlestatus" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="验车状态">
          <el-input v-model="form.checkstatus" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="验车日期">
          <el-input v-model="form.checktime" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="落户日期">
          <el-input v-model="form.settletime" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="会员编号">
          <el-input v-model="form.vipnum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="保险状态">
          <el-input v-model="form.policystatus" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="车型">
          <el-input v-model="form.cartype" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";


export default {
  name: "carinfo",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      carnum:"",
      polivynum:"",
      settlestatus:"",
      checkstatus:"",
      checktime:"",
      settletime:"",
      vipnum:"",
      policystatus:"",
      cartype:"",
      form:{},
      dialogFormVisible: false,
      multipleSelection: [],
      headerBg: 'headerBg',
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/carinfo/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },
    del(id){
      request.delete("/carinfo/"+id).then(res=>{
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },
    handleSelectionChange(){
      this.multipleSelection = val
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load()
    },

    save(){
      request.post("/carinfo",this.form).then(res => {
        if (res) {
          console.log(res)
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
  }
}
</script>

<style scoped>

</style>