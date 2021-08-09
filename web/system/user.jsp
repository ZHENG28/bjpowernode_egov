<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="egov" uri="http://www.egov.com/jsp/jstl/functions" %>
<%@ page import="com.zj.egov.bean.Page" %>
<%@ page import="com.zj.egov.bean.User" %>
<%@page pageEncoding="utf-8" %>
<%
    Page<User> thisPage = (Page<User>) request.getAttribute("page");
    int totalPage = thisPage.getTotalpage();
    int pageNo = thisPage.getPageno();
    pageContext.setAttribute("isNotFirstPage", pageNo > 1);
    pageContext.setAttribute("isNotLastPage", pageNo < totalPage);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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

        #updateBtn,
        #deleteBtn {
            border: none;
            background-color: transparent;
            outline: none;
        }

        -->
    </style>

    <script type="text/javascript" src="/EGOV/js/jquery-3.6.0.js"></script>
    <script type="text/javascript">
        // 翻页功能
        function changePage(pageno) {
            // 使用ajax更合理
            document.location = "/EGOV/servlet/pageQueryUser?pageno=" + pageno;
        }

        // 跳转页码
        function goPage() {
            // 获取页码
            let pagenoObj = $("#mypageno");
            let pageno = pagenoObj.val();
            let pageNo = parseInt(pageno);

            // 非空验证
            if (pageno == "") {
                alert("请输入跳转页码");
                pagenoObj.focus();
                return;
            }

            // 数字验证
            if (isNaN(pageno)) {
                alert("页码必须为数字，请重新填写");
                pagenoObj.val("");
                pagenoObj.focus();
                return;
            }

            // 范围验证
            if (pageNo < 1 || pageNo > ${page.getTotalpage()}) {
                alert("请输入[1-${page.getTotalpage()}]范围内的页码");
                pagenoObj.val("");
                pagenoObj.focus();
                return;
            }

            // 跳转
            changePage(pageNo);
        }

        // 删除和修改按钮的联动效果
        function modifyDisabled() {
            // 获取所有的复选框
            let checkboxs = document.getElementsByName("checkboxInfo");
            // 获取全选框
            let selectAll = document.getElementById("selectAll");
            let checkedCount = 0;
            for (let i = 0; i < checkboxs.length; i++) {
                let checkbox = checkboxs[i];
                if (checkbox.checked) {
                    // 循环统计被选中的个数
                    checkedCount++;
                }
            }
            selectAll.checked = (checkedCount == checkboxs.length);

            let updateBtn = document.getElementById("updateBtn");
            let deleteBtn = document.getElementById("deleteBtn");
            // 选中0个（都不能用）
            if (checkedCount < 1) {
                updateBtn.disabled = true;
                updateBtn.src = "../images/update_disabled.jpg";
                deleteBtn.disabled = true;
                deleteBtn.src = "../images/delete_disabled.jpg";
            } else if (checkedCount == 1) {
                // 选中1个（都能用）
                updateBtn.disabled = false;
                updateBtn.src = "../images/update.jpg";
                deleteBtn.disabled = false;
                deleteBtn.src = "../images/delete.jpg";
            } else {
                // 选中1+个（仅有删除能用，修改不可用）
                updateBtn.disabled = true;
                updateBtn.src = "../images/update_disabled.jpg";
                deleteBtn.disabled = false;
                deleteBtn.src = "../images/delete.jpg";
            }
        }

        // 全选和取消全选
        function chooseAll() {
            let selectAll = document.getElementById("selectAll");
            let checkboxs = document.getElementsByName("checkboxInfo");
            // 全选 and 取消全选
            for (let i = 0; i < checkboxs.length; i++) {
                checkboxs[i].checked = selectAll.checked;
            }
            modifyDisabled();
        }

        // 前往修改页面
        function goUpdate() {
            document.forms["userForm"].submit();
        }

        // 删除用户信息
        function deleteUsers() {
            let form = document.forms["userForm"];
            if (confirm("您确定要删除这些用户信息吗？")) {
                form.action = "/EGOV/servlet/deleteUser?pageno=" + ${page.pageno};
                form.submit();
            } else {
                form.action = "";
            }
        }
    </script>
</head>
<body onload="modifyDisabled()">
<form action="/EGOV/servlet/goUpdate" method="get" name="userForm">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="30">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30"/></td>
                        <td width="1101" background="../images/tab_05.gif"><img src="../images/311.gif" width="16"
                                                                                height="16"/> <span
                                class="STYLE4">系统用户列表</span></td>
                        <td width="281" background="../images/tab_05.gif">
                            <table border="0" align="right" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td width="60">
                                        <table width="90%" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td class="STYLE1">
                                                    <div align="center">
                                                        <img src="../images/add.jpg"
                                                             style="cursor: pointer"
                                                             onclick="document.location='/EGOV/system/userAdd.jsp'"/>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="60">
                                        <table width="90%" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td class="STYLE1">
                                                    <div align="center">
                                                        <input type="image" src="../images/update_disabled.jpg"
                                                               id="updateBtn" disabled onclick="goUpdate()">
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="60">
                                        <table width="90%" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td class="STYLE1">
                                                    <div align="center">
                                                        <input type="image" src="../images/delete_disabled.jpg"
                                                               id="deleteBtn" disabled onclick="deleteUsers()">
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
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
                                <tr>
                                    <td width="6%" height="26" background="../images/tab_14.gif" class="STYLE1">
                                        <div align="center" class="STYLE2 STYLE1"><input type="checkbox" id="selectAll"
                                                                                         onclick="chooseAll()"/></div>
                                    </td>
                                    <td width="8%" height="18" background="../images/tab_14.gif" class="STYLE1">
                                        <div align="center" class="STYLE2 STYLE1">序号</div>
                                    </td>
                                    <td width="12%" height="18" background="../images/tab_14.gif" class="STYLE1">
                                        <div align="center" class="STYLE2 STYLE1">用户代码</div>
                                    </td>
                                    <td width="24%" height="18" background="../images/tab_14.gif" class="STYLE1">
                                        <div align="center" class="STYLE2">用户姓名</div>
                                    </td>
                                    <td width="38%" height="18" background="../images/tab_14.gif" class="STYLE1">
                                        <div align="center" class="STYLE2 STYLE1">机构类型</div>
                                    </td>
                                </tr>
                                <c:forEach items="${page.dataList}" var="user" varStatus="userStatus">
                                    <tr>
                                        <td height="18" bgcolor="#FFFFFF">
                                            <div align="center" class="STYLE1">
                                                <input name="checkboxInfo" type="checkbox" class="STYLE2"
                                                       value="${user.usercode}"
                                                       onclick="modifyDisabled()"/>
                                            </div>
                                        </td>
                                        <td height="18" bgcolor="#FFFFFF" class="STYLE2">
                                            <div align="center"
                                                 class="STYLE2 STYLE1">${(page.pageno-1)*page.pagesize+userStatus.count}
                                            </div>
                                        </td>
                                        <td height="18" bgcolor="#FFFFFF">
                                            <div align="center" class="STYLE2 STYLE1">${user.usercode}
                                            </div>
                                        </td>
                                        <td height="18" bgcolor="#FFFFFF">
                                            <div align="center" class="STYLE2 STYLE1">${user.username}
                                            </div>
                                        </td>
                                        <td height="18" bgcolor="#FFFFFF">
                                            <div align="center"
                                                 class="STYLE2 STYLE1">${egov:getStringByResource(user.orgtype)}
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
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
                                    <td width="25%" height="29" nowrap="nowrap"><span
                                            class="STYLE1">共${page.totalsize}条记录，当前第${page.pageno}/${page.totalpage}页，每页${page.pagesize}条记录</span>
                                        <input type="hidden" name="pageno" value="${page.pageno}"/>
                                    </td>
                                    <td width="75%" valign="top" class="STYLE1">
                                        <div align="right">
                                            <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td width="30" height="22" valign="middle">
                                                        <div align="right"><img
                                                                src="../images/firstPage${isNotFirstPage?"":"Disabled"}.gif"
                                                        ${isNotFirstPage ? "style='cursor: pointer' onclick='changePage(1)'" : ""}/>
                                                        </div>
                                                    </td>
                                                    <td width="30" height="22" valign="middle">
                                                        <div align="right"><img
                                                                src="../images/prevPage${isNotFirstPage?"":"Disabled"}.gif"
                                                        ${isNotFirstPage ? egov:changePage(page.pageno - 1) : ""}/>
                                                        </div>
                                                    </td>
                                                    <td width="30" height="22" valign="middle">
                                                        <div align="right"><img
                                                                src="../images/nextPage${isNotLastPage?"":"Disabled"}.gif"
                                                        ${isNotLastPage ? egov:changePage(page.pageno + 1) : ""}/>
                                                        </div>
                                                    </td>
                                                    <td width="30" height="22" valign="middle">
                                                        <div align="right"><img
                                                                src="../images/lastPage${isNotLastPage?"":"Disabled"}.gif"
                                                        ${isNotLastPage ? egov:changePage(page.totalpage) : ""}/>
                                                        </div>
                                                    </td>
                                                    <td width="59" height="22" valign="middle">
                                                        <div align="right">转到第</div>
                                                    </td>
                                                    <td width="25" height="22" valign="middle"><span class="STYLE7">
                    <input name="textfield" type="text" class="STYLE1" style="height:14px; width:25px;text-align:right"
                           size="5" id="mypageno"/>
                  </span></td>
                                                    <td width="23" height="22" valign="middle">页</td>
                                                    <td width="30" height="22" valign="middle"><img
                                                            src="../images/go.gif"
                                                            style="cursor: pointer"
                                                            onclick="goPage()"
                                                            width="37" height="15"/>
                                                    </td>
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