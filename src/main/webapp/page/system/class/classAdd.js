layui.use(['form','layer','transfer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer, transfer = layui.transfer,
        $ = layui.jquery;

    //穿梭框请求数据
    $.post("../../../biz/student_getStudentByClassId.action",{
        classId : $(".Id").val() //将需要删除的newsId作为参数传入
    },function(data){
        if (data.code===0){
            //实例调用
            transfer.render({
                elem: '#students',
                showSearch: true,
                title: ['未选学生', '已选学生'],
                data: data.data,
                value: data.select,//已经选
                id: 'studentsCon', //定义唯一索引
                onchange:function (data, index) {
                    // if (index==0){//添加
                    //     data.forEach(item=>{stus+=item.value+",";})
                    // } else if (index==1) {//移除
                    //     console.log('移除'+JSON.stringify(data))
                    //     data.forEach(item=>{
                    //         stus = stus.replace(','+item.value+',',',')
                    //     });
                    // }
                    // console.log(stus);
                }
            })
        }else {
            layer.msg("获取数据失败");
        }
    })


    form.on("submit(addUser)",function(data){
        //新增,更新
        var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        //获取穿梭框右侧数据
        var stus = "";
        transfer.getData('studentsCon').forEach(item=>{stus += item.value+',';});
        // 实际使用时的提交信息
        $.post(updateFlag==='0'?"../../../biz/classes_save.action":"../../../biz/classes_update.action",{//
            classesId : updateFlag==='0'?null:$(".Id").val(),//id
            classesCode : $(".classesCode").val(),  //登录名
            classesName : $(".classesName").val(),  //邮箱
            students: stus.replace(/^,+/,"").replace(/,$/,"")
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
    var stus = ',';

})