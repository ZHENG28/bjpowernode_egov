package com.zj.egov.auth.servlet;

import com.zj.egov.auth.service.AuthService;
import com.zj.egov.auth.service.impl.AuthServiceImpl;
import com.zj.egov.bean.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保存核准件
 */
public class SaveAuthServlet extends HttpServlet
{
    private AuthService authService = new AuthServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        // 存储auth信息的map
        Map<String, String> authMap = new HashMap<>();
        authMap.put("usercode", ((User) request.getSession(false).getAttribute("user")).getUsercode());

        // 设置FileItemFactory的参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置一次可接收的最大容量
        factory.setSizeThreshold(4 * 1024); // 4KB
        // 设置缓存文件的文件夹（使用绝对路径，因为相对路径默认在out文件夹下的，此处暂不修改out文件夹）
//        String tmpPath = this.getServletContext().getRealPath("/tmpFile");
        String tmpPath = "E:\\Desktop\\Java Web开发技术\\PowerNode\\Porject\\egov\\web\\tmpFile";
        factory.setRepository(new File(tmpPath));

        // 开始接收信息与上传文件的接收
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大可接收的文件容量
        upload.setSizeMax(1024 * 1024 * 1024);
        boolean uploadOK = true;
        String authPath = null;
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            String orgcode = null;
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    // 获取input中的name值
                    String name = fileItem.getFieldName();
                    // 获取input中的value值
                    String value = fileItem.getString("UTF-8");
                    authMap.put(name, value);
                    if ("orgcode".equals(name)) {
                        orgcode = value;
                    }
                } else {
                    // 如果不是表单元素，即为文件元素，则获取其文件名
                    String oldFileName = fileItem.getName();
                    // 为避免文件名重复，特采用组织机构（全球唯一）作为其上传修改后的文件名
                    String newFileName = orgcode + oldFileName.substring(oldFileName.lastIndexOf("."));
                    // 设置文件上传路径，此处应在绝对路径的文件夹名后加上文件名
                    authPath = "E:\\Desktop\\Java Web开发技术\\PowerNode\\Porject\\egov\\web\\authFile\\" + newFileName;
                    authMap.put("filename", newFileName);
                    // 将上传文件写入到对应的文件夹内
                    fileItem.write(new File(authPath));
                }
            }
        } catch (Exception e) {
            uploadOK = false;
            e.printStackTrace();
        }

        // 判断结果，调用View层
        if (uploadOK) {
            // 如果上传成功
            if (authService.saveAuth(authMap)) {
                // 如果保存成功
                try {
                    response.sendRedirect("/EGOV/auth/inputOrg.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // 如果保存失败，则删除写好的文件
                new File(authPath).delete();
            }
        }
    }
}
