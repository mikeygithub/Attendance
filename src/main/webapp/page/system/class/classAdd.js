layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addUser)",function(data){
        //新增,更新
        var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.post(updateFlag==='0'?"../../../biz/classes_save.action":"../../../biz/classes_update.action",{//
            classesId : updateFlag==='0'?null:$(".Id").val(),//id
            classesCode : $(".classesCode").val(),  //登录名
            classesName : $(".classesName").val()  //邮箱
        },function(res){
            if (res.code === 0){
                top.layer.close(index);
                top.layer.msg(updateFlag==='0'?"添加成功！":"修改成功");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }else {
                top.layer.close(index);
                top.layer.msg("操作失败！");
            }
        })
        return false;
    })

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

    //穿梭框请求数据
    $.post("../../../biz/classes_getStudentByClassId.action",{
        classId : $(".Id").val() //将需要删除的newsId作为参数传入
    },function(data){
        console.log(data)
        if (data.code===0){
            //实例调用
            transfer.render({
                elem: '#students',
                showSearch: true,
                title: ['未选学生', '已选学生'],
                data: data.classes,
                id: 'studentsCon', //定义唯一索引
                onchange:function (data, index) {
                    console.log(data); //得到当前被穿梭的数据
                    console.log(index); //如果数据来自左边，index 为 0，否则为 1
                    for (i=0;i<data.length;i++){
                        classes+=data[i].value+",";
                        console.log(classes);
                    }

                }
            })
        }else {
            layer.msg("获取数据失败");
        }
    })
})