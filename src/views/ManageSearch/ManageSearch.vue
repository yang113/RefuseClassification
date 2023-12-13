<template>
  <div>
    <el-form :inline="true" :model="addGarbage" class="demo-form-inline" style="padding-top:15px">
      <el-form-item label="垃圾类别">
        <el-input size="small" v-model="addGarbage.class" placeholder="请输入垃圾类别"></el-input>
      </el-form-item>
      <el-form-item label="垃圾小类">
        <el-input size="small" v-model="addGarbage.min_class" placeholder="请输入垃圾小类"></el-input>
      </el-form-item>
      <el-form-item label="具体垃圾">
        <el-input size="small" v-model="addGarbage.garbage" placeholder="请输入具体垃圾"></el-input>
      </el-form-item>
      <el-form-item label="处理方式">
        <el-input size="small" v-model="addGarbage.way" placeholder="请输入垃圾处理方式"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addRubbish" size="small" style="background-color: #819d6a">添加</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="resetRubbish" size="small" style="background-color: #819d6a">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
        :data="GarbageData"
        border
        style="width: 90%;margin-left: 60px">
      <el-table-column
          fixed
          prop="class"
          label="垃圾类别"
          width="250"
          height="50">
      </el-table-column>
      <el-table-column
          prop="min_class"
          label="垃圾小类"
          width="250"
          height="50">
      </el-table-column>
      <el-table-column
          prop="garbage"
          label="具体垃圾"
          width="250"
          height="50">
      </el-table-column>
      <el-table-column
          prop="way"
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
        :total="GarbageData.length">
    </el-pagination>
    <el-dialog title="修改垃圾信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="垃圾类别" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.class" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="垃圾小类" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.min_class" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="具体垃圾" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.garbage" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="处理方式" :label-width="formLabelWidth">
          <el-input size="small" v-model="form.way" autocomplete="off"></el-input>
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
export default {
  name: "ManageGarbage",
  data() {
    return {
      addGarbage:{
        class:'',
        min_class:'',
        garbage:'',
        way:''
      },
      GarbageData: [{
        class: '可回收垃圾',
        min_class: '废纸类',
        garbage: '草稿纸',
        way:'回收'
      }, {
        class: '可回收垃圾',
        min_class: '金属类',
        garbage: '铁勺',
        way:'回收'
      }, {
        class: '有害垃圾',
        min_class: '废水银类',
        garbage: '体温计',
        way:'专业机构'
      }, {
        class: '湿垃圾',
        min_class: '剩菜剩饭',
        garbage: '过期的食物',
        way:'堆肥'
      }, {
        class: '大件垃圾',
        min_class: '废家具类',
        garbage: '沙发',
        way:'回收'
      }, {
        class: '可回收垃圾',
        min_class: '玻璃类',
        garbage: '水杯',
        way:'焚烧'
      }, {
        class: '湿垃圾',
        min_class: '植物残枝',
        garbage: '花草',
        way:'堆肥'
      }],
      currentPage: 1,
      pageSize: 9,
      dialogFormVisible:false,
      form:{
        class:'',
        min_class:'',
        garbage:'',
        way:''
      },
      formLabelWidth:'120px'
    }
  },
  methods: {
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    handleClick(row) {
      this.dialogFormVisible = true;
    },
    confirmEdit(){
      this.dialogFormVisible = false;

    },
    DeleteGarbage(row){
      this.$confirm('是否确认删除？','提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type:'warning'
      }).then(()=>{
        let garbage = row.garbage;
        let newData = this.tableData.filter(item => item.garbage !== garbage);
        this.tableData = newData;
      })
    },
    addRubbish(){
      this.GarbageData.push(this.addGarbage);
    },
    resetRubbish(){
      this.addGarbage = {
        class:'',
        min_class:'',
        garbage:'',
        way:''
      }
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