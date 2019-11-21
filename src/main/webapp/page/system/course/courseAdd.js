layui.use(['form','layer','transfer','util'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        transfer = layui.transfer,
        util = layui.util,
        $ = layui.jquery;

    form.on("submit(addUser)",function(data){
        //新增,更新
        var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.post(updateFlag==='0'?"../../../biz/course_save.action":"../../../biz/course_update.action",{//
            courseId : updateFlag==='0'?null:$(".Id").val(),//id
            courseCode : $(".courseCode").val(),  //登录名
            courseName : $(".courseName").val(),  //邮箱
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


    //穿梭
    //模拟数据
    var data1 = [
        {"value": "1", "title": "李白"}
        ,{"value": "2", "title": "杜甫"}
        ,{"value": "3", "title": "苏轼"}
        ,{"value": "4", "title": "李清照"}
        ,{"value": "5", "title": "鲁迅", "disabled": true}
        ,{"value": "6", "title": "巴金"}
        ,{"value": "7", "title": "冰心"}
        ,{"value": "8", "title": "矛盾"}
        ,{"value": "9", "title": "贤心"}
    ]

    //实例调用
        transfer.render({
        elem: '#classes',
        showSearch: true,
        title: ['未选班级', '已选班级'],
        data: data1,
        id: 'classesCon', //定义唯一索引
        onchange:function (data, index) {
            console.log(data); //得到当前被穿梭的数据
            console.log(index); //如果数据来自左边，index 为 0，否则为 1
        }
    })
    //批量办法定事件
    util.event('lay-demoTransferActive', {
        getData: function(othis){
            var getData = transfer.getData('key123'); //获取右侧数据
            layer.alert(JSON.stringify(getData));
        }
        ,reload:function(){
            //实例重载
            transfer.reload('key123', {
                title: ['文人', '喜欢的文人']
                ,value: ['2', '5', '9']
                ,showSearch: true
            })
        }
    });

})