package com.aeq.ipfs.service;

import com.aeq.ipfs.model.IpfsFile;
import io.ipfs.api.IPFS;

import java.util.Date;
import java.util.List;

/**
 * Created by thomson on 22/8/17.
 */
public interface IpfsFileService {

    IpfsFile addIpfFile(IpfsFile file);

    IpfsFile getIpfsFile(Long id);

    IpfsFile getByHash(String hash);

    IpfsFile getByName(String fileName);

    List<IpfsFile> getByDate(Date uploadedDate);

    List<IpfsFile> getAllIpfsFiles();
}
