layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : '../../../biz/attendance_findByPage.action',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'attendanceId', title: 'ID', align:'center'},
            {field: 'attendanceType', title: '类型', minWidth:100, align:"center",templet:function (d) {
                switch (d.attendanceType) {
                    case 0:return '<button type="button" class="layui-btn layui-btn-primary layui-btn-radius layui-btn-sm">正常</button>';
                    case 1:return '<button type="button" class="layui-btn layui-btn-radius layui-btn-sm">迟到</button>';
                    case 2:return '<button type="button" class="layui-btn layui-btn-normal layui-btn-radius layui-btn-sm">早退</button>';
                    case 3:return '<button type="button" class="layui-btn layui-btn-warm layui-btn-radius layui-btn-sm">请假</button>';
                    case 4:return '<button type="button" class="layui-btn layui-btn-danger layui-btn-radius layui-btn-sm">旷课</button>';
                    }
                }
             },
            {field: 'attendanceTime', title: '时间', minWidth:100, align:"center",templet:function (d) {
                    if (d != null && d.attendanceTime != null&&d.attendanceTime!='') return d.attendanceTime.replace('T',' ');
                }},
            {field: 'attendanceStuId', title: '学生姓名', minWidth:100, align:"center",templet:function(d){
                    if (d != null && d.sysStudentEntity != null&&d.sysStudentEntity!='') return d.sysStudentEntity.studentName;
            }},
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]],
        done:function(res,curr,count){
          exportData=res.data;
        },
        page: true
    });
    //获取当前教师的课程
    $.post('../../../biz/course_findByPage.action',{
        teacherId : window.sessionStorage.getItem('userId'),
        page : 1,
        limit : 100000
    },function(data){
        if (data.code === 0){
            //渲染课程下拉框
            for (var i = 0; i < data.data.length; i++) {
                $(".courseId").append(new Option(data.data[i].courseName,data.data[i].courseId));
            }
            form.render('select');
        }else {
            layer.msg("获取课程数据失败");
        };
        form.render();
    })

    //课程的下拉框监听事件
    form.on('select(coursefilter)',function (data) {
        $.post("../../../biz/classes_getClassesByCourseIdSimple.action",{
            courseId : data.value  //将需要删除的newsId作为参数传入
        },function(res){
            if (res.code === 0){
                //渲染班级下拉框
                $(".classesId").empty();
                for (var i = 0; i < res.classes.length; i++) {
                    if (res.classes[i]!=null&&res.classes[i]!=""){
                        $(".classesId").append(new Option(res.classes[i].classesName,res.classes[i].classesId));
                    }
                }
                form.render('select');
            }else {
                layer.msg("获取班级数据失败");
            };
            form.render();
            table.render();
        })
    })

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() == ''&&$(".courseId").val()==''&& $(".classesId").val()=='') {
            layer.msg("请输入搜索的内容");
        }else {
            var index = layer.msg('查询中,请稍候...',{icon: 16,time:false,shade:0})
            setTimeout(function(){
                table.reload("userListTable",{
                    page: {
                        curr: 1 //重新从第 1 页开始
                    },
                    where: {
                        key: $(".searchVal").val(),  //搜索的关键字
                        courseId: $(".courseId").val(),
                        classId: $(".classesId").val()
                    }
                });
                layer.close(index);
            },800)
        }
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加",
            type : 2,
            content : "attendanceAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".Id").val(edit.collegeId);
                    body.find(".collegeCode").val(edit.collegeCode);  //登录名
                    body.find(".collegeName").val(edit.collegeName);  //邮箱
                    body.find(".updateFlag").val(1);//更新标识
                    form.render();
                }
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addNews_btn").click(function(){
        addUser();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].collegeId);
            }
            layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("../../../biz/attendance_delete.action",{
                    ids : newsId.join(',') //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code===0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                tableIns.reload();
                layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        console.log(layEvent)
        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此用户？",
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                _this.text(btnText);
                layer.close(index);
            },function(index){
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                $.get("../../../biz/attendance_delete.action",{
                    collegeId : data.collegeId  //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code === 0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });
    //导出
    form.on('submit(uploadImg)', function(data){
       var loading = layer.load(1, {shade: [0.3, '#fff']});
        table.exportFile(tableIns.config.id,exportData,'xls');
        layer.close(loading);
    });
})
