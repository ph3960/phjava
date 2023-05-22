package com.faw.usertestall.service;

import com.faw.usertestall.domain.dto.UserQueryDTO;

public interface ExcelService {
    void export(String filename, UserQueryDTO queryDTO);

    void asyncExport(String filename, UserQueryDTO queryDTO);
}
