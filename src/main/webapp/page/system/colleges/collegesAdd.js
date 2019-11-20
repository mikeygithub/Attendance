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
        $.post(updateFlag==='0'?"../../../biz/colleges_save.action":"../../../biz/colleges_update.action",{//
            collegesId : updateFlag==='0'?null:$(".Id").val(),//id
            collegesCode : $(".collegesCode").val(),  //登录名
            collegesName : $(".collegesName").val(),  //邮箱
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

})