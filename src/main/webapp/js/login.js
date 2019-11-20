layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("暂未支持",{
            time:5000
        });
    })
    /**
     * 是否已经登录过
     */
    function isLogin() {
        var userId = window.sessionStorage.getItem("userId");
        return !$tool.isBlank(userId);
    }
    //登录按钮
    form.on("submit(login)",function(data){

        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");

            $.post("./login_userLogin.action",{
                loginAccount : $(".loginAccount").val(),
                loginPassword : $(".loginPassword").val()
            },function(data){
                console.log(JSON.stringify(data));
                if (data.code === 0 ){
                    //保存用户信息到session中
                    window.sessionStorage.setItem("userName",data.data.userName);
                    window.sessionStorage.setItem("userId",data.data.userId);
                    window.sessionStorage.setItem("roleType",data.data.roleType);
                    window.location.href = "page/common/index.html";
                } else {
                    layer.msg(data.msg);
                    $("#loginPassword").val('');//置为空
                }
            })

        $(this).text("登录").attr("disabled",false).removeClass("layui-disabled");
        $("#code").val('');//清空输入框

        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })

    //退出登入
    $(".signOut").click(function(){
        window.sessionStorage.clear();
        window.localStorage.clear();
        var index = layer.msg('退出登入中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("退出登入成功！");
        },1000);
        window.location.href = "/Attendance_war/index.html";
        // window.location.href = $tool.getContent()+"index.html";
    })

})
