<template>
  <div class="container">
    <div style="background-color: #e2ecda;margin-top: -15px">
      <el-form :inline="true" :model="addLink" class="demo-form-inline" style="padding-top:15px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="日期">
              <el-date-picker
                  v-model="addLink.date"
                  type="date"
                  placeholder="请选择公告发布日期"
                  size="small"
                  style="width: 190px">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="省份">
              <el-input size="small" v-model="addLink.province" placeholder="请输入公告发布省份"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="城市">
              <el-input size="small" v-model="addLink.city" placeholder="请输入公告发布城市"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="标题">
              <el-input size="small" v-model="addLink.title" placeholder="请输入公告标题"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="链接">
              <el-input size="small" v-model="addLink.link" placeholder="请输入公告链接"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item>
              <el-button type="primary" @click="addReport" size="small" style="background-color: #819d6a">添加</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="resetReport" size="small" style="background-color: #819d6a">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <el-table
        :data="filteredTableData"
        style="width: 100%">
      <el-table-column
          label="日期"
          prop="date"
          sortable
          :sort-orders="['descending', 'ascending']">
      </el-table-column>
      <el-table-column
          label="省份"
          prop="province">
      </el-table-column>
      <el-table-column
          label="城市"
          prop="city">
      </el-table-column>
      <el-table-column
          label="标题"
          :title="tableData.title"
          prop="title">
      </el-table-column>
      <el-table-column
          fixed="right"
          width="250">
        <template slot="header" slot-scope="scope">
          <el-input
              v-model="search"
              size="small"
              placeholder="按城市搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button
              size="mini" type="primary"
              style="color: #fff"
              @click="handleLook(scope.$index, scope.row)">查看</el-button>
          <el-button
              size="mini" type="success"
              style="color: #fff"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
              size="mini" type="danger"
              style="color: #fff"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        class="pageCut"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-sizes="[8, 10, 20, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length">
    </el-pagination>
    <el-dialog title="修改公告信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="日期" :label-width="formLabelWidth">
          <el-date-picker
              v-model="form.date"
              type="date"
              placeholder="请选择公告发布日期"
              size="small"
              style="width: 640px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="省份" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.province" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="城市" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.city" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="标题" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="链接" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.link" autocomplete="off"></el-input>
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
  name: "manageNews",
  data() {
    return {
      tableData: [],
      search: '',
      currentPage: 1,
      pageSize: 8,
      addLink:{
        date:'',
        province:'',
        city:'',
        title:'',
        link:'',
        value:''
      },
      form:{
        date:'',
        province:'',
        city:'',
        title:'',
        link:'',
        value:''
      },
      dialogFormVisible:false,
      formLabelWidth: '120px',
    };
  },
  mounted(){
    this.load();
  },
  methods: {
    load(){
      request.get("information/getinfo",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          city:this.search
        }
      }).then(res=>{
        console.log('load',res)
        res.records.forEach(item=>{
          item.date = item.date.toString().split('T')[0];
        })
        this.tableData = res.records;
      })
    },
    handleLook(index, row) {
      console.log(index, row);
      let src = row.link;
      window.open(src, '_blank');
    },
    handleEdit(index, row) {
      console.log(index, row);
      this.dialogFormVisible = true;
      this.form = row;
    },
    handleDelete(index, row) {
      console.log(index, row);
      this.$confirm('是否确认删除？','提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type:'warning'
      }).then(()=>{
        console.log('删除',row);
        request.delete("/information/"+row.value).then(res=>{
          if(res){
            this.$message.success("删除成功")
            this.load()
          }else{
            this.$message.error("删除失败")
          }
        })
      })
    },
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    addReport(){
      console.log('添加',this.addLink)
      if(this.addLink.date === ""){
        this.$message.error("请输入公告发布日期")
        return
      }
      if(this.addLink.title === ""){
        this.$message.error("请输入公告标题")
        return
      }
      if(this.addLink.link === ""){
        this.$message.error("请输入公告链接")
        return
      }
      request.post("/information",this.addLink).then(res=>{
        if(res){
          console.log('add',res);
          this.$message.success("添加成功");
          this.load()
        }else{
          this.$message.error("添加失败");
        }
      })
      // this.tableData.push(this.addLink);
    },
    resetReport(){
      this.addLink = {
        date:'',
        province:'',
        city:'',
        title:'',
        link:'',
      }
    },
    confirmEdit(e){
      console.log('确认',e);
      request.post("/information",this.form).then(res=>{
        if(res){
          console.log('add',res);
          this.$message.success("修改成功");
          this.load()
        }else{
          this.$message.error("修改失败");
        }
      })
      this.dialogFormVisible = false;
    }
  },
  computed: {
    filteredTableData() {
      const filteredData = this.tableData.filter(data => {
        return !this.search || data.city.toLowerCase().includes(this.search.toLowerCase());
      });
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return filteredData.slice(startIndex, endIndex);
    }
  }
}
</script>

<style scoped>
.container {
  margin: 20px;
}
.el-table__header th{
  color: #819d6a; /* 设置表格首行字体颜色 */
  font-size: 16px;
}

.el-button {
  transition: color 0.3s; /* 设置按钮字体颜色过渡效果 */
}

.el-button:hover {
  color: #4d5942; /* 设置按钮鼠标悬浮时的字体颜色 */
}

.el-table__body td .cell {
  max-width: 200px; /* 设置标题一列的固定宽度 */
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; /* 当文本内容超过最大宽度时，用省略号展示 */
}
.el-pagination .el-pager li:not(.disabled):hover {
  color: #819d6a; /* 设置分页器鼠标悬浮时的颜色 */
}

.el-pagination .el-pager li.active {
  color: #819d6a; /* 设置分页器被选中页的字体颜色 */
}

.el-input__inner:focus {
  border-color: #819d6a; /* 设置搜索框被点击时的边框颜色 */
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
.el-button:hover{
  border-color: #819d6a;
}
</style>