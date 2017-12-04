package com.pam.pam_front.model;

import java.util.List;

public interface IResponse {
    void succeed();
    void failure();
    void setList(List list);
}
