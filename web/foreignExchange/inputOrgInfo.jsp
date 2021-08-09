<%@page pageEncoding="utf-8" %>
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

        #isregnoEmpty,
        #isregcryEmpty,
        #iscnnameEmpty,
        #isinvlistEmpty {
            color: red;
        }

        -->
    </style>

    <script type="text/javascript" src="/EGOV/js/formUtil.js"></script>
    <script type="text/javascript" src="/EGOV/js/jquery-3.6.0.js"></script>
    <script>
        // 动态添加投资者
        function addNewLine(invregnum, invname, cty) {
            // 获取表格
            let invListTable = document.getElementById("invListTable");
            // 获取表格现有的总行数
            let index = invListTable.rows.length;

            // 在表格末尾添加一行
            let tablerow = invListTable.insertRow(index);
            tablerow.id = invregnum;
            tablerow.style.backgroundColor = "white";
            // 给新行添加5个单元格
            let tableCell0 = tablerow.insertCell(0);
            let tableCell1 = tablerow.insertCell(1);
            let tableCell2 = tablerow.insertCell(2);
            let tableCell3 = tablerow.insertCell(3);
            let tableCell4 = tablerow.insertCell(4);
            // 给每个单元设置HTML
            tableCell0.innerHTML = '<div align="center" style="padding:5px" class="STYLE2 STYLE1"><input type="hidden" name="invregnum" value="' + invregnum + '"/>' + invname + '</div>';
            tableCell1.innerHTML = '<div align="center" style="padding:2px" class="STYLE2"><input type="hidden" name="cty" value="' + cty + '"/>' + cty + '</div>';
            tableCell2.innerHTML = '<div align="center" style="padding:5px" class="STYLE2 STYLE1"><input type="text" name="regcapItem" onblur="computeCap()" style="width:90px; height:20px; border:solid 1px #035551; color:#000000"><font color="red">*</font></div>';
            tableCell3.innerHTML = '<div align="center" style="padding:2px" class="STYLE2"><input type="text" name="scale" style="width:90px; height:20px; border:solid 1px #035551; color:#000000"><font color="red">*</font></div>';
            tableCell4.innerHTML = '<div align="center" style="padding:2px" class="STYLE2"><img style="cursor: pointer" onclick="delRow(' + invregnum + ')" src="../images/delete.jpg"/></div>';
        }

        // 删除投资者信息
        function delRow(id) {
            let invListTable = document.getElementById("invListTable");
            let tableRow = document.getElementById(id);
            invListTable.deleteRow(tableRow.rowIndex);
        }

        // 计算外方出资比例
        function computeCap() {
            let regcapItem = document.getElementsByName("regcapItem");
            let ctys = document.getElementsByName("cty");
            let totalRegcap = 0;
            let outTotalRegcap = 0;
            for (let i = 0; i < regcapItem.length; i++) {
                if (regcapItem[i].value != "") {
                    totalRegcap += parseInt(regcapItem[i].value);
                    if (ctys[i].value != "中国") {
                        outTotalRegcap += parseInt(regcapItem[i].value);
                    }
                }
            }

            $("#regcap").val(totalRegcap);
            $("#outregcap").val(outTotalRegcap);
            $("#tipScale").html((outTotalRegcap / totalRegcap * 100).toFixed(2) + "%");
        }

        function checkEmpty(formItem) {
            if (!formCheck.isNotEmpty([formItem])) {
                return;
            }
        }

        function validData() {
            let regno = new FormItem('外汇登记证号', 'regno');
            let cnname = new FormItem('企业中文名称', 'cnname');
            let regcry = new FormItem('注册币种', 'regcry');
            if ($("#regcap").val() == "" || $("#outregcap").val() == "") {
                $("#isinvlistEmpty").text("投资者列表不能为空，请点击查询并填写注册资本");
                return false;
            } else {
                $("#isinvlistEmpty").text("");
            }
            let formArr = [regno, cnname, regcry];
            return formCheck.isNotEmpty(formArr);
        }

        function doSave() {
            if (validData()) {
                document.forms["enForm"].submit();
            }
        }
    </script>
</head>

<body>
<form action="/EGOV/servlet/insertEn" method="post" name="enForm">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="30">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30"/></td>
                        <td width="1101" background="../images/tab_05.gif"><img src="../images/311.gif" width="16"
                                                                                height="16"/> <span class="STYLE4">新设外商企业登记-录入</span>
                        </td>
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
                                <tr>
                                    <td width="100" height="26" class="STYLE1" colspan="4">
                                        <div align="center" style="padding:5px" class="STYLE2 STYLE1"><font
                                                color="#FFFFFF"><B>企业基本信息</B></font>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">组织机构代码:</div>
                                    </td>
                                    <td width="250" bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2">${param.orgcode}
                                            <input type="hidden" name="orgcode" value="${param.orgcode}"/>
                                        </div>
                                    </td>
                                    <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">外汇登记证号:</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                    onblur="checkEmpty(new FormItem('外汇登记证号','regno'))"
                                                                                                    name="regno"
                                                                                                    id="regno"
                                                                                                    style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font
                                                color="red">*</font>&nbsp;<span id="isregnoEmpty"></span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">企业中文名称:</div>
                                    </td>
                                    <td width="250" bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                    onblur="checkEmpty(new FormItem('企业中文名称','cnname'))"
                                                                                                    name="cnname"
                                                                                                    id="cnname"
                                                                                                    style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font
                                                color="red">*</font>&nbsp;<span id="iscnnameEmpty"></span>
                                        </div>
                                    </td>
                                    <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">企业英文名称:</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                    name="enname"
                                                                                                    style="width:150px; height:20px; border:solid 1px #035551; color:#000000">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">联系人:</div>
                                    </td>
                                    <td width="250" bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                    name="contactman"
                                                                                                    style="width:150px; height:20px; border:solid 1px #035551; color:#000000">
                                        </div>
                                    </td>
                                    <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">联系电话:</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                    name="contacttel"
                                                                                                    style="width:150px; height:20px; border:solid 1px #035551; color:#000000">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100" height="26" class="STYLE1" colspan="4">
                                        <div align="center" width="100%" style="padding:5px" class="STYLE2 STYLE1"><font
                                                color="#FFFFFF"><B>企业资金情况信息</B></font></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">注册资本:</div>
                                    </td>
                                    <td width="250" bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2"><input type="text" readonly
                                                                                                    name="regcap"
                                                                                                    id="regcap"
                                                                                                    style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font
                                                color="red">*</font></div>
                                    </td>
                                    <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">注册币种:</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2">
                                            <select name="regcry" id="regcry"
                                                    onblur="checkEmpty(new FormItem('注册币种','regcry'))"
                                                    style="WIDTH:100px">
                                                <option value=""></option>
                                                <option value="000">人民币</option>
                                                <option value="001">美元</option>
                                                <option value="002">英镑</option>
                                                <option value="003">日元</option>
                                            </select> <font color="red">*</font>&nbsp;<span id="isregcryEmpty"></span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">外方注册资本:</div>
                                    </td>
                                    <td width="250" bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2"><input type="text" readonly
                                                                                                    name="outregcap"
                                                                                                    id="outregcap"
                                                                                                    style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font
                                                color="red">*</font></div>
                                    </td>
                                    <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                        <div align="right" style="padding:5px" class="STYLE2 STYLE1">外方出资比例:</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" class="STYLE1">
                                        <div align="left" style="padding:2px" class="STYLE2"><span
                                                id="tipScale">0%</span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100" height="26" class="STYLE1" colspan="4">
                                        <div align="center" style="padding:5px" class="STYLE2 STYLE1"><font
                                                color="#FFFFFF"><B>投资者资金及利润分配</B></font>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100%" bgcolor="#FFFFFF" class="STYLE1" colspan="4">
                                        <table id="invListTable" border="0" width="100%" height="100%" cellpadding="0"
                                               cellspacing="1"
                                               bgcolor="#0e6f68">
                                            <tr>
                                                <td width="20%" bgcolor="#CCCCCC" height="20" class="STYLE1">
                                                    <div align="center" style="padding:5px" class="STYLE2 STYLE1">投资者名称
                                                    </div>
                                                </td>
                                                <td width="20%" bgcolor="#CCCCCC" class="STYLE1">
                                                    <div align="center" style="padding:2px" class="STYLE2">国别</div>
                                                </td>
                                                <td width="20%" bgcolor="#CCCCCC" class="STYLE1">
                                                    <div align="center" style="padding:5px" class="STYLE2 STYLE1">
                                                        注册资本出资额
                                                    </div>
                                                </td>
                                                <td width="20%" bgcolor="#CCCCCC" class="STYLE1">
                                                    <div align="center" style="padding:2px" class="STYLE2">利润分配比例</div>
                                                </td>
                                                <td width="20%" bgcolor="#CCCCCC" class="STYLE1">
                                                    <div align="center" style="padding:2px" class="STYLE2"><img
                                                            src="../images/query.jpg" style="cursor: pointer"
                                                            onclick="window.open('/EGOV/foreignExchange/orgcodeSelect.jsp', '分页查询投资人信息', 'width=720, height=400, scrollbars=no')"/>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100" height="26" class="STYLE1" colspan="4">
                                        <div align="center" style="padding:5px" class="STYLE2 STYLE1">
                                            <b id="isinvlistEmpty"></b>
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
                                <tr height="30">
                                    <td bgcolor="#FFFFFF" height="30" class="STYLE1" colspan="2" align="center">
                                        <img src="../images/ok.jpg" onclick="doSave()" style="cursor: pointer"/>&nbsp;&nbsp;
                                        <img src="../images/back.jpg"
                                             onclick="document.location='/EGOV/foreignExchange/newInputOrg.jsp'"
                                             style="cursor: pointer"/>
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
