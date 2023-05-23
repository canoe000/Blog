$(function () {
    var presentListType;

    init();

    function init() {

        var listType = getUrlParam("listType");
        console.log(listType);
        if (listType != null) {
            presentListType = listType;
            if (listType === 'admin') {
                sentRequestForUserList("/listAllAdmin");
                $("#add_admin").show();
            } else {
                sentRequestForUserList("/listAllRegularUser");
                $("#add_admin").hide();
            }
        } else {
            presentListType = "regularUser";
            sentRequestForUserList("/listAllRegularUser");
            $("#add_admin").hide();
        }
    }

    //显示普通用户列表
    $("#btn_regular_user_list").click(function () {
        presentListType = "regularUser";
        $("#add_admin").hide();
        sentRequestForUserList("/listAllRegularUser");
    })

    //显示管理员用户列表
    $("#btn_admin_list").click(function () {
        presentListType = "admin";
        $("#add_admin").show();
        sentRequestForUserList("/listAllAdmin");
    })

    //绑定数据到用户列表
    function bindRes(res) {
        var html = "";
        for (var i = 0; i < res.length; i++) {
            console.log("res[i].id" + res[i].id);
            html +=
                "     <div class=\"row text-center border mx-2 mb-2\">\n" +
                "<input type=\"text\" name=\"id\" class=\"w-100 form-c\" hidden=\"hidden\" value=" + res[i].id + ">" +
                "                        <div class=\"col-4 border\">\n" +
                "                            <input type=\"text\" name=\"account\" " +
                "class=\"w-100 form-control\" value=" + res[i].account + " disabled>\n" +
                "                        </div>\n" +
                "                        <div class=\"col-4 border\">\n" +
                "                            <input type=\"text\" name=\"password\" " +
                "class=\"w-100 form-control\" value=" + res[i].password + ">\n" +
                "                        </div>\n" +
                "                        <div class=\"col-4 border\">\n" +
                "                            <button class=\"btn btn-outline-success btn_save_user\">保存</button>\n" +
                "                            <button class=\"btn btn-outline-danger btn_delete_user\">删除</button>\n" +
                "                        </div>\n" +
                "                    </div>";
        }
        $("#user_list").html(html);
    }

    //请求用户列表
    function sentRequestForUserList(url) {
        $.ajax({
            url: url,
            type: 'get',
            success: function (res) {
                if (res !== null && res !== "") {
                    bindRes(res);
                    //绑定按钮事件
                    $(".btn_save_user").click(btnSaveUserEvent);
                    $(".btn_delete_user").click(btnDeleteUserEvent);
                    $(".btn_add_admin").click(btnAddAdminEvent);
                }
            }
        })
    }

    //保存用户按钮事件
    function btnSaveUserEvent() {
        console.log(true)
        var userRowDiv = $(this).parent().parent();
        var id = userRowDiv.find("input[name='id']").val();
        var password = userRowDiv.find("input[name='password']").val();
        console.log("id:" + id)
        if (presentListType === 'regularUser') {
            saveUser('/updateRegularUserPassword', id, password);
        } else {
            saveUser('/updateAdminPassword', id, password);
        }
    }

    //删除用户按钮事件
    function btnDeleteUserEvent() {
        var userRowDiv = $(this).parent().parent();
        var id = userRowDiv.find("input[name='id']").val();
        if (presentListType === 'regularUser') {
            deleteUser('/deleteRegularUserById', id);
        } else {
            deleteUser('/deleteAdminById', id);
        }
    }

    //删除用户
    function deleteUser(url, id) {
        $.ajax({
            url: url,
            type: 'post',
            data: {
                "id": id
            },
            success: function (res) {
                window.location.href = "/userList?listType=" + presentListType;
            }
        })
    }

    //保存用户
    function saveUser(url, id, password) {
        console.log("password:" + password)
        $.ajax({
            url: url,
            type: 'post',
            data: {
                "id": id,
                "password": password
            },
            success: function (res) {
                window.location.href = "/userList?listType=" + presentListType;
            }
        })
    }
//todo 添加有问题
    //添加管理员按钮事件
    function btnAddAdminEvent() {
        var addAdminRowDiv = $(this).parent().parent();
        var account = addAdminRowDiv.find("input[name='admin_account']").val();
        var password = addAdminRowDiv.find("input[name='admin_password']").val();
        if (account == null || account.length <= 0 || account.length > 10 ||
            password == null || password.length <= 0 || password.length > 20) {
            toast("添加失败", "账户或密码不合理");
        } else {
            addAdmin(account, password);
        }
    }

    //添加管理员
    function addAdmin(account, password) {
        $.ajax(
            {
                url: "/addAdmin",
                type: "post",
                data: {
                    "account": account,
                    "password": password
                },
                success: function (res) {
                    window.location.href = "/userList?listType=" + presentListType;
                }
            }
        )
    }
})