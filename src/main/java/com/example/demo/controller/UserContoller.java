package com.example.demo.controller;

import com.example.demo.controller.ex.FileSizeException;
import com.example.demo.controller.ex.FileStateException;
import com.example.demo.controller.ex.FileTypeException;
import com.example.demo.controller.ex.FileUploadIOException;
import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.ex.InsertException;
import com.example.demo.service.ex.UsernameDuplicatedException;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserContoller extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
       userService.reg(user);
       return new JsonResult<>(200);
    }
    @RequestMapping("login")
    public JsonResult<User> login(String username,
                                String password,
                                HttpSession httpSession){
        User user = userService.login(username, password);
        httpSession.setAttribute("uid", user.getUid());
        httpSession.setAttribute("username", user.getUsername());

        System.out.println(getUidFromSession(httpSession));
        System.out.println(getUserNameFromSession(httpSession));
        return  new JsonResult<User>(OK, user);
    }
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession httpSession){
        Integer uid = (Integer) httpSession.getAttribute("uid");
        userService.changePassword(uid, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }
    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data =  userService.getByUid(getUidFromSession(session));
        return new JsonResult<User>(OK, data);
    }
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession httpSession){

        Integer uid = getUidFromSession(httpSession);
        String username = getUserNameFromSession(httpSession);
        userService.changeInfo(uid, username, user);
        return  new JsonResult<>(OK);
    }

    /**
     *?????????????????? @RequestParam ????????????request??????????????????????????????????????????????????????????????????
     * @param httpSession
     * @param file  MultipartFile?????????????????????????????????????????? ??????????????????????????????
     *             springboot???????????????????????????????????????
     * @return
     */

    /**
     * ??????????????????*/
    public static final Integer AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image /bmp");
    }
    @PostMapping("change_avatar")
    public JsonResult<String> change_avatar(HttpSession httpSession, @RequestParam("file") MultipartFile file) throws FileNotFoundException {
        if (file.isEmpty() ){
            throw  new FileNotFoundException("???????????????");
        }
        if(file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("???????????????");
        }

        String contendType =   file.getContentType();
        if(!AVATAR_TYPE.contains(contendType)){
            throw  new FileTypeException("?????????????????????");
        }
       String parent = httpSession.getServletContext().getRealPath("upload");
        File dir = new File(parent);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename :" + originalFilename);
        int index =  originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;

        File dest =  new File(dir , filename); //dir?????????????????????finename????????????
        try {
            file.transferTo(dest);  //???file?????????????????????dest??????
        }catch (FileStateException e){
           throw new FileStateException("??????????????????");
        }catch (IOException e) {
            throw  new FileUploadIOException("??????????????????");
        }
        Integer uid = getUidFromSession(httpSession);
        String username = getUserNameFromSession(httpSession);
        String avatar = "/upload/" + filename;
        userService.changeAvatar(uid, filename, username);
        // ?????????????????????????????????????????????????????????????????????
        return new JsonResult<>(OK, avatar);
    }
}
