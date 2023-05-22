package com.faw.usertestall.service;

import java.io.File;
import java.io.InputStream;

public interface FileService {

    void upload(InputStream inputStream, String fileName);

    void upload(File file);

}
