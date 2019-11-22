layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

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
        })
    })

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : '../../../biz/student_findByClassIdWithPage.action',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [50,100,200,300],
        limit : 50,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'studentId', title: 'Id', align:'center'},
            {field: 'studentName', title: '姓名', minWidth:100, align:"center"},
            {field: 'studentCode', title: '学生编号',minWidth:100, align:'center'},
            {field: 'studentPhone', title: '联系电话', minWidth:100, align:'center'},
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]],
        page: true
    });
    //提交数据
    $(".submit_option").on("click",function(){
        if($(".attendance_type").val() != ''){
            var index = layer.msg('查询中,请稍候...',{icon: 16,time:false,shade:0})
            setTimeout(function(){
                table.reload("userListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    classesId: $(".classesId").val(),
                    attendanceType: $(".attendance_type").val(),
                    number: $(".number").val()
                }
            });
            layer.close(index);
        },800)
        } else{
            layer.msg("请选择好数据！",{icon:2});
        }
    });
    //
    // //添加用户
    // function addUser(edit){
    //     var index = layui.layer.open({
    //         title : "添加",
    //         type : 2,
    //         content : "attendanceAdd.html",
    //         success : function(layero, index){
    //             var body = layui.layer.getChildFrame('body', index);
    //             if(edit){
    //                 body.find(".Id").val(edit.collegeId);
    //                 body.find(".collegeCode").val(edit.collegeCode);  //登录名
    //                 body.find(".collegeName").val(edit.collegeName);  //邮箱
    //                 body.find(".updateFlag").val(1);//更新标识
    //                 form.render();
    //             }
    //         }
    //     })
    //     layui.layer.full(index);
    //     window.sessionStorage.setItem("index",index);
    //     //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
    //     $(window).on("resize",function(){
    //         layui.layer.full(window.sessionStorage.getItem("index"));
    //     })
    // }
    // $(".addNews_btn").click(function(){
    //     addUser();
    // })
    //
    // //批量删除
    // $(".delAll_btn").click(function(){
    //     var checkStatus = table.checkStatus('userListTable'),
    //         data = checkStatus.data,
    //         newsId = [];
    //     if(data.length > 0) {
    //         for (var i in data) {
    //             newsId.push(data[i].collegeId);
    //         }
    //         layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
    //             $.post("../../../biz/attendance_delete.action",{
    //                 ids : newsId.join(',') //将需要删除的newsId作为参数传入
    //             },function(data){
    //                 if (data.code===0){
    //                     layer.msg("删除成功");
    //                 }else {
    //                     layer.msg("删除失败");
    //                 }
    //             tableIns.reload();
    //             layer.close(index);
    //             })
    //         })
    //     }else{
    //         layer.msg("请选择需要删除的用户");
    //     }
    // })
    //
    // //列表操作
    // table.on('tool(userList)', function(obj){
    //     var layEvent = obj.event,
    //         data = obj.data;
    //
    //     if(layEvent === 'edit'){ //编辑
    //         addUser(data);
    //     }else if(layEvent === 'usable'){ //启用禁用
    //         var _this = $(this),
    //             usableText = "是否确定禁用此用户？",
    //             btnText = "已禁用";
    //         if(_this.text()=="已禁用"){
    //             usableText = "是否确定启用此用户？",
    //             btnText = "已启用";
    //         }
    //         layer.confirm(usableText,{
    //             icon: 3,
    //             title:'系统提示',
    //             cancel : function(index){
    //                 layer.close(index);
    //             }
    //         },function(index){
    //             _this.text(btnText);
    //             layer.close(index);
    //         },function(index){
    //             layer.close(index);
    //         });
    //     }else if(layEvent === 'del'){ //删除
    //         layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
    //             $.get("../../../biz/attendance_delete.action",{
    //                 collegeId : data.collegeId  //将需要删除的newsId作为参数传入
    //             },function(data){
    //                 if (data.code === 0){
    //                     layer.msg("删除成功");
    //                 }else {
    //                     layer.msg("删除失败");
    //                 }
    //                 tableIns.reload();
    //                 layer.close(index);
    //             })
    //         });
    //     }
    // });

})
