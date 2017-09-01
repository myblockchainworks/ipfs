package com.aeq.ipfs.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;

/**
 * Created by thomson on 22/8/17.
 */
public class FileUtil
{
    public static boolean isVideoFile(MultipartFile multipartFile)
    {
        File file = new File(multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String mimetype= new MimetypesFileTypeMap().getContentType(file);
        String type = mimetype.split("/")[0];

        if(type.equals("video"))
            return true;
        else
            return false;
    }
}
