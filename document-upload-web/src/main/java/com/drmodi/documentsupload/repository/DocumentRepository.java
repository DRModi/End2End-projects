package com.drmodi.documentsupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drmodi.documentsupload.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
