<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body onload="pageLoad()">
<form action="/EGOV/servlet/addUser" method="post">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="30">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30"/></td>
                        <td width="1101" background="../images/tab_05.gif"><img src="../images/311.gif" width="16"
                                                                                height="16"/> <span
                                class="STYLE4">新增系统用户</span></td>
                        <td width="281" background="../images/tab_05.gif">
                            <table border="0" align="right" cellpadding="0" cellspacing="0">
                            </table>
                        </td>
                        <td width="14"><img src="../images/tab_07.gif" width="14" height="30"/></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
                        <td bgcolor="#f3ffe3">
                            <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1"
                                   bgcolor="#0e6f68">
                                <tr height="26"></tr>
                                <tr>
                                    <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">用户代码</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2">
                                            <input type="text" name="usercode" id="usercode"
                                                   onblur="checkUsercode(this.value)"
                                                   style="width:100px; height:20px; border:solid 1px #035551; color:#000000">
                                            &nbsp;<font color='red'>*</font>&nbsp;<span id="isusercodeEmpty"></span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">用户姓名</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2">
                                            <input type="text" name="username" id="username"
                                                   onblur="checkEmpty(new FormItem('用户姓名','username'))"
                                                   style="width:100px; height:20px; border:solid 1px #035551; color:#000000">
                                            &nbsp;<font color='red'>*</font>&nbsp;<span id="isusernameEmpty"></span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">用户密码</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2">
                                            <input type="password" name="userpswd" id="userpswd"
                                                   onblur="checkEmpty(new FormItem('用户密码','userpswd'))"
                                                   style="width:100px; height:20px; border:solid 1px #035551; color:#000000">
                                            &nbsp;<font color='red'>*</font>&nbsp;<span id="isuserpswdEmpty"></span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">确认密码</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2">
                                            <input type="password" name="checkpswd" id="checkpswd"
                                                   onblur="checkEmpty(new FormItem('确认密码','checkpswd'));checkPwd();"
                                                   style="width:100px; height:20px; border:solid 1px #035551; color:#000000">
                                            &nbsp;<font color='red'>*</font>&nbsp;<span id="ischeckpswdEmpty"></span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">机构类型</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2">
                                            <select name="orgtype" id="orgtype"
                                                    onblur="checkEmpty(new FormItem('机构类型','orgtype'))"
                                                    style="width:105px; height:20px; border:solid 1px #035551; color:#000000">
                                                <option value=""></option>
                                                <option value="0">外汇管理局</option>
                                                <option value="1">银行</option>
                                            </select>
                                            &nbsp;<font color='red'>*</font>&nbsp;<span
                                                id="isorgtypeEmpty"></span>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="9" background="../images/tab_16.gif">&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
                        <td bgcolor="#f3ffe3">
                            <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1"
                                   bgcolor="#0e6f68">
                                <tr height="26">
                                    <td bgcolor="#FFFFFF" height="26" class="STYLE1" colspan="2"
                                        style="padding-top:5px;padding-left:200px">
                                        <img src="../images/save.jpg" style="cursor:pointer" onclick="doSave()"/>
                                        <img src="../images/clear.jpg" style="cursor:pointer" onclick="clearForm()"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="9" background="../images/tab_16.gif">&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="29">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15" height="29"><img src="../images/tab_20.gif" width="15" height="29"/></td>
                        <td background="../images/tab_21.gif">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="75%" valign="top" class="STYLE1">
                                        <div align="left">
                                            <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td width="62" height="22" valign="middle"></td>
                                                    <td width="50" height="22" valign="middle"></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="14"><img src="../images/tab_22.gif" width="14" height="29"/></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
</body>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }

        .STYLE1 {
            font-size: 12px
        }

        .STYLE4 {
            font-size: 12px;
            color: #1F4A65;
            font-weight: bold;
        }

        a:link {
            font-size: 12px;
            color: #06482a;
            text-decoration: none;

        }

        a:visited {
            font-size: 12px;
            color: #06482a;
            text-decoration: none;
        }

        a:hover {
            font-size: 12px;
            color: #FF0000;
            text-decoration: underline;
        }

        a:active {
            font-size: 12px;
            color: #FF0000;
            text-decoration: none;
        }

        .STYLE7 {
            font-size: 12px
        }

        #isusercodeEmpty,
        #isusernameEmpty,
        #isuserpswdEmpty,
        #ischeckpswdEmpty,
        #isorgtypeEmpty {
            color: red;
        }

        -->
    </style>

    <script type="text/javascript" src="/EGOV/js/formUtil.js"></script>
    <script type="text/javascript" src="/EGOV/js/jquery-3.6.0.js"></script>
    <script>
        function pageLoad() {
            <%
                Object errMsg = request.getAttribute("errMsg");
                if(errMsg!=null){
            %>
            alert("<%=errMsg%>");
            <%
                }
            %>
        }

        let usercode = new FormItem("用户代码", "usercode");
        let username = new FormItem("用户姓名", "username");
        let userpswd = new FormItem("用户密码", "userpswd");
        let checkpswd = new FormItem("确认密码", "checkpswd");
        let orgtype = new FormItem("机构类型", "orgtype");

        // 校验表单
        function validData() {
            let formArr = [usercode, username, userpswd, checkpswd, orgtype];
            return formCheck.isNotEmpty(formArr) && formCheck.isSame(userpswd, checkpswd);
        }

        // 提交用户表单信息，保存数据
        function doSave() {
            if (validData()) {
                document.forms[0].submit();
            }
        }

        // 清空表单
        function clearForm() {
            $("#usercode").val("");
            $("#username").val("");
            $("#userpswd").val("");
            $("#checkpswd").val("");
            $("#orgtype").val("");
        }

        function checkEmpty(formItem) {
            if (!formCheck.isNotEmpty([formItem])) {
                return;
            }
        }

        function checkPwd() {
            return formCheck.isSame(userpswd, checkpswd);
        }

        function checkUsercode(usercode) {
            // 纯JS手写AJAX
            // let xRequest;
            // if (window.XMLHttpRequest) {
            //     xRequest = new XMLHttpRequest();
            // } else if (window.ActiveXObject) {
            //     xRequest = new ActiveXObject("Microsoft.XMLHTTP");
            // }
            //
            // xRequest.onreadystatechange = function () {
            //     if (xRequest.readyState == 4) {
            //         if (xRequest.status == 200) {
            //             document.getElementById("checkUserCode").innerHTML = xRequest.responseText;
            //         }
            //     }
            // };
            //
            // let timeStamp = new Date().getTime();
            // xRequest.open("GET", "/EGOV/servlet/checkCode?_=" + timeStamp + "&usercode=" + usercode, true);
            //
            // xRequest.send();
            $.ajax({
                url: "/EGOV/servlet/checkCode",
                data: {
                    "_": new Date().getTime(),
                    "usercode": usercode
                },
                success: function (response) {
                    alert(response);
                    $("#isusercodeEmpty").html(response);
                },
                error: function (xmlHttpRequest, textStatus, errorThrown) {
                    // 无法直接接收<font></font>形式的返回值
                    $("#isusercodeEmpty").html(xmlHttpRequest.responseText);
                    console.log("fail - " + textStatus);
                    console.log(errorThrown);
                }
            });
        }
    </script>
</head>
</html>
