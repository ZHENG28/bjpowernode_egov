<%@page pageEncoding='utf-8' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
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
            font-size: 12px;
        }

        -->
    </style>

    <script type="text/javascript" src="/EGOV/js/formUtil.js"></script>
    <script type="text/javascript" src="/EGOV/js/jquery-3.6.0.js"></script>
    <script>
        // 面向过程开发（同添加用户信息的js校验代码，此处省略）
        // 面向对象开发
        function validData() {
            let username = new FormItem("用户姓名", "username");
            let userpswd = new FormItem("用户密码", "userpswd");
            let checkpswd = new FormItem("确认密码", "checkpswd");
            let orgtype = new FormItem("机构类型", "orgtype");
            let formArr = [username, userpswd, checkpswd, orgtype];
            return formCheck.isNotEmpty(formArr) && formCheck.isSame(userpswd, checkpswd);
        }

        function doUpdate() {
            if (validData()) {
                document.forms["userForm"].submit();
            }
        }

        // 清空表单
        function clearForm() {
            $("#username").val("${user.username}");
            $("#userpswd").val("${user.userpswd}");
            $("#checkpswd").val("${user.userpswd}");
            $("#orgtype").val("${user.orgtype}");
        }
    </script>
</head>
<body>
<form action="/EGOV/servlet/updateUser" method="post" name="userForm">
    <input type="hidden" name="pageno" value="${param.pageno}"/>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="30">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30"/></td>
                        <td width="1101" background="../images/tab_05.gif"><img src="../images/311.gif" width="16"
                                                                                height="16"/> <span
                                class="STYLE4">修改系统用户</span></td>
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
                                        <div align="left" style="padding:2px" class="STYLE2">${user.usercode}
                                            <input type="hidden" name="usercode" value="${user.usercode}"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">用户姓名</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                    value="${user.username}"
                                                                                                    name="username"
                                                                                                    id="username"
                                                                                                    style="width:100px; height:20px; border:solid 1px #035551; color:#000000">&nbsp;<font
                                                color='red'>*</font></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">用户密码</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2"><input type="password"
                                                                                                    value="${user.userpswd}"
                                                                                                    name="userpswd"
                                                                                                    id="userpswd"
                                                                                                    style="width:100px; height:20px; border:solid 1px #035551; color:#000000">&nbsp;<font
                                                color='red'>*</font></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">确认密码</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2"><input type="password"
                                                                                                    value="${user.userpswd}"
                                                                                                    name="checkpswd"
                                                                                                    id="checkpswd"
                                                                                                    style="width:100px; height:20px; border:solid 1px #035551; color:#000000">&nbsp;<font
                                                color='red'>*</font></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">机构类型</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2">
                                            <select name="orgtype" id="orgtype"
                                                    style="width:105px; height:20px; border:solid 1px #035551; color:#000000">
                                                <option value=""></option>
                                                <option value="0" ${user.orgtype eq "0"? "selected" : ""}>
                                                    外汇管理局
                                                </option>
                                                <option value="1" ${user.orgtype eq "1"? "selected" : ""}>
                                                    银行
                                                </option>
                                            </select>&nbsp;<font color='red'>*</font></div>
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
                                        style="padding-top:5px;padding-left:200px"><img src="../images/save.jpg"
                                                                                        style="cursor:hand"
                                                                                        onclick="doUpdate()"/>&nbsp;&nbsp;<img
                                            src="../images/clear.jpg" style="cursor:pointer" onclick="clearForm()"/>
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
</html>
