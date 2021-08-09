<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="egov" uri="http://www.egov.com/jsp/jstl/functions" %>
<%@ page import="com.zj.egov.bean.Invest" %>
<%@ page import="com.zj.egov.bean.Page" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%
    Page<Invest> thisPage = (Page<Invest>) request.getAttribute("page");
    int totalPage = 0;
    int pageNo = 0;
    if (thisPage != null) {
        totalPage = thisPage.getTotalpage();
        pageNo = thisPage.getPageno();
        pageContext.setAttribute("isNotFirstPage", pageNo > 1);
        pageContext.setAttribute("isNotLastPage", pageNo < totalPage);
    } else {
        thisPage = new Page<>();
        request.setAttribute("page", thisPage);
    }
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="../clander/date.js"></script>
    <%--    梅花雨日历控件不可用，查找不到writeln--%>
    <%--    <script type="text/javascript" src="../clander/setday.js"></script>--%>
    <title>企业投资人信息登记列表</title>
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
    <script type="text/javascript" src="/EGOV/js/jquery-3.6.0.js"></script>
    <script>
        function pageQueryInv() {
            // document.all.dataTableBody.style.display = "contents";
            $("#pageno").val(1);
            document.forms["invForm"].submit();
        }

        function changePage(pageno) {
            // document.location = "/EGOV/servlet/pageQueryInv?pageNo=" + pageno;
            document.forms["invForm"].action = "/EGOV/servlet/pageQueryInv?pageno=" + pageno;
            document.forms["invForm"].submit();
        }

        function goPage() {
            let pagenoObj = $("#mypageno");
            let pageno = pagenoObj.val();
            let pageNo = parseInt(pageno);

            if (pageno == "") {
                alert("请输入跳转页码");
                pagenoObj.focus();
                return;
            }

            if (isNaN(pageno)) {
                alert("页码必须为数字，请重新填写");
                pagenoObj.val("");
                pagenoObj.focus();
                return;
            }
            if (pageNo < 1 || pageNo > ${page.getTotalpage()}) {
                alert("请输入[1-${page.getTotalpage()}]范围内的页码");
                pagenoObj.val("");
                pagenoObj.focus();
                return;
            }

            changePage(pageNo);
        }

        // 清空表单
        function clearForm() {
            $("#invregnum").val("");
            $("#invname").val("");
        }
    </script>
</head>

<body>
<form action="/EGOV/servlet/pageQueryInv" method="post" name="invForm">
    <input type="hidden" name="forward" value="/foreignExchange/orgcodeSelect.jsp">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="30">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30"/></td>
                        <td width="1101" background="../images/tab_05.gif"><img src="../images/311.gif" width="16"
                                                                                height="16"/> <span class="STYLE4">企业投资人信息登记列表</span>
                        </td>
                        <td width="281" background="../images/tab_05.gif">
                            <table border="0" align="right" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td width="60">
                                        <table width="90%" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td class="STYLE1"></td>
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
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr height="5">
                    <td width="9" background="../images/tab_12.gif">&nbsp;</td>
                    <td bgcolor="#f3ffe3">&nbsp;</td>
                    <td width="9" background="../images/tab_16.gif">&nbsp;</td>
                </tr>
                <tr>
                    <td width="9" background="../images/tab_12.gif">&nbsp;</td>
                    <td bgcolor="#f3ffe3">
                        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1">
                            <tr>
                                <td width="120" class="STYLE1">投资人登记编号:</td>
                                <td width="140" class="STYLE1"><input type="text" name="invregnum" id="invregnum"
                                                                      value="${param.invregnum}"
                                                                      style="width:100px; height:20px; border:solid 1px #035551; color:#000000">
                                </td>
                                <td width="90" class="STYLE1">投资人名称:</td>
                                <td width="130" class="STYLE1"><input type="text" name="invname" id="invname"
                                                                      value="${param.invname}"
                                                                      style="width:100px; height:20px; border:solid 1px #035551; color:#000000">
                                </td>
                                <td width="60" nowrap class="STYLE1">登记日期:</td>
                                <td class="class1_td alignleft" nowrap>
                                    <input type="text" name="startdate" value="${param.startdate}"
                                           style="width:75px; height:20px; border:solid 1px #035551; color:#000000"
                                           readonly>
                                    <input type="image" value=" 选择日期" disabled
                                           name="button004" src="../clander/clander.gif" align="top"/>
                                    ～
                                    <input type="text" name="enddate" value="${param.enddate}"
                                           style="width:75px; height:20px; border:solid 1px #035551; color:#000000"
                                           readonly>
                                    <input type="image" value=" 选择日期" disabled
                                           name="button004" src="../clander/clander.gif" align="top"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="STYLE1" colspan="5" align="left">&nbsp;&nbsp;</td>
                                <td nowrap class="STYLE1" align="right">
                                    <img src="../images/query.jpg" style="cursor:pointer" onclick="pageQueryInv()"/>&nbsp;&nbsp;
                                    <img src="../images/clear.jpg" style="cursor:pointer" onclick="clearForm()"/>&nbsp;&nbsp;
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="9" background="../images/tab_16.gif">&nbsp;</td>
                </tr>
                <tr height="10">
                    <td width="9" background="../images/tab_12.gif">&nbsp;</td>
                    <td bgcolor="#f3ffe3">&nbsp;</td>
                    <td width="9" background="../images/tab_16.gif">&nbsp;</td>
                </tr>
            </table>
        </tr>
        <tr>
            <td>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
                        <td bgcolor="#f3ffe3">
                            <table width="99%" id="dataTable" border="0" align="center" cellpadding="0" cellspacing="1"
                                   bgcolor="#0e6f68">
                                <thead class="class1_thead">
                                <tr>
                                    <td height="18" background="../images/tab_14.gif" class="STYLE1">
                                        <div align="center" class="STYLE2 STYLE1">序号</div>
                                    </td>
                                    <td height="18" background="../images/tab_14.gif"
                                        class="STYLE1">
                                        <div align="center" class="STYLE2 STYLE1">投资人登记编号</div>
                                    </td>
                                    <td height="18" background="../images/tab_14.gif"
                                        class="STYLE1">
                                        <div align="center" class="STYLE2">投资人名称</div>
                                    </td>
                                    <td height="18" background="../images/tab_14.gif"
                                        class="STYLE1">
                                        <div align="center" class="STYLE2 STYLE1">登记日期</div>
                                    </td>
                                    <td height="18" background="../images/tab_14.gif"
                                        class="STYLE1">
                                        <div align="center" class="STYLE2 STYLE1">经办人</div>
                                    </td>
                                </tr>
                                </thead>
                                <tbody id="dataTableBody">
                                <c:if test="${not empty page.dataList}">
                                    <c:forEach items="${page.dataList}" var="inv" varStatus="invStatus">
                                        <tr>
                                            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
                                                <div align="center"
                                                     class="STYLE2 STYLE1">${(page.pageno-1)*page.pagesize+invStatus.count}
                                                </div>
                                            </td>
                                            <td height="18" bgcolor="#FFFFFF">
                                                <div align="center" class="STYLE2 STYLE1" style="cursor:hand"
                                                     onclick="window.opener.addNewLine('${inv.invregnum}','${inv.invname}','${egov:getStringByResource(inv.cty)}')">${inv.invregnum}
                                                </div>
                                            </td>
                                            <td height="18" bgcolor="#FFFFFF">
                                                <div align="center" class="STYLE2 STYLE1">${inv.invname}
                                                </div>
                                            </td>
                                            <td height="18" bgcolor="#FFFFFF">
                                                <div align="center" class="STYLE2 STYLE1">${inv.regdate}
                                                </div>
                                            </td>
                                            <td height="18" bgcolor="#FFFFFF">
                                                <div align="center" class="STYLE2 STYLE1">${inv.username}
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
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
                                                        <div align="right" class="STYLE2 STYLE1">转到第</div>
                                                    </td>
                                                    <td width="25" height="22" valign="middle"><span class="STYLE7">
                    <input name="textfield" type="text" class="STYLE1" style="height:20px; width:25px;text-align:right"
                           size="5" id="mypageno" ${page.pageno eq 0? "readonly" : ""}/>
                  </span></td>
                                                    <td width="23" height="22" valign="middle" class="STYLE2 STYLE1">页
                                                    </td>
                                                    <td width="30" height="22" valign="middle">
                                                        <input type="image" src="../images/go.gif" onclick="goPage()"
                                                               width="37"
                                                               height="15" ${page.pageno eq 0? "disabled" : ""}/>
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