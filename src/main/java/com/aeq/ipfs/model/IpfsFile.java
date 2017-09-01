package com.aeq.ipfs.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by thomson on 22/8/17.
 */
@Entity
@Table(name="ipfs_file")
public class IpfsFile
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "file_name", unique = true)
    private String fileName;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "hash", unique = true)
    private String hash;

    @Column(name = "file_type")
    private String fileType;


    @Column(name = "uploaded_date")
    private Date uploadedDate;

    public IpfsFile() {
    }

    public IpfsFile(String fileName, String title, String description, String hash, String fileType, Date uploadedDate) {
        this.fileName = fileName;
        this.title = title;
        this.description = description;
        this.hash = hash;
        this.fileType = fileType;
        this.uploadedDate = uploadedDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }
}

