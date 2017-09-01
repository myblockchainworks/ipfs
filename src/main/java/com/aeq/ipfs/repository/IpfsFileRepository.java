package com.aeq.ipfs.repository;

import com.aeq.ipfs.model.IpfsFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by thomson on 22/8/17.
 */
public interface IpfsFileRepository  extends JpaRepository<IpfsFile, Long>{

    IpfsFile findByHash(String hash);
    IpfsFile findByFileName(String name);
    List<IpfsFile> findByUploadedDate(Date date);
}
