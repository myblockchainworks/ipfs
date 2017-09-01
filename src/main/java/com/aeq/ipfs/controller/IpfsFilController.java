package com.aeq.ipfs.controller;

import com.aeq.ipfs.bean.ApiResult;
import com.aeq.ipfs.model.IpfsFile;
import com.aeq.ipfs.service.IpfsFileService;
import io.ipfs.api.IPFS;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by thomson on 22/8/17.
 */
@RestController
@RequestMapping(value = "ipfsFile/")
public class IpfsFilController {

    IpfsFileService ipfsFileService;

    public IpfsFilController(IpfsFileService ipfsFileService) {
        this.ipfsFileService = ipfsFileService;
    }

    @CrossOrigin
    @RequestMapping(value = "getAllIpfsFile", method = RequestMethod.GET)
    public ApiResult<List<IpfsFile>> getAllIpfFiles()
    {
        List<IpfsFile> ipfsFiles = ipfsFileService.getAllIpfsFiles();

        if(!ipfsFiles.isEmpty()){
            return new ApiResult<>(true, ipfsFiles,"");
        } else {
            return new ApiResult<>(false,"There are no files added");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "getIpfsFile/{name}", method = RequestMethod.GET)
    public ApiResult<IpfsFile> getIpfsFileByName(@PathVariable("name") String name)
    {
        IpfsFile ipfsFile = ipfsFileService.getByName(name);

        if(!StringUtils.isEmpty(ipfsFile.getFileName())){
            return new ApiResult<>(true, ipfsFile,"");
        } else {
            return new ApiResult<>(false,"There are no file by name");
        }
    }
}
