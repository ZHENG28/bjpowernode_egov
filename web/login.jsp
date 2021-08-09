<%@page pageEncoding="utf-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>外汇业务信息管理平台_用户登录</title>
    <style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
            overflow: hidden;
        }

        .STYLE1 {
            font-size: 12px;
            color: #a1c8c6;
        }

        .STYLE4 {
            color: #FFFFFF;
            font-size: 12px;
        }

        -->
    </style>
    <script src="js/jquery-3.6.0.js"></script>
    <script src="js/formUtil.js"></script>
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

        function validData() {
            let orgtype = new FormItem("机构类型", "orgtype");
            let usercode = new FormItem("用户代码", "usercode");
            let userpwd = new FormItem("用户密码", "userpwd");
            let formArr = [orgtype, usercode, userpwd];
            return formCheck.isNotEmpty(formArr);
        }

        function doLogin() {
            if (validData()) {
                document.forms["loginForm"].submit();
            }
        }

        function clearForm() {
            $("#orgtype").val("");
            $("#usercode").val("");
            $("#userpwd").val("");
        }
    </script>
</head>
<body onload="pageLoad()">
<form action="servlet/login" method="post" name="loginForm">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td bgcolor="035551">&nbsp;</td>
        </tr>
        <tr>
            <td height="311" background="img/login_03.gif">
                <table width="758" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td height="210" background="img/login1.jpg">&nbsp;</td>
                    </tr>
                    <tr>
                        <td height="101">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="446" height="101" background="img/login_06.jpg">&nbsp;</td>
                                    <td width="156">
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <td width="40%" height="22"><span class="STYLE4">机构类型</span></td>
                                                <td width="71" height="22">
                                                    <select name="orgtype" id="orgtype"
                                                            style="width:100px; height:20px; border:solid 1px #035551; color:#000000">
                                                        <option value=""></option>
                                                        <option value="0">外汇管理局</option>
                                                        <option value="1">银行</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="22"><span class="STYLE4">用户代码</span></td>
                                                <td width="71" height="22"><input type="text" name="usercode"
                                                                                  id="usercode"
                                                                                  style="width:100px; height:20px; border:solid 1px #035551; color:#000000">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="22"><span class="STYLE4">用户密码</span></td>
                                                <td height="22"><input type="password" name="userpwd" id="userpwd"
                                                                       style="width:100px; height:22px; border:solid 1px #035551; color:#000000">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="25">&nbsp;</td>
                                                <td height="25">
                                                    <img src="img/dl.gif" width="37" onclick="doLogin()"
                                                         style="cursor: pointer"
                                                         height="19" border="0">
                                                    <img src="img/qx.gif" onclick="clearForm()" style="cursor: pointer"
                                                         width="37" height="19">
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="156" background="img/login_09.gif">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td bgcolor="1f7d78">&nbsp;</td>
        </tr>
        <tr>
            <td bgcolor="1f7d78">
                <div align="center"><span class="STYLE1">-- 北京动力节点 2012 --</span></div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
