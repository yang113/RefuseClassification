<template>
  <div>
    <el-form :inline="true" :model="addGarbage" class="demo-form-inline" style="padding-top:15px">
      <el-form-item label="垃圾类别">
        <el-input size="small" v-model="addGarbage.classname" placeholder="请输入垃圾类别"></el-input>
      </el-form-item>
      <el-form-item label="垃圾小类">
        <el-input size="small" v-model="addGarbage.minClass" placeholder="请输入垃圾小类"></el-input>
      </el-form-item>
      <el-form-item label="具体垃圾">
        <el-input size="small" v-model="addGarbage.name" placeholder="请输入具体垃圾"></el-input>
      </el-form-item>
      <el-form-item label="处理方式">
        <el-input size="small" v-model="addGarbage.handle" placeholder="请输入垃圾处理方式"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addRubbish" size="small" style="background-color: #819d6a">添加</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="resetRubbish" size="small" style="background-color: #819d6a">重置</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getRubbish" size="small" style="background-color: #819d6a">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
        :data="filterData"
        border
        style="width: 90%;margin-left: 60px">
      <el-table-column
          fixed
          prop="classname"
          label="垃圾类别"
          width="250"
          height="50">
      </el-table-column>
      <el-table-column
          prop="minClass"
          label="垃圾小类"
          width="250"
          height="50">
      </el-table-column>
      <el-table-column
          prop="name"
          label="具体垃圾"
          width="250"
          height="50">
      </el-table-column>
      <el-table-column
          prop="handle"
          label="处理方式"
          width="250"
          height="50">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="150"
          height="50">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text">编辑</el-button>
          <el-button type="text" @click="DeleteGarbage(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        class="pageCut"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-sizes="[9, 10, 20, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.total">
    </el-pagination>
    <el-dialog title="修改垃圾信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="垃圾类别" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.classname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="垃圾小类" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.min_class" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="具体垃圾" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="处理方式" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.handle" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmEdit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "ManageGarbage",
  data() {
    return {
      addGarbage:{
        classname:'',
        minClass:'',
        name:'',
        handle:''
      },
      GarbageData: [],
      currentPage: 1,
      pageSize: 9,
      dialogFormVisible:false,
      form:{
        classname:'',
        min_class:'',
        name:'',
        handle:''
      },
      total:'',
      filterData:[],
      formLabelWidth:'120px'
    }
  },
  mounted(){
    this.load();
  },
  methods: {
    load(){
      request.get("classification/getclass",{
        params:{
          pageNum:this.currentPage,
          pageSize: this.pageSize,
          name: '',
          min_class:'',
          classname:'',
          handle:''
        }
      }).then(res=>{
        console.log('load',res)
        this.GarbageData = res.records;
        this.filterData = this.GarbageData;
        this.total = res.total;
      })
    },
    getRubbish(){
      request.get("classification/getclass",{
        params:{
          pageNum:this.currentPage,
          pageSize: this.pageSize,
          name: this.addGarbage.name,
          min_class: this.addGarbage.minClass,
          classname: this.addGarbage.classname,
          handle: this.addGarbage.handle
        }
      }).then(res=>{
        console.log('load',res)
        this.filterData = res.records;
        this.total = res.total;
      })
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      request.get("classification/getclass",{
        params:{
          pageNum:this.currentPage,
          pageSize: this.pageSize,
          name: this.addGarbage.name,
          min_class: this.addGarbage.minClass,
          classname: this.addGarbage.classname,
          handle: this.addGarbage.handle
        }
      }).then(res=>{
        console.log('load',res)
        this.filterData = res.records;
        this.total = res.total;
      })
    },
    handleClick(row) {
      this.dialogFormVisible = true;
      console.log('sha',row);
      this.form.classname = row.classname;
      this.form.min_class = row.minClass;
      this.form.name = row.name;
      this.form.handle = row.handle;
    },
    confirmEdit(e){
      console.log('确认',this.form)
      request.post("/classification",this.form).then(res=>{
        if(res){
          console.log('add',res)
          this.$message.success("修改成功")
          this.load()
        }else{
          this.$message.error("修改失败")
        }
      })
      this.dialogFormVisible = false;
    },
    DeleteGarbage(row){
      this.$confirm('是否确认删除？','提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type:'warning'
      }).then(()=>{
        console.log('删除',row);
        request.delete("classification/"+row.value).then(res=>{
          if(res){
            this.$message.success("删除成功")
            this.load()
          }else{
            this.$message.error("删除失败")
          }
        })
      })
    },
    addRubbish(){
      if(this.addGarbage.name === ""){
        this.$message.error("请输入具体垃圾")
        return
      }
      if(this.addGarbage.classname === ""){
        this.$message.error("请输入垃圾类别")
        return
      }
      if(this.addGarbage.minClass === ""){
        this.$message.error("请输入垃圾小类")
        return
      }
      request.post("/classification",this.addGarbage).then(res=>{
        if(res){
          console.log('add',res);
          this.$message.success("添加成功");
          this.load()
        }else{
          this.$message.error("添加失败");
        }
      })
    },
    resetRubbish(){
      this.addGarbage = {
        classname:'',
        minClass:'',
        name:'',
        handle:''
      }
      this.load();
    }
  },
}
</script>

<style scoped>
.el-pagination .el-pager li:not(.disabled):hover {
  color: #819d6a; /* 设置分页器鼠标悬浮时的颜色 */
}

.el-pagination .el-pager li.active {
  color: #819d6a; /* 设置分页器被选中页的字体颜色 */
}

.el-pagination .btn-next:hover,
.el-pagination .btn-prev:hover {
  color: #819d6a; /* 设置分页器左右按键鼠标悬浮时的颜色 */
}
.el-pagination .el-select .el-input .el-input__inner:hover{
  border-color: #819d6a;
}
.pageCut{
  margin-top: 10px;
}
::v-deep .el-table--enable-row-transition .el-table__body td.el-table__cell{
  padding:5px;
}
</style>