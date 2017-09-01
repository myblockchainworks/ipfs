package com.aeq.ipfs.controller;


import com.aeq.ipfs.bean.ApiResult;
import com.aeq.ipfs.model.IpfsFile;
import com.aeq.ipfs.service.IpfsFileService;
import com.google.common.io.Files;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;


@RestController
@RequestMapping(value = "ipfs/")
class IpfsApi {

    IpfsFileService ipfsFileService;

    IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");

    public IpfsApi(IpfsFileService ipfsFileService) {
        this.ipfsFileService = ipfsFileService;
    }

    @CrossOrigin
    @RequestMapping(value = "healthcare", method = RequestMethod.GET)
    public String healthCare() {

        String ipfsName = ipfs.host;

        if (!StringUtils.isEmpty(ipfsName)) {
            return "Ipfs server is connected to : " + ipfsName;
        } else {
            return "Ipfs server is not connected...";
        }
    }

    @CrossOrigin
    @RequestMapping(value = "addFile", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ApiResult<IpfsFile> addFile(@RequestParam("file") MultipartFile file,
                                       @RequestParam("title") String title,
                                       @RequestParam("description") String description) {


        if (!file.isEmpty() || file != null) {
            System.out.println("Content Type: " + file.getContentType());
            String fileName = Files.getNameWithoutExtension(file.getOriginalFilename());

            byte[] contents;
            Multihash pointer = null;
            IpfsFile ipfsFile = new IpfsFile();
            try {
                contents = file.getBytes();
                NamedStreamable raw = new NamedStreamable.ByteArrayWrapper(fileName, contents);
                MerkleNode addResult = ipfs.add(raw);
                pointer = addResult.hash;
                System.out.println("Point to string " + pointer.toString());

                String fileType = Files.getFileExtension(file.getOriginalFilename());
                ipfsFile = new IpfsFile(fileName, title, description, pointer.toString(), fileType, Calendar.getInstance().getTime());
                ipfsFileService.addIpfFile(ipfsFile);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println(e.getMessage());
                return new ApiResult<>(false, "error while adding file to ipfs");
            }

            if (!StringUtils.isEmpty(pointer)) {
                return new ApiResult<>(true, ipfsFile, "File added successfully...");
            }
            return new ApiResult<>(false, "");
        } else {
            return new ApiResult<>(false, "empty file..");
        }
    }


    @CrossOrigin
    @RequestMapping(value = "getInfo/{hash}", method = RequestMethod.GET)
    public ApiResult<List> getInfoByHash(@PathVariable("hash") String hash) {
        if (!StringUtils.isEmpty(hash)) {
            try {
                Multihash filePointer = Multihash.fromBase58(hash);

                List<MerkleNode> list = ipfs.ls(filePointer);
                return new ApiResult<>(true, list, "");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return new ApiResult<>(false, "Error while getting info");
            }

        } else {
            return new ApiResult<>(false, "");
        }
    }

}
