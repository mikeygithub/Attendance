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
            table.render();
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
            {field: 'attendanceType', title: '操作', minWidth:200, align:'center',templet:function(d){
                    return '<div class="layui-btn-container">'+
                        '<input name="attendance_type'+d.studentId+'" type="radio" value="0" title="正常" checked="checked">' +
                        '<input name="attendance_type'+d.studentId+'" type="radio" value="1" title="迟到">' +
                        '<input name="attendance_type'+d.studentId+'" type="radio" value="2" title="早退">' +
                        '<input name="attendance_type'+d.studentId+'" type="radio"  value="3" title="请假">' +
                        '<input name="attendance_type'+d.studentId+'" type="radio" value="4" title="旷课"></div';
            }},
            // {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
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
                    classId: $(".classesId").val(),
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
    //批量删除
    $(".attendance_save").click(function(){
        //attendance_stu_id
        //attendance_type
        //attendance_cas_id
        var attendance_cas_id = $(".courseId").val();

        var allStu = new Array();
        //获取缺勤类型
        layui.table.cache["userListTable"].forEach(item =>{
            var att = {attendanceType:$('input[name=attendance_type'+item.studentId+']:checked').val(),attendanceStuId:item.studentId,attendanceCasId:attendance_cas_id};
            allStu.push(att);
        });

        console.log(allStu);

        layer.confirm('确定保存？', {icon: 3, title: '提示信息'}, function (index) {
            $.post("../../../biz/attendance_saveByBatch.action",{
                bizAttendanceEntities : JSON.stringify(allStu)
            },function(data){
                if (data.code===0){
                    layer.msg("保存成功");
                }else {
                    layer.msg("保存失败");
                }
                tableIns.reload();
                layer.close(index);
            })
        })
    })
})
