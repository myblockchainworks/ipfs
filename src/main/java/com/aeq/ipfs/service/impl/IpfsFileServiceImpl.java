package com.aeq.ipfs.service.impl;

import com.aeq.ipfs.model.IpfsFile;
import com.aeq.ipfs.repository.IpfsFileRepository;
import com.aeq.ipfs.service.IpfsFileService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by thomson on 22/8/17.
 */
@Service
public class IpfsFileServiceImpl implements IpfsFileService {

    IpfsFileRepository ipfsFileRepository;

    public IpfsFileServiceImpl(IpfsFileRepository ipfsFileRepository) {
        this.ipfsFileRepository = ipfsFileRepository;
    }

    @Override
    public IpfsFile addIpfFile(IpfsFile file) {
        return ipfsFileRepository.save(file);
    }

    @Override
    public IpfsFile getIpfsFile(Long id) {
        return ipfsFileRepository.getOne(id);
    }

    @Override
    public IpfsFile getByHash(String hash) {
        return ipfsFileRepository.findByHash(hash);
    }

    @Override
    public IpfsFile getByName(String fileName) {
        return ipfsFileRepository.findByFileName(fileName);
    }

    @Override
    public List<IpfsFile> getByDate(Date uploadedDate) {
        return ipfsFileRepository.findByUploadedDate(uploadedDate);
    }

    @Override
    public List<IpfsFile> getAllIpfsFiles() {
        return ipfsFileRepository.findAll();
    }
}
