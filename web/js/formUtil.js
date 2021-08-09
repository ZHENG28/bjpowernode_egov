// 定义表单项类型
FormItem = function (label, id) {
    this.label = label;
    this.id = id;
};

formValidData = function () {
    // 验证非空（参数为数组，数组中的元素为FormItem类型）
    this.isNotEmpty = function (formItemArr) {
        for (let i = 0; i < formItemArr.length; i++) {
            let formItem = formItemArr[i];
            let label = formItem.label;
            let domObj = document.getElementById(formItem.id);
            if (domObj.value == "") {
                $("#is" + formItem.id + "Empty").text(label + "不能为空");
                return false;
            } else {
                $("#is" + formItem.id + "Empty").text("");
            }
        }
        return true;
    };

    // 验证一致(参数为FormItem类型)
    this.isSame = function (item1, item2) {
        let id1 = item1.id;
        let label1 = item1.label;
        let domObj1 = document.getElementById(id1);

        let id2 = item2.id;
        let label2 = item2.label;
        let domObj2 = document.getElementById(id2);

        if (domObj1.value != domObj2.value) {
            $("#is" + item2.id + "Empty").text(label1 + "和" + label2 + "不一致");
            domObj1.value = "";
            domObj2.value = "";
            domObj1.focus();
            return false;
        } else {
            $("#is" + item2.id + "Empty").text(label1 + "和" + label2 + "一致");
            $("#is" + item2.id + "Empty").css('color', 'green');
        }
        return true;
    }
};

let formCheck = new formValidData();